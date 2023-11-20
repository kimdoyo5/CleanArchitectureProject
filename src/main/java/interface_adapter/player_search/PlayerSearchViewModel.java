package main.java.interface_adapter.player_search;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayerSearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Player Search View";
    public static final String SEARCH_LABEL = "Enter player Id";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String BACK_BUTTON_LABEL = "Back";

    private PlayerSearchState searchState = new PlayerSearchState();

    PlayerSearchViewModel(){
        super("Player Search");
    }

    public void setSearchState(PlayerSearchState searchState){this.searchState = searchState;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.searchState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayerSearchState getSearchState(){return searchState;}
}
