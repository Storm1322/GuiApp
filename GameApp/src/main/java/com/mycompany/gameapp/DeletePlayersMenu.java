package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.DefaultTableModel;

public class DeletePlayersMenu extends JFrame implements ActionListener{
    static JFrame deletePlayerMenu;
    static JButton returnButton, deleteButton;
    static JTable playerDisplay;
    static JScrollPane playerDisplayPane;
    GridBagConstraints constraints;
    
    public DeletePlayersMenu(){
        constraints = new GridBagConstraints();
        deletePlayerMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeTables();
    }
    
    public void initializeButtons(){
        returnButton = new JButton("Return <=");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        returnButton.addActionListener(e -> returnToMenu());
        deletePlayerMenu.add(returnButton, constraints);
        deleteButton = new JButton("delete");
        constraints.gridx = 2;
        constraints.gridy = 2;
        deleteButton.addActionListener(e -> confirmDeletion());
        deletePlayerMenu.add(deleteButton, constraints);
    }
    
    public void initializeTables(){
        playerDisplay = new JTable(Player.array,Player.playerTable);
        playerDisplayPane = new JScrollPane(playerDisplay);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Player.array, Player.playerTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        playerDisplay.setModel(tableModel);
        deletePlayerMenu.add(playerDisplayPane, constraints);
    }
    
    public static void addComponentsToFrame(){
        deletePlayerMenu.add(returnButton);
        deletePlayerMenu.add(playerDisplayPane);
        deletePlayerMenu.add(deleteButton);       
    }
    
    public static void frameSettings(){
        deletePlayerMenu.setSize(800, 600);
        deletePlayerMenu.setLayout(new GridBagLayout());
        deletePlayerMenu.setVisible(true);
        deletePlayerMenu.setLocationRelativeTo(null);
        deletePlayerMenu.setResizable(false);
        deletePlayerMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        deletePlayerMenu.dispose();
        new MainMenu();
    }
    
    public static void confirmDeletion(){
        int answer = showConfirmDialog(null, "Are you sure?", "Select an option...", JOptionPane.YES_NO_OPTION);
        if (answer == 0){
            int[] rowsToRemove = playerDisplay.getSelectedRows();
            for(int i = rowsToRemove.length - 1; i >= 0; i--){
                ((DefaultTableModel)playerDisplay.getModel()).removeRow(rowsToRemove[i]);
                Player.players.remove(rowsToRemove[i]);
            }
            Player.storePlayers();
            Player.importPlayers();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
