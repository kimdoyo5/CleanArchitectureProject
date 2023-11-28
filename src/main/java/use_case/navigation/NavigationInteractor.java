package main.java.use_case.navigation;

public class NavigationInteractor implements NavigationInputBoundary {
    final NavigationOutputBoundary navigationPresenter;

    public NavigationInteractor(NavigationOutputBoundary navigationOutputBoundary) {
        this.navigationPresenter = navigationOutputBoundary;
    }

    @Override
    public void execute(String source) {
        //should prepare different views depending on button ):
        if (source.equals("playerComparison")){
            navigationPresenter.preparePlayerComparisonView();
        }
        else if (source.equals("playerSearch")){
            navigationPresenter.preparePlayerSearchView();
        }
        else {
            navigationPresenter.prepareIDSearchView();
        }

    }
}
