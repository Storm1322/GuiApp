package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.DefaultTableModel;

public class DeleteTournamentMenu extends JFrame implements KeyListener{
    static JFrame deleteTournamentMenu;
    static JButton returnButton, deleteButton;
    static JTable tournamentDisplay;
    static JScrollPane tournamentDisplayPane;
    GridBagConstraints constraints;
    
    public DeleteTournamentMenu(){
        constraints = new GridBagConstraints();
        deleteTournamentMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeTables();
        
        deleteTournamentMenu.addKeyListener(this);
    }
    
    public void initializeButtons(){
        returnButton = new JButton("Return <=");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        returnButton.addActionListener(e -> returnToMenu());
        deleteTournamentMenu.add(returnButton, constraints);
        deleteButton = new JButton("delete");
        constraints.gridx = 2;
        constraints.gridy = 2;
        deleteButton.addActionListener(e -> confirmDeletion());
        deleteTournamentMenu.add(deleteButton, constraints);
    }
    
    public void initializeTables(){
        tournamentDisplay = new JTable(Tournament.tournamentArray,Tournament.tournamentHeadline);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
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
        deleteTournamentMenu.add(tournamentDisplayPane, constraints);
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
            int[] rowsToRemove = tournamentDisplay.getSelectedRows();
            for(int i = rowsToRemove.length - 1; i >= 0; i--){
                ((DefaultTableModel)tournamentDisplay.getModel()).removeRow(rowsToRemove[i]);
                Tournament.tournaments.remove(rowsToRemove[i]);
            }
            Tournament.storeTournaments();
            Tournament.importTournaments();
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_DELETE){
            confirmDeletion();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_DELETE){
            confirmDeletion();
            System.out.println("delete pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_DELETE){
            confirmDeletion();
            System.out.println("delete pressed");
        }
    }
}
