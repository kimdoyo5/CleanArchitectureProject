package main.java.interface_adapter.player_comparison_remove;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class PlayerComparisonRemoveViewModel extends ViewModel{

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private PlayerComparisonRemoveState state = new PlayerComparisonRemoveState();
    public PlayerComparisonRemoveViewModel(){
        super("player_comparison_remove");
    }

    public void setState(PlayerComparisonRemoveState state) {
        this.state = state;
    }
    public void firePropertyChanged(){
        support.firePropertyChange("player_comparison_remove", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayerComparisonRemoveState getState() {
        return state;
    }

}
