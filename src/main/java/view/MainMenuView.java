package main.java.view;

import interface_adapter.navigation.NavigationController;
import main.java.interface_adapter.navigation.NavigationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.navigation.NavigationState;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Main Menu";
    private final NavigationViewModel navigationViewModel;
    private final JButton playerComparison;
    private final JButton IDsearch;
    private final JButton playerSearch;

    // add JButtons later
    public MainMenuView(NavigationViewModel navigationViewModel, NavigationController navigationController) {
        this.navigationViewModel = navigationViewModel;
        this.navigationViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        playerComparison = new JButton(NavigationViewModel.PLAYER_COMPARISON_BUTTON_LABEL);
        buttons.add(playerComparison);
        IDsearch = new JButton(NavigationViewModel.ID_SEARCH_BUTTON_LABEL);
        buttons.add(IDsearch);
        playerSearch = new JButton(NavigationViewModel.PLAYER_SEARCH_BUTTON_LABEL);
        buttons.add(playerSearch);

        playerComparison.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playerComparison)) {
                            navigationController.execute("playerComparison");
                        }
                    }
                }
        );

        IDsearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(IDsearch)) {
                            navigationController.execute("IDSearch");
                        }
                    }
                }
        );

        playerSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playerComparison)) {
                            navigationController.execute("playerSearch");
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    // No properties to change??
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
