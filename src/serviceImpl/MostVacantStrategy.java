package serviceImpl;

import database.RideManager;
import entity.Ride;
import exceptions.NoRideFoundException;
import service.SelectionStrategy;

import java.util.Map;
import java.util.UUID;

public class MostVacantStrategy implements SelectionStrategy {
    @Override
    public Ride findRides(String origin, String destination, int seats, RideManager rideManager, String model) {
        Map<UUID, Ride> activeRides=rideManager.getActiveRides();

        Ride mostVacantRide=null;
        int maxAvailable=0;
        for(Ride r: activeRides.values())
        {
            if(r.getDestination().equals(destination) && r.getOrigin().equals(origin) && r.getAvailableSeats()>=seats)
            {
                if(r.getAvailableSeats()>maxAvailable)
                {
                    maxAvailable=r.getAvailableSeats();
                    mostVacantRide=r;
                }
            }
        }
        if(mostVacantRide.equals(null))
            throw new NoRideFoundException();

        return mostVacantRide;
    }
}
