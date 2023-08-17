package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class SimulateTournamentMenu extends JFrame implements ActionListener{
    static JFrame simulateTournamentMenu;
    static JButton continueButton, exitButton;
    static JTextField playerOneScoreTF, playerTwoScoreTF;
    static JLabel playerOneL, playerTwoL, invalidInput, dashL, playerOneScoreL, playerTwoScoreL;
    static JTable tournamentParticipants;
    static JScrollPane tournamentParticipantsPane;
    
    public SimulateTournamentMenu(){
        simulateTournamentMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeLabels();
        
        initializeTextFields();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        continueButton = new JButton("Continue");
        continueButton.setBounds(325, 400, 150, 50);
        continueButton.addActionListener(e -> simulateTournament());
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> exit());
    }
    
    public static void initializeLabels(){
        playerOneL = new JLabel();
        playerOneL.setBounds(225, 300, 150, 25);
        playerOneL.setVisible(false);
        playerTwoL = new JLabel();
        playerTwoL.setBounds(475, 300, 150, 25);
        playerTwoL.setVisible(false);
        invalidInput = new JLabel("Invalid input. Please check again.");
        invalidInput.setBounds(300, 250, 200, 50);
        invalidInput.setVisible(false);
        dashL = new JLabel("---");
        dashL.setBounds(398, 300, 25, 25);
        dashL.setVisible(false);
        playerOneScoreL = new JLabel();
        playerOneScoreL.setBounds(275, 150, 250, 25);
        playerTwoScoreL = new JLabel();
        playerTwoScoreL.setBounds(275, 150, 250, 25);
    }
    
    public static void initializeTextFields(){
        playerOneScoreTF = new JTextField();
        playerOneScoreTF.setBounds(300, 200, 200, 25);
        playerOneScoreTF.addActionListener(e -> playerTwoScoreEntry());
        playerOneScoreTF.setVisible(false);
        playerTwoScoreTF = new JTextField();
        playerTwoScoreTF.setBounds(300, 200, 200, 25);
        playerTwoScoreTF.addActionListener(e -> checkScores());
        playerTwoScoreTF.setVisible(false);
    }
    
    public static void initializeTables(){
        tournamentParticipants = new JTable(Simulation.playingPlayersArray, Simulation.participants);
        tournamentParticipants.setBounds(100, 50, 600, 350);
        tournamentParticipantsPane = new JScrollPane(tournamentParticipants);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(Simulation.playingPlayersArray, Simulation.participants) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tournamentParticipants.setModel(tableModel);
    }
    
    public static void addComponentsToFrame(){
        simulateTournamentMenu.add(playerOneScoreTF);
        simulateTournamentMenu.add(playerOneScoreL);
        simulateTournamentMenu.add(playerOneL);
        simulateTournamentMenu.add(playerTwoScoreTF);
        simulateTournamentMenu.add(playerTwoScoreL);
        simulateTournamentMenu.add(playerTwoL);
        simulateTournamentMenu.add(invalidInput);
        simulateTournamentMenu.add(exitButton);
        simulateTournamentMenu.add(tournamentParticipantsPane);
        simulateTournamentMenu.add(continueButton);
        simulateTournamentMenu.add(dashL);
    }
    
    public static void frameSettings(){
        simulateTournamentMenu.setSize(800, 600);
        simulateTournamentMenu.setLayout(new GridBagLayout());
        simulateTournamentMenu.setVisible(true);
        simulateTournamentMenu.setLocationRelativeTo(null);
        simulateTournamentMenu.setResizable(false);
        simulateTournamentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    Turnuva simulasyonunu baslatmak icin method.
    protected static void simulateTournament(){
        continueButton.setVisible(false);
        tournamentParticipantsPane.setVisible(false);
        showMessageDialog(null, "Starting tournament simulation.\n You are expected to enter set scores for both players. \n Enter 60 for one player and either 45, 30, 15 or 0 for the other. \n First player to 4 sets wins.");
        simulateTournamentMenu.setLayout(null);
        exitButton.setBounds(325, 500, 150, 50);
        playerOneScoreL.setBounds(275, 150, 250, 25);
        Simulation.simulateTournament();
    }
    
    protected static void playerOneScoreEntry(){
        String firstPlayer = Simulation.currentPlayersObjects.get(0).name + " " + Simulation.currentPlayersObjects.get(0).surname;
        playerOneScoreL.setText("Enter " + firstPlayer + "'s score:");
        playerOneL.setText(firstPlayer + "    " + Simulation.playerOneSetScore);
        playerTwoL.setText(Simulation.playerTwoSetScore + "    " + Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname);
        playerOneScoreL.setVisible(true);
        playerOneL.setVisible(true);
        playerTwoL.setVisible(true);
        dashL.setVisible(true);
//        Text Fielda input beklenicek.
        playerOneScoreTF.setVisible(true);
    }
    
    protected static void playerTwoScoreEntry(){
        if(GameApp.checkInput(playerOneScoreTF.getText()) == false){
            Simulation.playerOneScore = Integer.parseInt(playerOneScoreTF.getText());
            if(Simulation.playerOneScore != 60 && Simulation.playerOneScore != 45 && Simulation.playerOneScore != 30 && Simulation.playerOneScore != 15 && Simulation.playerOneScore != 0){
                invalidInput.setVisible(true);
            }else{
                playerOneScoreTF.setVisible(false);
                playerOneScoreL.setVisible(false);
                invalidInput.setVisible(false);
                playerOneScoreTF.setVisible(false);
                String secondPlayer = Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname;
                playerTwoScoreL.setBounds(275, 150, 250, 25);
                playerTwoScoreL.setText("Enter " + secondPlayer + "'s score:");
                playerTwoScoreL.setVisible(true);
                playerTwoScoreTF.setVisible(true);
            }
        }
    }
    
    public static void checkScores(){
        if(GameApp.checkInput(playerTwoScoreTF.getText()) == false){
            Simulation.playerTwoScore = Integer.parseInt(playerTwoScoreTF.getText());
            if(Simulation.playerOneScore != 60 && Simulation.playerOneScore != 45 && Simulation.playerOneScore != 30 && Simulation.playerOneScore != 15 && Simulation.playerOneScore != 0){
                invalidInput.setVisible(true);
            }else{
                invalidInput.setVisible(false);
                playerTwoScoreL.setVisible(false);
                playerTwoScoreTF.setVisible(false);
                Simulation.scoreCheck();
            }
        } 
    }
    
    public static void nextRoundMessage(){
        showMessageDialog(null, "");
    }
    
    public static void exit(){
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
