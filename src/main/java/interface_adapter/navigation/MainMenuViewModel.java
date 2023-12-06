package main.java.interface_adapter.navigation;

import main.java.interface_adapter.ViewModel;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for main menu
 */
public class MainMenuViewModel extends ViewModel {
    private MainMenuState state = new MainMenuState();
    public static final String PLAYER_COMPARISON_BUTTON_LABEL = "Compare players";
    public static final String ID_SEARCH_BUTTON_LABEL = "Search by Player ID";
    public static final String PLAYER_SEARCH_BUTTON_LABEL = "Search by Player Name";

    /**
     * Constructs the class
     */
    public MainMenuViewModel() {
        super("navigation");
    }

    /**
     * Sets the state of the view
     * @param state the state of the view
     */
    public void setState(MainMenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Applies the property changes to the view
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * adds listener for property changes
     * @param listener the listener for property changes
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state
     * @return the current state of the view
     */
    public MainMenuState getState() {
        return state;
    }
}
