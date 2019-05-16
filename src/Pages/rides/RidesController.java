package Pages.rides;

import Components.NiceListView.CellObject;
import Components.NiceListView.NiceCell;
import Dtos.RideDto;
import Helpers.HttpClient;
import Helpers.SQLiteHandler;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import java.util.Date;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This controller is responsible for handling business logic
 * of the rides list page
 */
public class RidesController extends ControllerBase implements Initializable {

    @FXML
    private ListView<CellObject> listView;

    @FXML
    private Label errorText;

    private ObservableList<CellObject> rideObservableList;
    private ArrayList<RideDto> rides;

    public RidesController() {
        rideObservableList = FXCollections.observableArrayList();
        rides = new ArrayList<>();
    }

    /**
     * Initialize list with rides
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorText.setVisible(false);

        getRides();

        listView.setItems(rideObservableList);
        listView.setCellFactory(studentListView -> new NiceCell());
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CellObject>() {
            @Override
            public void changed(ObservableValue<? extends CellObject> observable, CellObject oldValue, CellObject newValue) {
                for(int i = 0; i < rides.size(); i++){
                    if(rides.get(i).id == newValue.getId()){
                        navigationService.navigateToRidesDetail(rides.get(i));
                        return;
                    }
                }
            }
        });
    }

    /**
     * Fills the list with the rides of the user
     * gets the data from the server by defaukt, if there is no connection
     * it gets the data from the local SQLite database
     */
    private void getRides() {
        String result = HttpClient.get(Settings.getAzureBaseUrl() + "/api/Rides/GetRidesDriverJv", User.getAccessToken());

        if(result != ""){
            SQLiteHandler.clearRides();
            JSONArray jsonArray = new JSONArray(result);
            try {
                for(int i = 0; i < jsonArray.length(); i++){
                    ObjectMapper mapper = new ObjectMapper();
                    RideDto ride = mapper.readValue(jsonArray.get(i).toString(), RideDto.class);
                    rides.add(ride);
                    rideObservableList.add(new CellObject(ride.destination, ride.passengerName, ride.cost + " HUF", ride.id));
                    SQLiteHandler.insertRide(ride);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                errorText.setVisible(true);
                errorText.setText("Could not process server response!");
            }
        } else {
            System.out.println("Could not get rides from server");
            errorText.setText("Unable to connect to server!");
            errorText.setVisible(true);
            rides = SQLiteHandler.getRides();
            for(int i = 0; i < rides.size(); i++){
                rideObservableList.add(new CellObject(rides.get(i).destination, rides.get(i).passengerName, rides.get(i).cost + " HUF", rides.get(i).id));
            }
        }
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToVehicles();
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToLogin();
    }

    /**
     * New rides can only be created from the taxee app
     * therefore this button creates dummy rides for testing
     * and showing the functionality
     */
    public void handleAddDummyRidesButton(ActionEvent actionEvent) {
        boolean success = true;
        for(int i = 0; i < 10; i++) {
            RideDto ride = new RideDto();
            ride.id = 0;
            ride.startTime = new Date();
            ride.endTime = new Date();
            ride.pickUpLocation = "Budapest, Bocskai ut 77-79.";
            ride.destination = "Budakeszi, Barackvirag utca 35.";
            ride.passengerName = "passenger 1";
            ride.cost = 500;
            ride.distance = 12;

            Gson gson = new Gson();
            String json = gson.toJson(ride);
            if (HttpClient.post(Settings.getAzureBaseUrl() + "/api/Rides/SaveRide", User.getAccessToken(), json) == "") {
                errorText.setVisible(true);
                errorText.setText("Error! Could not create rides!");
                success = false;
                break;
            }
        }

        if(success){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Dummy rides created!");
            alert.showAndWait();
        }

        getRides();
    }
}