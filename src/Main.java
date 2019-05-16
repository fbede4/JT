import Helpers.SQLiteHandler;
import Helpers.Settings;
import Helpers.User;
import Services.NavigationService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    /**
     * This method is responsible for bootstraping the application
     * Creates the local db if it not yet exists
     * Navigates to login or rides page depending on
     * saved user data
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        SQLiteHandler.createLocalTablesIfNotExist();

        NavigationService.setStage(primaryStage);
        NavigationService navService = new NavigationService();

        String token = Settings.getAuthToken();
        if(token != null){
            User.setAccessToken(token);
            navService.navigateToRides();
        } else {
            navService.navigateToLogin();
        }

        primaryStage.setTitle("Taxee");

        primaryStage.show();
    }

    /**
     * The progream implements an application that
     * allows users to handle their ride and vehicle datas
     *
     * @author  Bede Fülöp
     * @version 1.0
     * @since   2019-04-14
     */
    public static void main(String[] args) {
        launch(args);
    }
}
