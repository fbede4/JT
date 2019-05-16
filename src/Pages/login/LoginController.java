package Pages.login;

import Helpers.HttpClient;
import Helpers.SQLiteHandler;
import Helpers.Settings;
import Helpers.User;
import Pages.ControllerBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controller is responsible for handling business logic
 * of the login page
 */
public class LoginController extends ControllerBase implements Initializable {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Text actiontarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String email = this.emailField.getText();
        String password = this.passwordField.getText();

        if(email.isEmpty() || password.isEmpty()){
            this.actiontarget.setText("You have to fill all fields!");
            return;
        }

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);

        String res = HttpClient.post(Settings.getAzureBaseUrl() + "/api/account/login", null, json);
        if(res != "") {
            JSONObject jsonOb = new JSONObject(res);
            User.setAccessToken(jsonOb.getString("accessToken"));
            SQLiteHandler.insertOrUpdateToken(User.getAccessToken());
            Settings.setAuthProperty(User.getAccessToken());
            navigationService.navigateToRides();
        } else {
            this.actiontarget.setText("Invalid login attempt");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        navigationService.navigateToRegister();
    }
}
