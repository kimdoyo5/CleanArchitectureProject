package main.java.interface_adapter.PlayerDataDisplay;

import main.java.interface_adapter.ViewModel;
import main.java.interface_adapter.player_search.PlayerSearchState;
import main.java.view.PlayerDataDisplayView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerDataDisplayViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Data Display View";
    public static final String BACK_BUTTON_LABEL = "Back";

    private PlayerDataDisplayViewState viewState = new PlayerDataDisplayViewState();
    public PlayerDataDisplayViewModel() {
        super("Data Display");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.viewState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public PlayerDataDisplayViewState getState(){return viewState;}

    public void setViewState(PlayerDataDisplayViewState playerDataDisplayViewState){
        this.viewState = playerDataDisplayViewState;
    }
}
