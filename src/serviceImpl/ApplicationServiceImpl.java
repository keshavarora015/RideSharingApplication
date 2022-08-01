package serviceImpl;

import database.RideManager;
import database.UserManager;
import entity.Ride;
import entity.User;
import entity.Vehicle;
import enums.SelectionStrategyType;
import exceptions.InvalidVehicleException;
import exceptions.NoRideFoundException;
import service.ApplicationService;

import java.util.Collection;
import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    private RideManager rideManager= new RideManager();
    private UserManager userManager= new UserManager();

    public ApplicationServiceImpl() {
    }

    @Override
    public void add_user(String name, char gender, int age) {

        try {
            User user=new User(name,gender,age);
            userManager.add_Users(user);
            System.out.println("User "+name+" Added" );
       }catch (Exception e){
            System.out.println(e);}
    }

    @Override
    public void add_vehicle(String userName, String model, String vehicleNumber) {
        try {
            Vehicle vehicle=new Vehicle(userName,model,vehicleNumber);
            User user= userManager.getUser(userName);
            user.addVehicle(vehicle);
            System.out.println("Vehicle added Successfully with vehicle number : "+vehicleNumber);
        }catch (RuntimeException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void offer_ride(String userName, String origin, int availableSeats, String model, String vehicleNumber, String destination) {
        try {
            Ride ride= new Ride(userName,origin,destination,availableSeats,vehicleNumber,model);
            User user= userManager.getUser(userName);

            if(!user.checkVehicle(vehicleNumber))
                throw new InvalidVehicleException();
            rideManager.addOfferRide(ride,userName);
            System.out.println("Ride offered by "+userName +
                               " from "+origin +
                               " to "+destination+
                               ", Available Seats : "+availableSeats+
                               " vehicle "+model+
                               " vehicleNumber "+vehicleNumber);

        }catch (RuntimeException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public Ride select_ride(String userName, String origin, String destination, int seats, SelectionStrategyType strategy, String model) {

        Ride ride=null;
        try{
            if(strategy.equals(SelectionStrategyType.PREFFERED))
            {
                PreferredVehicleStrategy st=new PreferredVehicleStrategy();
                ride=st.findRides(origin, destination, seats, rideManager, model);
            }
            else if(strategy.equals(SelectionStrategyType.MOST_VACANT))
            {
                MostVacantStrategy st=new MostVacantStrategy();
                ride= st.findRides(origin, destination, seats, rideManager, model);
            }
            if(ride!=null)
            {
                ride.addPassenger(userName, seats);
            }
            else
                throw new NoRideFoundException();
        }catch(RuntimeException e)
        {
            System.out.println(e);
        }
        return ride;
    }

    @Override
    public void end_ride(String userName, String origin, int availableSeats, String vehicleModel, String vehicleNumber, String destination) {

        Ride ride=rideManager.endRide(vehicleNumber);
        ride.endRide();
        String sharedBy=ride.getSharedBy();
        List<String> passengers=ride.getSelectedBy();

        User user=userManager.getUser(sharedBy);
        user.addSharedRide(ride);

        for(String s: passengers)
        {
            user=userManager.getUser(s);
            user.addConsumedRide(ride);
        }
    }

    @Override
    public void print_ride_status() {

        Collection<User> users=userManager.getUsers();
        for (User user:users) {
            System.out.println(user.getName()+": "+user.getConsumedRides().size()+" Taken "+user.getSharedRides().size()+" Offered");
        }

    }


}
