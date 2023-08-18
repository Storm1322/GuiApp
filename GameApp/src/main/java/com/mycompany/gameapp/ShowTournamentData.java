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
    GridBagConstraints constraints;
    
    public ShowTournamentData() {
        constraints = new GridBagConstraints();
        showTournamentDataMenu = new JFrame("Tennis Tournament");
        
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
        showTournamentDataMenu.add(returnButton, constraints);
    }
    
    public void initializeTables(){
        tournamentDisplay = new JTable(Tournament.tournamentArray, Tournament.tournamentHeadline);
        tournamentDisplayPane = new JScrollPane(tournamentDisplay);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Tournament.tournamentArray, Tournament.tournamentHeadline) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tournamentDisplay.setModel(tableModel);
        showTournamentDataMenu.add(tournamentDisplayPane, constraints);
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
