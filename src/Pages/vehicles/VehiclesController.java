package Pages.vehicles;

import Components.NiceListView.CellObject;
import Components.NiceListView.NiceCell;
import Dtos.VehicleDto;
import Helpers.HttpClient;
import Helpers.SQLiteHandler;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This controller is responsible for handling business logic
 * of the vehicles list view
 */
public class VehiclesController extends ControllerBase implements Initializable {

    @FXML
    private ListView<CellObject> listView;

    @FXML
    private Label errorText;

    private ObservableList<CellObject> vehicleObservableList;
    private ArrayList<VehicleDto> vehicles;

    public VehiclesController() {
        vehicleObservableList = FXCollections.observableArrayList();
        vehicles = new ArrayList<>();
    }

    /**
     * Initialize list with vehicles
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);
        try {
            getVehicles();
        } catch (IOException e) {
            e.printStackTrace();
            errorText.setText("No data found!");
            errorText.setVisible(true);
        }
        listView.setItems(vehicleObservableList);
        listView.setCellFactory(studentListView -> new NiceCell());
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CellObject>() {
            @Override
            public void changed(ObservableValue<? extends CellObject> observable, CellObject oldValue, CellObject newValue) {
                for(int i = 0; i < vehicles.size(); i++){
                    if(vehicles.get(i).id == newValue.getId()){
                        navigationService.navigateToVehicleDetail(vehicles.get(i));
                        return;
                    }
                }
            }
        });
    }

    /**
     * Fills the list with the vehicles of the user
     * gets the data from the server by defaukt, if there is no connection
     * it gets the data from the local SQLite database
     */
    private void getVehicles() throws IOException {
        String result = HttpClient.get(Settings.getAzureBaseUrl() + "/api/Vehicles/GetVehicles", User.getAccessToken());
        if(result != ""){
            SQLiteHandler.clearVehicles();
            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++){
                ObjectMapper mapper = new ObjectMapper();
                VehicleDto vehicle = mapper.readValue(jsonArray.get(i).toString(), VehicleDto.class);
                vehicles.add(vehicle);
                vehicleObservableList.add(new CellObject(vehicle.brand, vehicle.model, String.valueOf(vehicle.yearOfProduction), vehicle.id));
                SQLiteHandler.insertVehicle(vehicle);
            }
        } else {
            System.out.println("Could not get vehicles from server");
            errorText.setText("Unable to connect to server!");
            errorText.setVisible(true);
            vehicles = SQLiteHandler.getVehicles();
            for(int i = 0; i < vehicles.size(); i++){
                vehicleObservableList.add(new CellObject(vehicles.get(i).brand, vehicles.get(i).model, vehicles.get(i).yearOfProduction + " HUF", vehicles.get(i).id));
            }
        }
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToRides();
    }

    public void handleNewVehicleButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToVehicleCreate();
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToLogin();
    }
}