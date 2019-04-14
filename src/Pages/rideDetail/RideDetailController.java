package Pages.rideDetail;

import Dtos.RideDto;
import Helpers.HttpClient;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RideDetailController extends ControllerBase implements Initializable {

    private RideDto ride;
    public void setRide(RideDto ride){
        this.ride = ride;
        this.destination.setText(ride.destination);
        this.driverName.setText(ride.passengerName);
        this.cost.setText((ride.cost) + " HUF");
        this.pickUpLocation.setText(ride.pickUpLocation);
        this.startTime.setText(String.valueOf(ride.startTime));
        this.endTime.setText(String.valueOf(ride.endTime));
    }

    @FXML
    private Label destination;
    @FXML
    private Label driverName;
    @FXML
    private Label cost;
    @FXML
    private Label pickUpLocation;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public RideDetailController(){
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        navigationService.navigateToRides();
    }

    public void handleDeleteButtonAction(ActionEvent actionEvent) {
        HttpClient.post(Settings.getAzureBaseUrl() + "/api/Rides/DeleteRide/" + this.ride.id, User.getAccessToken(), "{}");
        navigationService.navigateToRides();
    }
}
