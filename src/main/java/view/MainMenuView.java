package main.java.view;


import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.id_search.IDSearchViewModel;

import main.java.interface_adapter.navigation.MainMenuViewModel;

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

import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

/**
 * The main menu of the program contains button to access the other use cases
 */
public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "navigation";
    private final MainMenuViewModel mainMenuViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlayerSearchViewModel playerSearchViewModel;
    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final IDSearchViewModel idSearchViewModel;
    private final JButton compare;
    private final JButton IDsearch;
    private final JButton playerSearch;


    /**
     * Constructor of the class
     * @param mainMenuViewModel view model of main menu
     * @param viewManagerModel view model manager that manages the views
     * @param playerSearchViewModel view model for player search
     * @param playerComparisonViewModel view model for player comparison
     * @param idSearchViewModel view model for id search
     * @throws IOException Unable to read the picture file
     */
    // add JButtons later
    public MainMenuView(MainMenuViewModel mainMenuViewModel, ViewManagerModel viewManagerModel, PlayerSearchViewModel playerSearchViewModel, PlayerComparisonViewModel playerComparisonViewModel, IDSearchViewModel idSearchViewModel) throws IOException {
        this.mainMenuViewModel = mainMenuViewModel;
        this.mainMenuViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.playerSearchViewModel = playerSearchViewModel;
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.idSearchViewModel = idSearchViewModel;

        BufferedImage picture = ImageIO.read(new File("baseball.png"));
        JLabel pic = new JLabel(new ImageIcon(picture.getScaledInstance(100,100, Image.SCALE_SMOOTH)));
        pic.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel title = new JLabel("MLB Data Program");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        IDsearch = new JButton(MainMenuViewModel.PLAYER_SEARCH_BUTTON_LABEL);
        buttons.add(IDsearch);
        playerSearch = new JButton(MainMenuViewModel.ID_SEARCH_BUTTON_LABEL);
        buttons.add(playerSearch);
        compare = new JButton(MainMenuViewModel.PLAYER_COMPARISON_BUTTON_LABEL);
        buttons.add(compare);

        compare.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(compare)) {
                            viewManagerModel.setActiveView(playerComparisonViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        IDsearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(IDsearch)) {
                            viewManagerModel.setActiveView(idSearchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        playerSearch.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        if (evt.getSource().equals(playerSearch)) {
                            viewManagerModel.setActiveView(playerSearchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(pic);
    }


    /**
     * Actions performed by the user
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    // No properties to change??
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
