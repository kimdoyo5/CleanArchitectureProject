package main.java.view;

import main.java.entity.Player;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison_add.PlayerComparisonAddController;
import main.java.interface_adapter.player_comparison_remove.PlayerComparisonRemoveController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PlayerComparisonView extends JPanel {

    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final PlayerComparisonRemoveController playerComparisonRemoveController;
    private final JTable comparisonTable;
    private final DefaultTableModel comparisonTableModel;
    private final JList<String> playerListDisplay;
    private final DefaultListModel<String> playerListModel;

    public PlayerComparisonView(PlayerComparisonController comparisonController,
                                PlayerComparisonRemoveController removeController,
                                PlayerComparisonViewModel comparisonViewModel) {
        this.playerComparisonRemoveController = removeController;
        this.playerComparisonViewModel = comparisonViewModel;

        setLayout(new BorderLayout());
        playerListModel = new DefaultListModel<>();
        playerListDisplay = new JList<>(playerListModel);
        add(new JScrollPane(playerListDisplay), BorderLayout.WEST);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.NORTH);

        comparisonTableModel = new DefaultTableModel();
        comparisonTable = new JTable(comparisonTableModel);
        add(new JScrollPane(comparisonTable), BorderLayout.CENTER);

        playerComparisonViewModel.addPropertyChangeListener(evt -> updateView());
    }

    private JPanel createButtonPanel() {
        JButton removeButton = new JButton("Remove Player");
        removeButton.addActionListener(this::removePlayerAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        return buttonPanel;
    }

    private void removePlayerAction(ActionEvent e) {
        // Implement logic to remove a player
    }

    private void updateView() {
        updatePlayerList();
        updateComparisonTable();
    }

    private void updatePlayerList() {
        List<Player> players = playerComparisonViewModel.getState().getPlayers();
        playerListModel.clear();
        players.forEach(player -> playerListModel.addElement(player.getName()));
    }

    private void updateComparisonTable() {
        String[][] data = playerComparisonViewModel.getState().getDataArray();
        if (data != null && data.length > 0) {
            comparisonTableModel.setDataVector(data, data[0]); // First row is column names
        }
    }
}
