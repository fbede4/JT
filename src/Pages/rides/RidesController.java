package rides;

import Components.NiceListView.CellObject;
import Components.NiceListView.NiceCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class RidesController implements Initializable {

    @FXML
    private ListView<CellObject> listView;

    private ObservableList<CellObject> rideObservableList;

    private Scene navigateScene;
    private Stage stage;

    public RidesController() {

        rideObservableList = FXCollections.observableArrayList();

        //add some Students
        rideObservableList.addAll(
                new CellObject("John Doe", " v vx", " bgrdbgr"),
                new CellObject("John Doe", " vx vx vcx ", " grbgrd bg"),
                new CellObject("John Doe", " bdbgbgr", "b grd bgrd bgrb"),
                new CellObject("John Doe", "r bgrdbgdb gr", "grdbgrd bg"),
                new CellObject("John Doe", "bgrd bg", "rd bgr bgr"),
                new CellObject("John Doe", "rdb grd", " bgrdbg"),
                new CellObject("John Doe", "db grdbgrdbg", "rdb gr bgrdbg")
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listView.setItems(rideObservableList);
        listView.setCellFactory(studentListView -> new NiceCell());
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CellObject>() {
            @Override
            public void changed(ObservableValue<? extends CellObject> observable, CellObject oldValue, CellObject newValue) {
                System.out.println("Selected item: " + newValue.getTitle());
                stage.setScene(navigateScene);
            }
        });
    }

    public void setNavigateScene(Scene scene) {
        this.navigateScene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}