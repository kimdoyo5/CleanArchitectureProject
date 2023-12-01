package main.java.interface_adapter.player_search;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model of the player search use case
 * Contains the specifications of the view
 */
public class PlayerSearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Player Search View";
    public static final String SEARCH_LABEL = "Enter player Id";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String BACK_BUTTON_LABEL = "Back";

    private PlayerSearchState searchState = new PlayerSearchState();

    /**
     * Constructs the class
     */
    public PlayerSearchViewModel(){
        super("player search");
    }

    /**
     * Set the state of the view
     * @param searchState The state of the view
     */
    public void setSearchState(PlayerSearchState searchState){this.searchState = searchState;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Changes the view based on the state
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.searchState);
    }

    /**\
     * Adds listener for property change
     * @param listener The listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Get the current state
     * @return The current state of the view
     */
    public PlayerSearchState getSearchState(){return searchState;}

}
