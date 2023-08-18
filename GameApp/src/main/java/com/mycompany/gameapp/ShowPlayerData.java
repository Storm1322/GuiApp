package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ShowPlayerData extends JFrame implements ActionListener{
    static JFrame showPlayerDataMenu;
    static JTable playerDisplay;
    static JButton returnButton;
    static JScrollPane playerDisplayPane;
    GridBagConstraints constraints;
    
    public ShowPlayerData(){
        constraints = new GridBagConstraints();
        showPlayerDataMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeTables();
    }
    
    public static void frameSettings(){
        showPlayerDataMenu.setSize(800, 600);
        showPlayerDataMenu.setLayout(new GridBagLayout());
        showPlayerDataMenu.setVisible(true);
        showPlayerDataMenu.setLocationRelativeTo(null);
        showPlayerDataMenu.setResizable(false);
        showPlayerDataMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initializeButtons(){
        returnButton = new JButton("Return <=");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        returnButton.addActionListener(e -> returnToMenu());
        showPlayerDataMenu.add(returnButton, constraints);
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
        showPlayerDataMenu.add(playerDisplayPane, constraints);
    }
    
    public static void returnToMenu(){
        showPlayerDataMenu.dispose();
        new MainMenu();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
