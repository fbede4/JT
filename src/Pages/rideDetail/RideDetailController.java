package rideDetail;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RideDetailController implements Initializable {
    private String title;
    private String comment;
    private String description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.title = "fewfew";
        this.comment = "few";
        this.description = "fwe";
    }

    public RideDetailController(){
    }

    public String getTitle(){
        return title;
    }

    public String getComment(){
        return comment;
    }

    public String getDescription(){
        return description;
    }
}
