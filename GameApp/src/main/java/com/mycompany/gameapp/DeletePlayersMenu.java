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
    
    public DeletePlayersMenu(){
        deletePlayerMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
        deleteButton = new JButton("delete");
        deleteButton.setBounds(325, 450, 150, 50);
        deleteButton.addActionListener(e -> confirmDeletion());
    }
    
    public static void initializeTables(){
        playerDisplay = new JTable(Player.array,Player.playerTable);
        playerDisplay.setBounds(0, 0, 800, 450);
        playerDisplayPane = new JScrollPane(playerDisplay);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Player.array, Player.playerTable) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        playerDisplay.setModel(tableModel);
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
            int rowToRemove = playerDisplay.getSelectedRow();
            ((DefaultTableModel)playerDisplay.getModel()).removeRow(rowToRemove);
            Player.players.remove(rowToRemove);
            Player.storePlayers();
            Player.importPlayers();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
