package Pages.vehicleDetail;

import Dtos.RideDto;
import Dtos.VehicleDto;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class VehicleDetailController extends ControllerBase implements Initializable {

    private VehicleDto vehicle;

    @FXML
    private Label brand;
    @FXML
    private Label type;
    @FXML
    private Label yom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public VehicleDetailController(){
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        navigationService.navigateToVehicles();
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
        this.brand.setText(vehicle.brand);
        this.type.setText(vehicle.typeOfCar);
        this.yom.setText(String.valueOf(vehicle.yearOfProduction));
    }
}
