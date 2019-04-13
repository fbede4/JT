package login;

import Dtos.LoginDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Text actiontarget;

    private Scene navigateScene;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        actiontarget.setText("Sign in button pressed");

        LoginDto dto = new LoginDto(email, password);
        // send post...

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(navigateScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setNavigateScene(Scene scene) {
        this.navigateScene = scene;
    }
}
