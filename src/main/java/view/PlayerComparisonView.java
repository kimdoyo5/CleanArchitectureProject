package main.java.view;

import main.java.interface_adapter.id_search.IDSearchController;
import main.java.interface_adapter.id_search.IDSearchState;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonController;
import main.java.interface_adapter.player_comparison.PlayerComparisonViewModel;
import main.java.interface_adapter.player_comparison.PlayerComparisonState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class PlayerComparisonView extends JPanel implements ActionListener {

    public final String viewName = "player comparison";
    private final PlayerComparisonViewModel playerComparisonViewModel;
    private final JTextField queryInputField = new JTextField(15);
    private final JLabel result = new JLabel("");
    private final JLabel label1 = new JLabel("Enter player 1 name:");
    private final JLabel label2 = new JLabel("Enter player 2 name:");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final PlayerComparisonController playerComparisonController;
    private final JButton playerComparison;
    private final JButton back;

    public PlayerComparisonView(PlayerComparisonController playerComparisonController, PlayerComparisonViewModel playerComparisonViewModel) {

        this.playerComparisonController = playerComparisonController;
        this.playerComparisonViewModel = playerComparisonViewModel;

        playerComparisonViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(PlayerComparisonViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerComparison = new JButton(PlayerComparisonViewModel.COMPARISON_BUTTON_LABEL);
        back = new JButton(PlayerComparisonViewModel.BACK_BUTTON_LABEL);

        playerComparison.addActionListener(

                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playerComparison)) {
                            PlayerComparisonState currentState = playerComparisonViewModel.getState();
                            try {
                                playerComparisonController.execute(currentState.getQuery());
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "An error occured. Please try again.");
                            }
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            playerComparisonController.execute();
                        }
                    }
                }
        );

        queryInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlayerComparisonState currentState = playerComparisonViewModel.getState();
                        String text = queryInputField.getText() + e.getKeyChar();
                        currentState.setQuery(text);
                        playerComparisonViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
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
                                .addComponent(playerComparison))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(queryInputField)
                                                .addComponent(playerComparison))
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

}
