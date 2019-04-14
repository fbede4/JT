import Services.NavigationService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        NavigationService.setStage(primaryStage);
        NavigationService navService = new NavigationService();
        navService.navigateToLogin();

        primaryStage.setTitle("Taxee");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
