package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the Player Comparison feature.
 * This class serves as the ViewModel in the MVC pattern, managing the state and logic
 * of the player comparison view. It notifies observers about changes in the player
 * comparison state.
 */
public class PlayerComparisonViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Player Comparison View";
    public static final String REMOVE_BUTTON_LABEL = "Remove Player";
    public static final String BACK_BUTTON_LABEL = "Back";

    private PlayerComparisonState state = new PlayerComparisonState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a PlayerComparisonViewModel.
     * Initializes the view model with the unique identifier "playerComparison".
     */
    public PlayerComparisonViewModel() {
        super("player comparison");
    }

    /**
     * Sets the state of the player comparison.
     * Updates the state of the player comparison and notifies observers of the change.
     *
     * @param state The new state of the player comparison.
     */
    public void setState(PlayerComparisonState state) {
        this.state = state;
    }

    /**
     * Notifies observers about a change in the player comparison state.
     * Fires a property change event to update observers about changes in the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener.
     * Registers a new listener to be notified about changes in the player comparison state.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the player comparison.
     *
     * @return The current PlayerComparisonState.
     */
    public PlayerComparisonState getState() {
        return state;
    }
}
