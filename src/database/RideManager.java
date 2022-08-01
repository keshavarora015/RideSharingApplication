package database;

import entity.Ride;
import exceptions.RideAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RideManager {
    private Map<UUID, Ride> activeRides;


    public RideManager()
    {
        this.activeRides = new HashMap<>();
    }

    public Map<UUID,Ride> getActiveRides() {
        return activeRides;
    }

    public void addOfferRide(Ride ride, String userName)
    {
        for(Ride rd: activeRides.values())
        {
            if(rd.getVehicleNumber().equals(ride.getVehicleNumber()))
                throw new RideAlreadyExistsException();
        }
        activeRides.put(ride.getId(), ride);
    }

    public Ride endRide(String vehicleNumber)
    {
        for(Ride rd: activeRides.values())
        {
            if(rd.getVehicleNumber().equals(vehicleNumber))
                return rd;
        }
        return null;
    }
}
