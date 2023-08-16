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
    
    public ShowPlayerData(){
        showPlayerDataMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void addComponentsToFrame(){
        showPlayerDataMenu.add(playerDisplayPane);
        showPlayerDataMenu.add(returnButton);
    }
    
    public static void frameSettings(){
        showPlayerDataMenu.setSize(800, 600);
        showPlayerDataMenu.setLayout(new GridLayout(2,1));
        showPlayerDataMenu.setVisible(true);
        showPlayerDataMenu.setLocationRelativeTo(null);
        showPlayerDataMenu.setResizable(false);
        showPlayerDataMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
    }
    
    public static void initializeTables(){
        playerDisplay = new JTable(Player.array,Player.playerTable);
        playerDisplay.setBounds(100, 50, 600, 350);
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

        playerDisplay.setModel(tableModel);
    }
    
    public static void returnToMenu(){
        showPlayerDataMenu.dispose();
        new MainMenu();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
