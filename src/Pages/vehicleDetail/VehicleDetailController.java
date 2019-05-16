package Pages.vehicleDetail;

import Dtos.VehicleDto;
import Helpers.HttpClient;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller is responsible for handling business logic
 * of the vehicle detail page
 */
public class VehicleDetailController extends ControllerBase implements Initializable {

    private VehicleDto vehicle;

    @FXML
    private Label brand;
    @FXML
    private Label model;
    @FXML
    private Label yom;
    @FXML
    private Label errorText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);
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
        this.model.setText(vehicle.model);
        this.yom.setText(String.valueOf(vehicle.yearOfProduction));
    }

    public void handleEditButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToVehicleEdit(this.vehicle);
    }

    public void handleDeleteButtonAction(ActionEvent actionEvent) {
        String result = HttpClient.post(Settings.getAzureBaseUrl() + "/api/Vehicles/DeleteVehicle/" + this.vehicle.id, User.getAccessToken(), "{}");
        if(result == "") {
            errorText.setVisible(true);
            errorText.setText("Delete failed!");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Vehicle deleted!");
            alert.showAndWait();
            navigationService.navigateToVehicles();
        }
    }
}
