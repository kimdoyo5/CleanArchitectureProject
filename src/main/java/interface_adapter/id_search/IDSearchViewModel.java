package main.java.interface_adapter.id_search;

import main.java.interface_adapter.ViewModel;

import main.java.interface_adapter.id_search.IDSearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model for IDSearch, gives the functionality showing what has been
 * searched and what the results of the search was. Can be updated since it
 * extends ViewModel which means it must implement firePropertyChanged()
 */
public class IDSearchViewModel extends ViewModel{
    public static final String TITLE_LABEL = "ID Search View";
    public static final String SEARCH_LABEL = "Enter player name";
    public static final String SEARCH_BUTTON_LABEL = "Get ID";
    public static final String BACK_BUTTON_LABEL = "Back";

    private IDSearchState state = new IDSearchState();

    /**
     * The constructor, all attributes are set to final default values
     */
    public IDSearchViewModel() {
        super("id search");
    }

    /**
     * Allows for the state to be changed to show new data
     * @param state the state to change to
     */
    public void setState(IDSearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Implementation of superclass, allows for the update of values in the view
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Allows for propertyChangeListeners to be added to the view so that
     * data can be taken in from the text field and buttons
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gives the current state
     * @return the current state in the view
     */
    public IDSearchState getState() {
        return state;
    }
}
