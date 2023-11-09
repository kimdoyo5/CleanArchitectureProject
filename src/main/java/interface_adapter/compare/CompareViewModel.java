package main.java.interface_adapter.compare;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CompareViewModel extends ViewModel {
    private CompareState state = new CompareState();

    public CompareViewModel() {
        super("compare");
    }

    public void setState(CompareState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CompareState getState() {
        return state;
    }
}
