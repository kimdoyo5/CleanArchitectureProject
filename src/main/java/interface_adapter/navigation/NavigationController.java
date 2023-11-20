
package main.java.interface_adapter.navigation;

import main.java.use_case.navigation.NavigationInputBoundary;


public class NavigationController {
    final NavigationInputBoundary navigationUseCaseInteractor;
    public NavigationController(NavigationInputBoundary navigationUseCaseInteractor) {
        this.navigationUseCaseInteractor = navigationUseCaseInteractor;
    }


    public void execute(String source) {
        navigationUseCaseInteractor.execute(source);
    }
}
