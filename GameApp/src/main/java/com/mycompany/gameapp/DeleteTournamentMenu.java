package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.DefaultTableModel;

public class DeleteTournamentMenu extends JFrame implements ActionListener{
    static JFrame deleteTournamentMenu;
    static JButton returnButton, deleteButton;
    static JTable tournamentDisplay;
    static JScrollPane tournamentDisplayPane;
    
    public DeleteTournamentMenu(){
        deleteTournamentMenu = new JFrame("Tennis Tournament");
        
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
        tournamentDisplay = new JTable(Tournament.tournamentArray,Tournament.tournamentHeadline);
        tournamentDisplay.setBounds(0, 0, 800, 450);
        tournamentDisplayPane = new JScrollPane(tournamentDisplay);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Tournament.tournamentArray,Tournament.tournamentHeadline) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tournamentDisplay.setModel(tableModel);
    }
    
    public static void addComponentsToFrame(){
        deleteTournamentMenu.add(returnButton);
        deleteTournamentMenu.add(tournamentDisplayPane);
        deleteTournamentMenu.add(deleteButton);       
    }
    
    public static void frameSettings(){
        deleteTournamentMenu.setSize(800, 600);
        deleteTournamentMenu.setLayout(new GridBagLayout());
        deleteTournamentMenu.setVisible(true);
        deleteTournamentMenu.setLocationRelativeTo(null);
        deleteTournamentMenu.setResizable(false);
        deleteTournamentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        deleteTournamentMenu.dispose();
        new MainMenu();
    }
    
    public static void confirmDeletion(){
        int answer = showConfirmDialog(null, "Are you sure?", "Select an option...", JOptionPane.YES_NO_OPTION);
        if (answer == 0){
            int rowToRemove = tournamentDisplay.getSelectedRow();
            ((DefaultTableModel)tournamentDisplay.getModel()).removeRow(rowToRemove);
            Tournament.tournaments.remove(rowToRemove);
            Tournament.storeTournaments();
            Tournament.importTournaments();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
