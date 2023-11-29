package main.java.interface_adapter.player_comparison;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerComparisonViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Player Comparison View";
    public static final String ADD_BUTTON_LABEL = "Add Player";
    public static final String COMPARISON_BUTTON_LABEL = "Compare";
    public static final String BACK_BUTTON_LABEL = "Back";
    private PlayerComparisonState state = new PlayerComparisonState();

    public PlayerComparisonViewModel() {
        super("playerComparison");
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

    public PlayerComparisonState getState() {
        return state;
    }
}