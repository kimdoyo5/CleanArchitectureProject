package interface_adapter.player_comparison;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerComparisonViewModel extends ViewModel {
    private PlayerComparisonState state = new PlayerComparisonState();

    public PlayerComparisonViewModel() {
        super("player search"); // Fix player search
    }

    public void setState(PlayerComparisonState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}