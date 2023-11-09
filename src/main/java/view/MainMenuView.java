package main.java.view;

import interface_adapter.navigation.NavigationController;
import main.java.interface_adapter.navigation.NavigationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Main Menu";
    private final NavigationViewModel navigationViewModel;

    // add JButtons later
    public MainMenuView(NavigationViewModel navigationViewModel, NavigationController navigationController) {
        this.navigationViewModel = navigationViewModel;
        // this.navigationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.setLayout(new GridLayout());

        this.add(title);
    }

   /*
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }
    */

}
