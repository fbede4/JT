package Helpers;

import Dtos.RideDto;
import Dtos.VehicleDto;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class implements the communication with the localdb
 * SQLite
 */
public class SQLiteHandler {
    /**
     * Connect to the local database
     */
    private static Connection connect() {
        String url = "jdbc:sqlite:localdb.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a vehicle into the vehicles table
     * @param vehicle
     */
    public static void insertVehicle(VehicleDto vehicle) {
        String sql = "INSERT INTO vehicles (id,brand,model,type,yom) " +
                "VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vehicle.id);
            pstmt.setString(2, vehicle.brand);
            pstmt.setString(3, vehicle.model);
            pstmt.setString(4, vehicle.typeOfCar);
            pstmt.setInt(5, vehicle.yearOfProduction);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * clear vehicles
     */
    public static void clearVehicles(){
        String sql = "DELETE FROM vehicles";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all rows in the rides table
     */
    public static ArrayList<VehicleDto> getVehicles(){
        String sql = "SELECT id,brand,model,type,yom FROM vehicles";
        ArrayList<VehicleDto> result = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                VehicleDto vehicle = new VehicleDto();
                vehicle.id = rs.getInt("id");
                vehicle.brand = rs.getString("brand");
                vehicle.model = rs.getString("model");
                vehicle.typeOfCar = rs.getString("type");
                vehicle.yearOfProduction = rs.getInt("yom");
                result.add(vehicle);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * Insert a ride into the rides table
     * @param ride
     */
    public static void insertRide(RideDto ride) {
        String sql = "INSERT INTO rides (id,passengerName,distance,pickUpLocation,destination,cost,startTime,endTime) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        DateFormat df = new SimpleDateFormat(Settings.getDsteFormat());
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ride.id);
            pstmt.setString(2, ride.passengerName);
            pstmt.setDouble(3, ride.distance);
            pstmt.setString(4, ride.pickUpLocation);
            pstmt.setString(5, ride.destination);
            pstmt.setDouble(6, ride.cost);
            pstmt.setString(7, df.format(ride.startTime));
            pstmt.setString(8, df.format(ride.endTime));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * clear rides
     */
    public static void clearRides(){
        String sql = "DELETE FROM rides";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all rows in the rides table
     */
    public static ArrayList<RideDto> getRides(){
        String sql = "SELECT id,passengerName,distance,pickUpLocation,destination,startTime,endTime FROM rides";
        ArrayList<RideDto> result = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            SimpleDateFormat formatter = new SimpleDateFormat(Settings.getDsteFormat());

            // loop through the result set
            while (rs.next()) {
                RideDto ride = new RideDto();
                ride.id = rs.getInt("id");
                ride.destination = rs.getString("destination");
                ride.pickUpLocation = rs.getString("pickUpLocation");
                ride.passengerName = rs.getString("passengerName");
                ride.distance = rs.getDouble("distance");
                ride.cost = 0;
                ride.endTime = formatter.parse(rs.getString("endTime"));
                ride.startTime = formatter.parse(rs.getString("startTime"));
                result.add(ride);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Insert a user into the users table
     * @param token
     */
    public static void insertOrUpdateToken(String token) {
        String oldTOken = getToken();
        String sql = "";
        if(oldTOken == "") {
            sql = "INSERT INTO User (accessToken) " +
                    "VALUES('" + token + "')";
        } else {
            sql = "UPDATE User SET accessToken='" + token + "' " +
                    "WHERE id=1";
        }
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * get token
     */
    public static String getToken(){
        String sql = "SELECT accessToken FROM User LIMIT 1";

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if (rs.next()) {
                return rs.getString("accessToken");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void clearToken() {
        String sql = "DELETE FROM User";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  static void createLocalTablesIfNotExist() {
        createRidesTableIfNotExists();
        createVehiclesTableIfNotExists();
        createUserTableIfNotExists();
    }

    /**
     * Create rides table
     */
    private static void createRidesTableIfNotExists() {
        String sql = "CREATE TABLE \"Rides\" ( `id` INTEGER, `passengerName` REAL, `distance` REAL, `pickUpLocation` TEXT, `destination` TEXT, `startTime` TEXT, `endTime` TEXT, `cost` REAL )";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create vehicles table
     */
    private static void createVehiclesTableIfNotExists() {
        String sql = "CREATE TABLE \"Vehicles\" ( `brand` TEXT, `type` TEXT, `model` TEXT, `yom` INTEGER, `id` INTEGER )";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create user table
     */
    private static void createUserTableIfNotExists() {
        String sql = "CREATE TABLE \"User\" ( `accessToken` TEXT, `id` INTEGER NOT NULL UNIQUE, PRIMARY KEY(`id`) )";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
