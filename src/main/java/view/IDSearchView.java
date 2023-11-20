package main.java.view;

import main.java.interface_adapter.id_search.IDSearchController;
import main.java.interface_adapter.id_search.IDSearchViewModel;
import main.java.interface_adapter.id_search.IDSearchState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class IDSearchView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "id search";
    private final IDSearchViewModel idSearchViewModel;
    private final JTextField queryInputField = new JTextField(15);
    private final JLabel result = new JLabel("");
    private final JLabel label = new JLabel("Enter a player name");
    private final JLabel buffer = new JLabel("<html><body><br><br></body></html>");
    private final IDSearchController idSearchController;
    private final JButton search;
    private final JButton back;

    public IDSearchView(IDSearchController controller, IDSearchViewModel idSearchViewModel) {

        this.idSearchController = controller;
        this.idSearchViewModel = idSearchViewModel;
        idSearchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(IDSearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        search = new JButton(IDSearchViewModel.SEARCH_BUTTON_LABEL);
        back = new JButton(IDSearchViewModel.BACK_BUTTON_LABEL);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            IDSearchState currentState = idSearchViewModel.getState();

                            try{
                                idSearchController.execute(currentState.getQuery());
                            }
                            catch (IOException e){
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
                            idSearchController.execute();
                        }

                    }
                }
        );

        queryInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        IDSearchState currentState = idSearchViewModel.getState();
                        String text = queryInputField.getText() + e.getKeyChar();
                        currentState.setQuery(text);
                        idSearchViewModel.setState(currentState);
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
                                .addComponent(search))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(queryInputField)
                                                .addComponent(search))
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
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        IDSearchState state = (IDSearchState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
        }
        result.setText(state.getResult());
    }

}
