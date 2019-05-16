package Pages.register;

import Helpers.HttpClient;
import Helpers.SQLiteHandler;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller is responsible for handling business logic
 * of the register page
 */
public class RegisterController extends ControllerBase implements Initializable {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();
        String confirmPassword = this.confirmPasswordField.getText();

        if(email.isEmpty() || password.isEmpty()){
            this.actiontarget.setText("You have to fill all fields!");
            return;
        }

        if(!confirmPassword.equals(password)){
            this.actiontarget.setText("Passwords don't match");
            return;
        }

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        json.put("isDriver", true);
        json.put("name", email);
        json.put("city", email);

        String res = HttpClient.post(Settings.getAzureBaseUrl() + "/api/account/register", "", json);
        if(res != "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Registration successful");
            alert.showAndWait();
            navigationService.navigateToLogin();
        } else {
            this.actiontarget.setText("Registration failed");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleBackButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToLogin();
    }
}
