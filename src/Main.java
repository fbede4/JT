import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;

public class Main extends Application {

    Stage window;
    Scene loginScene, menuScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        FXMLLoader loginPaneLoader = new FXMLLoader(getClass().getResource("login/login.fxml"));
        Parent loginRoot = loginPaneLoader.load();
        FXMLLoader ridesPaneLoader = new FXMLLoader(getClass().getResource("rides/rides.fxml"));
        Parent ridesRoot = ridesPaneLoader.load();

        Scene loginScene = new Scene(loginRoot, 300, 275);
        Scene ridesScene = new Scene(ridesRoot, 300, 275);
        LoginController loginController = (LoginController) loginPaneLoader.getController();
        loginController.setNavigateScene(ridesScene);

        primaryStage.setTitle("Taxee");
        primaryStage.setScene(loginScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
