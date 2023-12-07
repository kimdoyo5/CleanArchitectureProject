package main.java.view;

import main.java.entity.Player;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewState;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonState;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddState;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveState;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveViewModel;
import main.java.interface_adapter.player_search.PlayerSearchState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * This class is the view of the player comparison use case.
 * This view contains a remove player button, and back button.
 */
public class PlayerComparisonView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "player comparison";
    private final PlayerComparisonController playerComparisonController;
    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final PlayerComparisonRemoveController playerComparisonRemoveController;
    private final PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel;
    private final DefaultTableModel comparisonTableModel;
    //private final DefaultListModel<String>  playerListModel;
    private final JButton back;
    private final JButton remove;

    private final JButton compare;

    /**
     * Initializing PlayerComparisonView
     * @param playerComparisonController the controller for player comparison use case.
     * @param playerComparisonRemoveController the controller for player comparison remove use case.
     * @param playerComparisonViewModel the view model for player comparison controller.
     * @param playerComparisonRemoveViewModel the view model for player comparison remove controller.
     */
    public PlayerComparisonView(PlayerComparisonController playerComparisonController,
                                PlayerComparisonRemoveController playerComparisonRemoveController,
                                PlayerComparisonViewModel playerComparisonViewModel,
                                PlayerComparisonRemoveViewModel playerComparisonRemoveViewModel) {
        this.playerComparisonController = playerComparisonController;
        this.playerComparisonViewModel = playerComparisonViewModel;
        this.playerComparisonRemoveController = playerComparisonRemoveController;
        this.playerComparisonRemoveViewModel = playerComparisonRemoveViewModel;
        playerComparisonViewModel.addPropertyChangeListener(this);
        playerComparisonRemoveViewModel.addPropertyChangeListener(this);

        // Title
        JLabel title = new JLabel(playerComparisonViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Array Layout
        setLayout(new BorderLayout());

        comparisonTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable comparisonTable = new JTable(comparisonTableModel);

        add(new JScrollPane(comparisonTable), BorderLayout.CENTER);


        // Back button
        back = new JButton(playerComparisonViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(
                new ActionListener() {
                    /**
                     * Detects if the back button is pressed
                     * @param e the action that is performed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            playerComparisonController.back();
                        }
                    }
                }
        );

        // Remove button
        remove = new JButton(playerComparisonViewModel.REMOVE_BUTTON_LABEL);
        remove.addActionListener(
                new ActionListener() {
                    /**
                     * Detects if the back button is pressed
                     * @param e the action that is performed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(remove)){
                            playerComparisonRemoveController.execute();
                        }
                    }
                }
        );

        compare = new JButton("Compare");
        compare.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(compare)){
                            playerComparisonController.execute();
                        }
                    }
                }
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane pane2 = new JScrollPane(comparisonTable);
        // Layout configuration
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pane2)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(back)
                                .addComponent(remove)
                                .addComponent(compare))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pane2)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(back)
                                .addComponent(remove)
                                .addComponent(compare))
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
        if(name.equals("player_comparison_remove")){
            PlayerComparisonRemoveState state = (PlayerComparisonRemoveState) evt.getNewValue();
            if (state.getPlayerRemoveError() != null){
                JOptionPane.showMessageDialog(this, state.getPlayerRemoveError());
            }else{
                String message = "Removed players: \n";
                for (String playerName: state.getLastRemovedPlayers()){
                    message = message + playerName + "\n";
                }
                JOptionPane.showMessageDialog(this, message);
                updateView();
            }

        }else{
            PlayerComparisonState currentState = (PlayerComparisonState) evt.getNewValue();
            if (currentState.getPlayerComparisonError() != null) {
                JOptionPane.showMessageDialog(this, currentState.getPlayerComparisonError());
                currentState.setPlayerComparisonError(null);
                playerComparisonViewModel.setState(currentState);
            }else{
                updateView();
            }
        }
    }

    private void updateView() {
        String[][] data = playerComparisonViewModel.getState().getDataArray();
        if (data != null && data.length > 0) {
            comparisonTableModel.setDataVector(data, data[0]); // First row is column names
        }
    }
}