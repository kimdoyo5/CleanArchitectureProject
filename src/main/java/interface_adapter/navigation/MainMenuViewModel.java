package main.java.interface_adapter.navigation;

import main.java.interface_adapter.ViewModel;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuViewModel extends ViewModel {
    private MainMenuState state = new MainMenuState();
    public static final String PLAYER_COMPARISON_BUTTON_LABEL = "Compare two Players";
    public static final String ID_SEARCH_BUTTON_LABEL = "Search by Player ID";
    public static final String PLAYER_SEARCH_BUTTON_LABEL = "Search by Player Name";

    public MainMenuViewModel() {
        super("navigation");
    }

    public void setState(MainMenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MainMenuState getState() {
        return state;
    }
}
