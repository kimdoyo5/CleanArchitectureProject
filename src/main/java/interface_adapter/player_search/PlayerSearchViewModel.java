package main.java.interface_adapter.player_search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class PlayerSearchViewModel extends ViewModel {
    private PlayerSearchState state = new PlayerSearchState();

    public PlayerSearchViewModel() {
        super("player search");
    }

    public void setState(PlayerSearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayerSearchState getState() {
        return state;
    }
}
