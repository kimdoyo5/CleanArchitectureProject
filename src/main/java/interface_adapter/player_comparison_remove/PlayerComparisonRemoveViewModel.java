package main.java.interface_adapter.player_comparison_remove;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for the player comparison remove use case
 * Contains the required information and methods for the view
 */
public class PlayerComparisonRemoveViewModel extends ViewModel{

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private PlayerComparisonRemoveState state = new PlayerComparisonRemoveState();

    /**
     * Constructor for the class
     */
    public PlayerComparisonRemoveViewModel(){
        super("player_comparison_remove");
    }

    /**
     * Sets the state for the view model
     * @param state updated state to be saved by the view model
     */
    public void setState(PlayerComparisonRemoveState state) {
        this.state = state;
    }

    /**
     * Changes the related view based on changes to the state
     */
    public void firePropertyChanged(){
        support.firePropertyChange("player_comparison_remove", null, this.state);
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
    public PlayerComparisonRemoveState getState() {
        return state;
    }

}
