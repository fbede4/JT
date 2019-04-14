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
