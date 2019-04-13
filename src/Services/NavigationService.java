package Services;

import Dtos.RideDto;
import Dtos.VehicleDto;
import Pages.rideDetail.RideDetailController;
import Pages.rides.RidesController;
import Pages.vehicleDetail.VehicleDetailController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationService {
    private static Stage primaryStage;
    public static void setStage(Stage stage){
        primaryStage = stage;
    }

    public void navigateToLogin() {
        try {
            FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("../Pages/login/login.fxml"));
            Parent loginRoot = loginPaneLoader.load();
            Scene loginScene = new Scene(loginRoot, 500, 500);
            primaryStage.setScene(loginScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToRides() {
        try {
            FXMLLoader ridesPaneLoader = new FXMLLoader(getClass().getResource("../Pages/rides/rides.fxml"));
            Parent ridesRoot = ridesPaneLoader.load();
            Scene ridesScene = new Scene(ridesRoot, 500, 500);
            primaryStage.setScene(ridesScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToRidesDetail(RideDto ride) {
        try {
            FXMLLoader rideDetailPaneLoader = new FXMLLoader(getClass().getResource("../Pages/rideDetail/ride_detail.fxml"));
            Parent rideDetailRoot = rideDetailPaneLoader.load();
            RideDetailController rideDetail = rideDetailPaneLoader.getController();
            rideDetail.setRide(ride);
            Scene rideDetailScene = new Scene(rideDetailRoot, 500, 500);
            primaryStage.setScene(rideDetailScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToVehicles() {
        try {
            FXMLLoader vehiclesPaneLoader = new FXMLLoader(getClass().getResource("../Pages/vehicles/vehicles.fxml"));
            Parent vehiclesRoot = vehiclesPaneLoader.load();
            Scene vehiclesScene = new Scene(vehiclesRoot, 500, 500);
            primaryStage.setScene(vehiclesScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigateToVehicleDetail(VehicleDto vehicle) {
        try {
            FXMLLoader vehicleDetailPaneLoader = new FXMLLoader(getClass().getResource("../Pages/vehicleDetail/vehicle_detail.fxml"));
            Parent vehicleDetailRoot = vehicleDetailPaneLoader.load();
            VehicleDetailController vehicleDetail = vehicleDetailPaneLoader.getController();
            vehicleDetail.setVehicle(vehicle);
            Scene vehicleDetailScene = new Scene(vehicleDetailRoot, 500, 500);
            primaryStage.setScene(vehicleDetailScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
