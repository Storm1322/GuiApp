package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ShowTournamentData extends JFrame implements ActionListener{
    static JFrame showTournamentDataMenu;
    static JTable tournamentDisplay;
    static JButton returnButton;
    static JScrollPane tournamentDisplayPane;
    
    public ShowTournamentData() {
        showTournamentDataMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
    }
    
    public static void initializeTables(){
        tournamentDisplay = new JTable(Tournament.tournamentArray, Tournament.tournamentHeadline);
        tournamentDisplay.setBounds(100, 50, 600, 350);
        tournamentDisplayPane = new JScrollPane(tournamentDisplay);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Tournament.tournamentArray, Tournament.tournamentHeadline) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tournamentDisplay.setModel(tableModel);
    }
    
    public static void addComponentsToFrame(){
        showTournamentDataMenu.add(tournamentDisplayPane);
        showTournamentDataMenu.add(returnButton);
    }
    
    public static void frameSettings(){
        showTournamentDataMenu.setSize(800, 600);
        showTournamentDataMenu.setLayout(new GridBagLayout());
        showTournamentDataMenu.setVisible(true);
        showTournamentDataMenu.setLocationRelativeTo(null);
        showTournamentDataMenu.setResizable(false);
        showTournamentDataMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        showTournamentDataMenu.dispose();
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
