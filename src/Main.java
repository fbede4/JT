import Helpers.SQLiteHandler;
import Helpers.User;
import Services.NavigationService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        SQLiteHandler.createLocalTablesIfNotExist();

        NavigationService.setStage(primaryStage);
        NavigationService navService = new NavigationService();

        String token = SQLiteHandler.getToken();
        if(token != ""){
            User.setAccessToken(token);
            navService.navigateToRides();
        } else {
            navService.navigateToLogin();
        }

        primaryStage.setTitle("Taxee");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
