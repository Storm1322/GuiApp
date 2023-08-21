package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class SimulateTournamentMenu extends JFrame{
    static JFrame simulateTournamentMenu;
    static JButton returnButton, continueButton, exitButton;
    static JTextField playerOneScoreTF, playerTwoScoreTF;
    static JLabel playerOneL, playerTwoL, invalidInput, dashL, playerOneScoreL, playerTwoScoreL;
    static JTable tournamentParticipants;
    static JScrollPane tournamentParticipantsPane;
    GridBagConstraints constraints;
    
    public SimulateTournamentMenu(){
        constraints = new GridBagConstraints();
        simulateTournamentMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeLabels();
        
        initializeTextFields();
        
        initializeTables();
    }
    
    public void initializeButtons(){
        returnButton = new JButton("Return <=");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        returnButton.addActionListener(e -> returnToBeginTournament());
        simulateTournamentMenu.add(returnButton, constraints);
        continueButton = new JButton("Continue");
        constraints.gridx = 1;
        constraints.gridy = 2;
        continueButton.addActionListener(e -> simulateTournament());
        simulateTournamentMenu.add(continueButton, constraints);
        exitButton = new JButton("Exit");
        constraints.gridx = 2;
        constraints.gridy = 2;
        exitButton.addActionListener(e -> exit());
        simulateTournamentMenu.add(exitButton, constraints);
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
        simulateTournamentMenu.add(playerTwoScoreL);
        simulateTournamentMenu.add(playerTwoL);
        simulateTournamentMenu.add(playerOneScoreL);
        simulateTournamentMenu.add(playerOneL);
        simulateTournamentMenu.add(dashL);
        simulateTournamentMenu.add(invalidInput);
        simulateTournamentMenu.add(GameApp.invalidInput);
        GameApp.invalidInput.setVisible(false);
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
        simulateTournamentMenu.add(playerOneScoreTF);
        simulateTournamentMenu.add(playerTwoScoreTF);
    }
    
    public void initializeTables(){
        tournamentParticipants = new JTable(Simulation.playingPlayersArray, Simulation.participants);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
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
        simulateTournamentMenu.add(tournamentParticipantsPane, constraints);
    }
    
    public static void frameSettings(){
        simulateTournamentMenu.setSize(800, 600);
        simulateTournamentMenu.setLayout(new GridBagLayout());
        simulateTournamentMenu.setVisible(true);
        simulateTournamentMenu.setLocationRelativeTo(null);
        simulateTournamentMenu.setResizable(false);
        simulateTournamentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    Turnuvuda oynayacak oyuncu sayisi belirlenen menuye geri donmek icin method.
    public static void returnToBeginTournament(){
//        simulasyonda kullanilan datalar reset.
        Simulation.playingPlayersObjects.clear();
        Simulation.playingPlayers.clear();
        Simulation.currentPlayersObjects.clear();
        Simulation.pickedNumbers.clear();
        Simulation.roundCount = 1;
        Simulation.matchCount = 0;
        simulateTournamentMenu.dispose();
        new BeginTournamentMenu();
    }
    
//    Turnuva simulasyonunu baslatmak icin method.
    protected static void simulateTournament(){
        continueButton.setVisible(false);
        tournamentParticipantsPane.setVisible(false);
        simulateTournamentMenu.setLayout(null);
        exitButton.setBounds(325, 500, 150, 50);
        returnButton.setBounds(100, 500, 150, 50);
        showMessageDialog(null, "Starting tournament simulation.\n You are expected to enter set scores for both players. \n Enter 60 for one player and either 45, 30, 15 or 0 for the other. \n First player to 4 sets wins.");
        playerOneScoreL.setBounds(250, 150, 300, 25);
        Simulation.simulateTournament();
    }
    
//    Birinci playerin skorunu girmek icin arayuz elemanlarini ayarlayan method.
    protected static void playerOneScoreEntry(){
        String firstPlayer = Simulation.currentPlayersObjects.get(0).name + " " + Simulation.currentPlayersObjects.get(0).surname;
        playerOneScoreL.setText("Enter " + firstPlayer + "'s score: (Press enter)");
        playerOneL.setText(firstPlayer + "    " + Simulation.playerOneSetScore);
        playerTwoL.setText(Simulation.playerTwoSetScore + "    " + Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname);
        playerOneScoreL.setVisible(true);
        playerOneL.setVisible(true);
        playerTwoL.setVisible(true);
        dashL.setVisible(true);
//        Text Fielda input beklenicek.
        playerOneScoreTF.setVisible(true);
    }
    
//    Ikinci playerin skorunu girmek icin arayuz elemanlarini ayarlayan method.
    protected static void playerTwoScoreEntry(){
        if(GameApp.checkInput(playerOneScoreTF.getText()) == false){
            Simulation.playerOneScore = Integer.parseInt(playerOneScoreTF.getText());
            if(Simulation.playerOneScore != 60 && Simulation.playerOneScore != 45 && Simulation.playerOneScore != 30 && Simulation.playerOneScore != 15 && Simulation.playerOneScore != 0){
                invalidInput.setVisible(true);
                GameApp.invalidInput.setVisible(false);
            }else{
                playerOneScoreTF.setVisible(false);
                playerOneScoreTF.setText("");
                playerOneScoreL.setVisible(false);
                invalidInput.setVisible(false);
                playerOneScoreTF.setVisible(false);
                String secondPlayer = Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname;
                playerTwoScoreL.setBounds(275, 150, 250, 25);
                playerTwoScoreL.setText("Enter " + secondPlayer + "'s score: (Press enter)");
                playerTwoScoreL.setVisible(true);
                playerTwoScoreTF.setVisible(true);
            }
        }else{
            GameApp.invalidInput.setVisible(false);
            invalidInput.setVisible(true);
        }
    }
    
    
//    Skorlar istenen sekilde girilmis mi kontrol etmek icin method.
    public static void checkScores(){
        if(GameApp.checkInput(playerTwoScoreTF.getText()) == false){
            Simulation.playerTwoScore = Integer.parseInt(playerTwoScoreTF.getText());
            if(Simulation.playerTwoScore != 60 && Simulation.playerTwoScore != 45 && Simulation.playerTwoScore != 30 && Simulation.playerTwoScore != 15 && Simulation.playerTwoScore != 0){
                invalidInput.setVisible(true);
                GameApp.invalidInput.setVisible(false);
            }else{
                invalidInput.setVisible(false);
                playerTwoScoreTF.setText("");
                playerTwoScoreL.setVisible(false);
                playerTwoScoreTF.setVisible(false);
                Simulation.scoreCheck();
            }
        }else{
            GameApp.invalidInput.setVisible(false);
            invalidInput.setVisible(true);
        }
    }
    
//    Sonraki rounda gecerken kalan oyunculari gosteren method.
    public static void nextRoundMessage(){
        showMessageDialog(null, "Players moving to the next rounds are: " + Simulation.playingPlayers);
    }
    
//    Kazanani gosteren method.
    public static void winnerMessage(){
        dashL.setVisible(false);
        showMessageDialog(null, Simulation.playingPlayersObjects.get(0).name + " " + Simulation.playingPlayersObjects.get(0).surname + " has won the tournament!");
        int answer = showConfirmDialog(null, "Would you like to start another tournament?", "Start Again?", JOptionPane.YES_NO_OPTION);
        if (answer == 0){
            returnToBeginTournament();
        }else{
            Simulation.playingPlayersObjects.clear();
            Simulation.playingPlayers.clear();
            Simulation.currentPlayersObjects.clear();
            Simulation.pickedNumbers.clear();
            Simulation.roundCount = 1;
            Simulation.matchCount = 0;
            simulateTournamentMenu.dispose();
            new MainMenu();
        }
    }
    
//    Uygulamayi kapat.
    public static void exit(){
        System.exit(0);
    }
}
