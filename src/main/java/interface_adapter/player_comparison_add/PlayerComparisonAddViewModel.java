package main.java.interface_adapter.player_comparison_add;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerComparisonAddViewModel extends ViewModel {

    public static final String ADD_BUTTON_LABEL = "Add Player to Comparison";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private PlayerComparisonAddState state = new PlayerComparisonAddState();
    public PlayerComparisonAddViewModel(){
        super("player_comparison_add");
    }

    public void setState(PlayerComparisonAddState state) {
        this.state = state;
    }
    public void firePropertyChanged(){
        support.firePropertyChange("player_comparison_add", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayerComparisonAddState getState() {
        return state;
    }

}
