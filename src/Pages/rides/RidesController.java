package Pages.rides;

import Components.NiceListView.CellObject;
import Components.NiceListView.NiceCell;
import Dtos.RideDto;
import Helpers.HttpClient;
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
import javafx.scene.control.ListView;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RidesController extends ControllerBase implements Initializable {

    @FXML
    private ListView<CellObject> listView;

    private ObservableList<CellObject> rideObservableList;
    private ArrayList<RideDto> rides;

    public RidesController() {

        rideObservableList = FXCollections.observableArrayList();
        rides = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getRides();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void getRides() throws IOException {
        String result = HttpClient.get(Settings.getAzureBaseUrl() + "/api/Rides/GetRidesDriver", User.getAccessToken());
        if(result != ""){
            JSONArray jsonArray = new JSONArray(result);
            for(int i = 0; i < jsonArray.length(); i++){
                ObjectMapper mapper = new ObjectMapper();
                RideDto ride = mapper.readValue(jsonArray.get(i).toString(), RideDto.class);
                rides.add(ride);
                rideObservableList.add(new CellObject(ride.destination, ride.driverName, String.valueOf(ride.cost) + " HUF", ride.id));
            }
        } else {
            // todo error...
        }
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToVehicles();
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToLogin();
    }
}