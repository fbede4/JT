package Dtos;

import java.util.Date;

/**
 * This object is used for the communication
 * with the server (get and send rides)
 */
public class RideDto {
    public int id;
    public String passengerName;
    public double distance;
    public double cost;
    public String pickUpLocation;
    public String destination;
    public Date startTime;
    public Date endTime;
}
