package main.java.interface_adapter.id_search;
import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.id_search.IDSearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class IDSearchViewModel extends ViewModel{
    public static final String TITLE_LABEL = "ID Search View";
    public static final String SEARCH_LABEL = "Enter player name";
    public static final String SEARCH_BUTTON_LABEL = "Get ID";
    public static final String BACK_BUTTON_LABEL = "Back";

    private IDSearchState state = new IDSearchState();

    public IDSearchViewModel() {
        super("id search");
    }

    public void setState(IDSearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public IDSearchState getState() {
        return state;
    }
}
