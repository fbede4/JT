package Pages.rideDetail;

import Dtos.RideDto;
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
        this.driverName.setText(ride.driverName);
        this.cost.setText(String.valueOf(ride.cost) + " HUF");
    }

    @FXML
    private Label destination;
    @FXML
    private Label driverName;
    @FXML
    private Label cost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public RideDetailController(){
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        navigationService.navigateToRides();
    }
}
