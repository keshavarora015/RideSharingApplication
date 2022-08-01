package serviceImpl;

import database.RideManager;
import entity.Ride;
import exceptions.NoRideFoundException;
import service.SelectionStrategy;

import java.util.Map;
import java.util.UUID;

public class PreferredVehicleStrategy implements SelectionStrategy {
    @Override
    public Ride findRides(String origin, String destination, int seats, RideManager rideManager, String model) {
        Map<UUID, Ride> activeRides=rideManager.getActiveRides();
        Ride preferredRide=null;
        for(Ride r: activeRides.values())
        {
            if(r.getDestination().equals(destination) && r.getOrigin().equals(origin) && r.getAvailableSeats()>=seats)
            {
                if(r.getVehicleModel().equals(model))
                {
                    preferredRide=r;
                }
            }
        }

        if(preferredRide == null)
            throw new NoRideFoundException();

        return preferredRide;
    }
}
