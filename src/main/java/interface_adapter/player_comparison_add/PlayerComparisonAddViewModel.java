package main.java.interface_adapter.player_comparison_add;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for the player comparison add use case
 * Contains the required information and methods for the view
 */
public class PlayerComparisonAddViewModel extends ViewModel {

    public static final String ADD_BUTTON_LABEL = "Add Player to Comparison";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private PlayerComparisonAddState state = new PlayerComparisonAddState();

    /**
     * Constructor for the class
     */
    public PlayerComparisonAddViewModel(){
        super("player_comparison_add");
    }

    /**
     * Sets the state for the view model
     * @param state updated state to be saved by the view model
     */
    public void setState(PlayerComparisonAddState state) {
        this.state = state;
    }

    /**
     * Changes the related view based on changes to the state
     */
    public void firePropertyChanged(){
        support.firePropertyChange("player_comparison_add", null, this.state);
    }

    /**
     * Adds a listener to detect changes to the view model
     * @param listener the propertu change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the state connected to the view model
     * @return the state connected to the view model
     */
    public PlayerComparisonAddState getState() {
        return state;
    }

}
