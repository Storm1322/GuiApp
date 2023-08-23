package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class SimulateTournamentMenu extends JFrame{
    static JFrame simulateTournamentMenu;
    static JButton returnButton, continueButton, exitButton;
    static JTextField playerOneScoreTF, playerTwoScoreTF;
    static JLabel playerOneL, playerTwoL, invalidInput, dashL, playerOneScoreL, playerTwoScoreL;
    static private JLabel[] matchLabels;
    static JTable tournamentParticipants, setScoresTable;
    static String[] scoreTableHeadlines = {"Player 1","Player 2"};
    static String[] zeroArray = {"0","0"};
    static String[][] setArray;
    static JScrollPane tournamentParticipantsPane, bracketPanelPane, setScoresPanelPane;
    static protected JPanel bracketPanel, setScoresPanel;
    GridBagConstraints constraints;
    public static DefaultTableModel setScoreModel;
    static int numOfRounds = (int) (Math.log(Double.valueOf(Tournament.tournamentPlayerCount)) / Math.log(2));
    static int labelIndex;
    
    public SimulateTournamentMenu(){
        constraints = new GridBagConstraints();
        simulateTournamentMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeLabels();
        
        initializeTextFields();
        
        initializePanels();
        
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
        continueButton.addActionListener(e -> {
            try {
                simulateTournament();
            } catch (IOException ex) {
                Logger.getLogger(SimulateTournamentMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        simulateTournamentMenu.add(continueButton, constraints);
        exitButton = new JButton("Exit");
        constraints.gridx = 2;
        constraints.gridy = 2;
        exitButton.addActionListener(e -> exit());
        simulateTournamentMenu.add(exitButton, constraints);
    }
    
    public void initializeLabels(){
        playerOneL = new JLabel();
        playerOneL.setBounds(125, 300, 150, 25);
        playerOneL.setVisible(false);
        playerTwoL = new JLabel();
        playerTwoL.setBounds(375, 300, 150, 25);
        playerTwoL.setVisible(false);
        invalidInput = new JLabel("Invalid input. Please check again.");
        invalidInput.setBounds(200, 250, 200, 50);
        invalidInput.setVisible(false);
        dashL = new JLabel("---");
        dashL.setBounds(298, 300, 25, 25);
        dashL.setVisible(false);
        playerOneScoreL = new JLabel();
        playerOneScoreL.setBounds(175, 150, 300, 25);
        playerTwoScoreL = new JLabel();
        playerTwoScoreL.setBounds(175, 150, 300, 25);
        simulateTournamentMenu.add(playerTwoScoreL);
        simulateTournamentMenu.add(playerTwoL);
        simulateTournamentMenu.add(playerOneScoreL);
        simulateTournamentMenu.add(playerOneL);
        simulateTournamentMenu.add(dashL);
        simulateTournamentMenu.add(invalidInput);
        simulateTournamentMenu.add(GameApp.invalidInput);
        GameApp.invalidInput.setVisible(false);
        
        matchLabels = new JLabel[(int) (Math.pow(2, numOfRounds) - 1)];
        
    }
    
    public static void initializeTextFields(){
        playerOneScoreTF = new JTextField();
        playerOneScoreTF.setBounds(200, 200, 200, 25);
        playerOneScoreTF.addActionListener(e -> playerTwoScoreEntry());
        playerOneScoreTF.setVisible(false);
        playerTwoScoreTF = new JTextField();
        playerTwoScoreTF.setBounds(200, 200, 200, 25);
        playerTwoScoreTF.addActionListener(e -> {
            try {
                checkScores();
            } catch (IOException ex) {
                Logger.getLogger(SimulateTournamentMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        playerTwoScoreTF.setVisible(false);
        simulateTournamentMenu.add(playerOneScoreTF);
        simulateTournamentMenu.add(playerTwoScoreTF);
    }
    
    public void initializePanels(){
        bracketPanelPane = new JScrollPane(new JPanel());
        bracketPanelPane.setBounds(425, 100, 375, 175);
        
        createBrackets(Tournament.tournamentPlayerCount / 2, 0);
        bracketPanelPane.setVisible(false);
        
        setScoresPanel = new JPanel();
        setScoresPanel.setLayout(new GridBagLayout());
        setScoresPanel.setBackground(Color.gray);
        setScoresPanelPane = new JScrollPane(setScoresPanel);
        setScoresPanelPane.setBounds(550, 300, 200, 130);
        setScoresPanelPane.setVisible(false);
        simulateTournamentMenu.add(setScoresPanelPane);
    }
    
    public static void createBrackets(int matchNumber,int currentRound){
        bracketPanel = new JPanel();
        if(matchNumber > 1){
            bracketPanel.setLayout(new GridLayout(matchNumber * 3 / 2, 1));
        }else{
            bracketPanel.setLayout(new GridLayout(matchNumber, 1));
        }
        for (int j = 0; j < matchNumber; j++) {    
            if(currentRound == 0){
                matchLabels[labelIndex] = new JLabel("Match " + (labelIndex + 1) + ": ");
                bracketPanel.add(matchLabels[labelIndex]);
            }else{
                bracketPanel.add(new JLabel(""));
                matchLabels[labelIndex] = new JLabel("Match " + (labelIndex + 1) + ": ");
                bracketPanel.add(matchLabels[labelIndex]);
            }
            labelIndex++;        
        }
        ((JPanel)bracketPanelPane.getViewport().getView()).add(bracketPanel);
        simulateTournamentMenu.add(bracketPanelPane);
        if(matchNumber > 1){
            createBrackets(matchNumber / 2,currentRound + 1);
        }
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
        
        setArray = new String[7][2];
        for (int i = 0; i < 7;){
            setArray[i++] = zeroArray;
        }
        
        //instance table model
        setScoreModel = new DefaultTableModel(setArray, scoreTableHeadlines) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        setScoresTable = new JTable(setArray, scoreTableHeadlines);
        setScoresTable.setModel(setScoreModel);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        constraints.gridwidth = 3;
        setScoresPanel.add(setScoresTable, constraints);
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
        Simulation.currentSet = 0;
        labelIndex = 0;
        simulateTournamentMenu.dispose();
        new BeginTournamentMenu();
    }
    
//    Turnuva simulasyonunu baslatmak icin method.
    protected static void simulateTournament() throws IOException{
        continueButton.setVisible(false);
        tournamentParticipantsPane.setVisible(false);
        simulateTournamentMenu.setLayout(null);
        exitButton.setBounds(325, 500, 150, 50);
        returnButton.setBounds(100, 500, 150, 50);
        showMessageDialog(null, "Starting tournament simulation.\n You are expected to enter set scores for both players. \n Enter 60 for one player and either 45, 30, 15 or 0 for the other. \n First player to 4 sets wins.");
        playerOneScoreL.setBounds(150, 150, 350, 25);
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
        bracketPanelPane.setVisible(true);
        setScoresPanelPane.setVisible(true);
        updateBracketScores(Simulation.matchesDone, 
            Simulation.currentPlayersObjects.get(0).name + " " + Simulation.currentPlayersObjects.get(0).surname, 
            Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname, 
       Simulation.playerOneSetScore, Simulation.playerTwoSetScore);
//        Text Fielda input beklenicek.
        playerOneScoreTF.setVisible(true);
        playerOneScoreTF.requestFocus();
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
                updateBracketScores(Simulation.matchesDone, 
                    Simulation.currentPlayersObjects.get(0).name + " " + Simulation.currentPlayersObjects.get(0).surname, 
                    Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname, 
               Simulation.playerOneSetScore, Simulation.playerTwoSetScore);
                String secondPlayer = Simulation.currentPlayersObjects.get(1).name + " " + Simulation.currentPlayersObjects.get(1).surname;
                playerTwoScoreL.setBounds(150, 150, 350, 25);
                playerTwoScoreL.setText("Enter " + secondPlayer + "'s score: (Press enter)");
                playerTwoScoreL.setVisible(true);
                playerTwoScoreTF.setVisible(true);
                playerTwoScoreTF.requestFocus();
            }
        }else{
            GameApp.invalidInput.setVisible(false);
            invalidInput.setVisible(true);
        }
    }
    
    
//    Skorlar istenen sekilde girilmis mi kontrol etmek icin method.
    public static void checkScores() throws IOException{
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
    
    public static void updateBracketScores(int matchNumber, String participant1, String participant2, int wins1, int wins2){
        if (matchNumber >= 1 && matchNumber <= Math.pow(2, numOfRounds + 1) - 1) {
            matchLabels[matchNumber - 1].setText("Match " + matchNumber + ": " +
                    participant1 + " (" + wins1 + " score) vs " +
                    participant2 + " (" + wins2 + " score)");
        }
    }
    
//    Sonraki rounda gecerken kalan oyunculari gosteren method.
    public static void nextRoundMessage(){
        showMessageDialog(null, "Players moving to the next rounds are: " + Simulation.playingPlayers);
    }
    
//    Kazanani gosteren method.
    public static void winnerMessage() throws IOException{
        dashL.setVisible(false);
        Dimension size = bracketPanelPane.getSize();
        int imageHeight = size.height;
        int imageWidth = size.width;
        
        for(int j = 0; j < bracketPanelPane.getComponentCount(); j++){
            BufferedImage componentImage = new BufferedImage(imageHeight, imageWidth, BufferedImage.TYPE_INT_RGB);
//            Now paint the component directly onto the image
            Graphics2D imageGraphics = componentImage.createGraphics();
            bracketPanelPane.getComponent(j).paint(imageGraphics);
            Path f = Paths.get("TournamentName" + j + ".jpg");
            Files.createFile(f);
            File file = new File("TournamentName" + j + ".jpg");
            ImageIO.write(componentImage, "jpg", file);
        }
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
