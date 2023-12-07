package main.java.view;

import main.java.entity.Player;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewState;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddState;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddViewModel;
import main.java.interface_adapter.player_search.PlayerSearchState;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View used to display the data of the player after player search
 * Contains a back button to return to playerSearchView
 */
public class PlayerDataDisplayView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Data Display";
    private final PlayerDataDisplayViewModel playerDataDisplayViewModel;
    private final JLabel result = new JLabel("");
    private final JLabel title = new JLabel("Player Data");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final JButton back;

    private final JButton add;

    private final PlayerSearchViewModel playerSearchViewModel;
    private final ViewManagerModel viewManagerModel;

    private final PlayerComparisonAddController playerComparisonAddController;

    private final PlayerComparisonAddViewModel playerComparisonAddViewModel;

    /**
     * Initialize PLayerDataDisplayeView
     * @param playerDataDisplayViewModel view model of playerDataDisplay which saves the info about the view
     * @param playerSearchViewModel view model of playerSearchView containing info on playerSearchView
     * @param viewManagerModel the manager in charge of switching the views
     * @param playerComparisonAddController this controller for the player comparison add use case
     */
    public PlayerDataDisplayView(PlayerDataDisplayViewModel playerDataDisplayViewModel, PlayerSearchViewModel playerSearchViewModel, ViewManagerModel viewManagerModel, PlayerComparisonAddController playerComparisonAddController, PlayerComparisonAddViewModel playerComparisonAddViewModel){
        this.playerDataDisplayViewModel = playerDataDisplayViewModel;
        this.playerSearchViewModel = playerSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playerComparisonAddController = playerComparisonAddController;
        this.playerComparisonAddViewModel = playerComparisonAddViewModel;

        playerDataDisplayViewModel.addPropertyChangeListener(this);
        playerComparisonAddViewModel.addPropertyChangeListener(this);

        back = new JButton(playerDataDisplayViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            PlayerSearchState currentState = playerSearchViewModel.getSearchState();
                            viewManagerModel.setActiveView(playerSearchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        add = new JButton(playerComparisonAddViewModel.ADD_BUTTON_LABEL);
        add.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(add)){
                            Player searchedPlayer = playerDataDisplayViewModel.getState().getLastSearchedPlayer();
                            playerComparisonAddController.execute(searchedPlayer);
                        }
                    }
                }
        );

        this.setSize(new Dimension(100, 500));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(result)
                                .addComponent(buffer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(back)
                                .addComponent(add))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(result))
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(buffer)
                                .addComponent(back))
                        )
                        .addComponent(add)

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
        String name = evt.getPropertyName();
        if (name.equals("player_comparison_add")){
            PlayerComparisonAddState state = (PlayerComparisonAddState) evt.getNewValue();
            if (state.getPlayerAddError() != null) {
                JOptionPane.showMessageDialog(this, state.getPlayerAddError());
            }else{
                JOptionPane.showMessageDialog(this, "Added " + state.getLastAddedPlayer().getName() + " to player comparison");
            }
        }else{
            PlayerDataDisplayViewState currentState = playerDataDisplayViewModel.getState();
            result.setText(currentState.getResult());
        }
    }
}
