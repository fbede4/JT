import Pages.rideDetail.RideDetailController;
import Services.NavigationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Pages.login.LoginController;
import Pages.rides.RidesController;

public class Main extends Application {

    Stage window;
    Scene loginScene, menuScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        NavigationService.setStage(primaryStage);
        NavigationService navService = new NavigationService();
        navService.NavigateToLogin();

        primaryStage.setTitle("Taxee");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
