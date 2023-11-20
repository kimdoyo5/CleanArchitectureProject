package main.java.interface_adapter.player_comparison_add;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import interface_adapter.ViewModel;

public class PlayerComparisonAddViewModel extends ViewModel {

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
