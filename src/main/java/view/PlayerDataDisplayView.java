package main.java.view;

import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewModel;
import main.java.interface_adapter.PlayerDataDisplay.PlayerDataDisplayViewState;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.player_search.PlayerSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerDataDisplayView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Data Display";
    private final PlayerDataDisplayViewModel playerDataDisplayViewModel;
    private final JLabel result = new JLabel("");
    private final JLabel title = new JLabel("Player Data");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final JButton back;

    private final PlayerSearchViewModel playerSearchViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlayerDataDisplayView(PlayerDataDisplayViewModel playerDataDisplayViewModel, PlayerSearchViewModel playerSearchViewModel, ViewManagerModel viewManagerModel){
        this.playerDataDisplayViewModel = playerDataDisplayViewModel;
        this.playerSearchViewModel = playerSearchViewModel;
        this.viewManagerModel = viewManagerModel;

        playerDataDisplayViewModel.addPropertyChangeListener(this);

        back = new JButton(playerDataDisplayViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)){
                            viewManagerModel.setActiveView(playerSearchViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setPreferredSize(new Dimension(100, 500));
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
                                .addComponent(back))
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

        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PlayerDataDisplayViewState currentState = playerDataDisplayViewModel.getState();
        result.setText(currentState.getResult());
    }
}
