package Pages.vehicleCreateEdit;

import Dtos.VehicleDto;
import Helpers.HttpClient;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehicleCreateEditController extends ControllerBase implements Initializable {

    private VehicleDto vehicle;

    @FXML private TextField brand;
    @FXML private TextField model;
    @FXML private TextField yom;
    @FXML private TextField type;
    @FXML private Text message;
    @FXML private Label title;

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
        brand.setText(vehicle.brand);
        model.setText(vehicle.model);
        yom.setText(String.valueOf(vehicle.yearOfProduction));
        type.setText(vehicle.typeOfCar);
    }

    public void handleSubmitButtonAction(ActionEvent actionEvent) throws IOException {
        if(brand.getText().equals(null) || brand.getText().equals("")
                || model.getText().equals(null) || model.getText().equals("")
                || yom.getText().equals(null) || yom.getText().equals("")
                || type.getText().equals(null) || type.getText().equals("")) {
            this.message.setText("Kitöltetlen mezők!");
            return;
        }

        this.vehicle.brand = brand.getText();
        this.vehicle.model = model.getText();
        this.vehicle.yearOfProduction = Integer.parseInt(yom.getText());
        this.vehicle.typeOfCar = type.getText();

        Gson gson = new Gson();
        String json = gson.toJson(this.vehicle);
        String res = HttpClient.post(Settings.getAzureBaseUrl() + "/api/vehicles/SaveVehicle", User.getAccessToken(), json);
        if(res != ""){
            ObjectMapper mapper = new ObjectMapper();
            setVehicle(mapper.readValue(res, VehicleDto.class));
            message.setText("Sikeres mentés");
        } else {
            message.setText("Mentés sikertelen");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.vehicle = new VehicleDto();

        yom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    yom.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        if(this.title.getText().equals("Create Vehicle")) {
            navigationService.navigateToVehicles();
        } else {
            navigationService.navigateToVehicleDetail(this.vehicle);
        }
    }

    public void setTitle(String title){
        this.title.setText(title);
    }
}
