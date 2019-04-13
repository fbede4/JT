package Dtos;

import java.util.Date;

public class RideDto {
    public int id;
    public int passengerId;
    public String passengerName;
    public int driverId;
    public String driverName;
    public String driverUserId;
    public int rideRequestId;
    public double distance;
    public double cost;
    public String pickUpLocation;
    public double pickUpLatitude;
    public double pickUpLongitude;
    public String destination;
    public double destinationLatitude;
    public double destinationLongitude;
    public boolean isProcessed;
    public Date startTime;
    public Date endTime;
}
