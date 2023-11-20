package main.java.interface_adapter.navigation;
import interface_adapter.ViewModel;
import interface_adapter.navigation.NavigationState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NavigationViewModel extends ViewModel {
    private NavigationState state = new NavigationState();
    public static final String PLAYER_COMPARISON_BUTTON_LABEL = "Compare two Players";
    public static final String ID_SEARCH_BUTTON_LABEL = "Search by Player ID";
    public static final String PLAYER_SEARCH_BUTTON_LABEL = "Search by Player Name";

    public NavigationViewModel() {
        super("navigation");
    }

    public void setState(NavigationState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public NavigationState getState() {
        return state;
    }
}
