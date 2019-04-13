package Pages.vehicleCreateEdit;

import Dtos.VehicleDto;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class VehicleCreateEditController extends ControllerBase implements Initializable {

    private VehicleDto vehicle;

    @FXML private TextField brand;
    @FXML private TextField model;
    @FXML private TextField yom;
    @FXML private TextField type;
    @FXML private Text message;

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
        brand.setText(vehicle.brand);
        model.setText(vehicle.model);
        yom.setText(String.valueOf(vehicle.yearOfProduction));
        type.setText(vehicle.typeOfCar);
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToVehicleDetail(this.vehicle);
    }
}
