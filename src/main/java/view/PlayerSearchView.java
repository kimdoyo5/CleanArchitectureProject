package main.java.view;


import main.java.interface_adapter.player_search.PlayerSearchController;
import main.java.interface_adapter.player_search.PlayerSearchState;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * This class is the view of the player search use case
 * This view contains a search bar, search button, and back button
 */
public class PlayerSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "player search";
    private final PlayerSearchViewModel playerSearchViewModel;
    private final JTextField queryInputField = new JTextField(15);
    private final JLabel result = new JLabel("");
    private final JLabel label = new JLabel("Enter player Id");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final PlayerSearchController playerSearchController;
    private final JButton playerSearch;
    private final JButton back;

    /**
     * Initializing PlayerSearchView
     * @param playerSearchController this controller for the player search use case
     * @param playerSearchViewModel the view model for player search controller
     */
    public PlayerSearchView(PlayerSearchController playerSearchController, PlayerSearchViewModel playerSearchViewModel){
        this.playerSearchController = playerSearchController;
        this.playerSearchViewModel = playerSearchViewModel;
        this.playerSearchViewModel.addPropertyChangeListener(this);



        JLabel title = new JLabel(playerSearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerSearch = new JButton(playerSearchViewModel.SEARCH_LABEL);
        playerSearch.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playerSearch)){
                            PlayerSearchState currentstate = playerSearchViewModel.getSearchState();
                            try{
                                playerSearchController.execute(Integer.parseInt(currentstate.getSearch()));
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "An error occured. Please try again.");
                            }
                        }
                    }
                }
        );

        back = new JButton(playerSearchViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(
                new ActionListener() {
                    /**
                     * Detects if the back button is pressed
                     * @param e the action that is performed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            playerSearchController.execute();
                        }
                    }
                }
        );
        queryInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if ("1234567890".contains(String.valueOf(e.getKeyChar()))) {
                            PlayerSearchState currentState = playerSearchViewModel.getSearchState();
                            String text = queryInputField.getText() + e.getKeyChar();
                            currentState.setSearch(text);
                            playerSearchViewModel.setSearchState(currentState);
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER){
                            PlayerSearchState currentstate = playerSearchViewModel.getSearchState();
                            try{
                                playerSearchController.execute(Integer.parseInt(currentstate.getSearch()));
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "An error occured. Please try again.");
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(queryInputField)
                                .addComponent(result)
                                .addComponent(buffer)
                                .addComponent(label))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(back)
                                .addComponent(playerSearch))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(queryInputField)
                                                .addComponent(playerSearch))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(result))
                                )
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(buffer)
                                .addComponent(back)
                        )

        );
    }

    /**
     * The event that is performed by user
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    /**
     * Change to property of the view
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PlayerSearchState currentState = playerSearchViewModel.getSearchState();
        if (currentState.getSearch_error() != null) {
            JOptionPane.showMessageDialog(this, currentState.getSearch_error());
        }
    }

}
