package Pages;

import Services.NavigationService;

/**
 * This is a base class for all the controllers used in the app.
 * It implements all the common functionality of a controller
 */
public class ControllerBase {
    protected NavigationService navigationService;

    public ControllerBase(){
        navigationService = new NavigationService();
    }
}
