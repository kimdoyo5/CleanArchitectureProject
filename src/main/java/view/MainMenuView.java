package main.java.view;


import main.java.interface_adapter.navigation.NavigationController;

import main.java.interface_adapter.navigation.NavigationViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import main.java.interface_adapter.navigation.NavigationState;


public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "navigation";
    private final NavigationViewModel navigationViewModel;
    private final JButton compare;
    private final JButton IDsearch;
    private final JButton playerSearch;


    // add JButtons later
    public MainMenuView(NavigationViewModel navigationViewModel, NavigationController navigationController) throws IOException {
        this.navigationViewModel = navigationViewModel;
        this.navigationViewModel.addPropertyChangeListener(this);

        BufferedImage picture = ImageIO.read(new File("baseball.png"));
        JLabel pic = new JLabel(new ImageIcon(picture.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
        pic.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = new JLabel("MLB Data Program");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        IDsearch = new JButton(NavigationViewModel.PLAYER_SEARCH_BUTTON_LABEL);
        buttons.add(IDsearch);
        playerSearch = new JButton(NavigationViewModel.ID_SEARCH_BUTTON_LABEL);
        buttons.add(playerSearch);
        compare = new JButton(NavigationViewModel.PLAYER_COMPARISON_BUTTON_LABEL);
        buttons.add(compare);

        compare.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(compare)) {
                            navigationController.execute("compare");
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

                        if (evt.getSource().equals(playerSearch)) {

                            navigationController.execute("playerSearch");
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(pic);
    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    // No properties to change??
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
