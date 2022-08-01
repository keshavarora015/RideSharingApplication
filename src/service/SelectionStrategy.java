package service;

import database.RideManager;
import entity.Ride;

public interface SelectionStrategy {
     Ride findRides(String origin, String destination, int seats, RideManager rideManager, String model);
}
