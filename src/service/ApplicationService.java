package service;

import entity.Ride;
import entity.Vehicle;
import enums.SelectionStrategyType;

public interface ApplicationService {

    //Add User
    void add_user(String name, char gender, int age);

    //Add vehicle;
    void add_vehicle(String userName, String model, String vehicleNumber);

    //Offer Ride
    void offer_ride(String userName,String origin, int availableSeats, String vehicleModel ,String vehicleNumber, String destination);

    //Select Ride
    Ride select_ride(String userName, String origin, String destination, int seats, SelectionStrategyType strategy, String model);

    //End Ride
    public void end_ride(String userName,String origin, int availableSeats, String vehicleModel ,String vehicleNumber, String destination);


    //Print Ride Status
    void print_ride_status();
}
