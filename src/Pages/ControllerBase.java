package Pages;

import Services.NavigationService;

public class ControllerBase {
    protected NavigationService navigationService;

    public ControllerBase(){
        navigationService = new NavigationService();
    }
}
