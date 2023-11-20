package main.java.interface_adapter.id_search;
import interface_adapter.ViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class IDSearchViewModel extends ViewModel{
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
