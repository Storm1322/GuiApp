package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameApp extends JFrame implements ActionListener{
    static JFrame frame = new JFrame("Tennis Simulation");
    static JButton yesButton, noButton, exitButton, addPlayerButton, addCityButton, addTournamentButton, continueButton, returnButton, deletePlayerButton, deleteCityButton, deleteTournamentButton, beginTournamentButton, seeSavedData;
    static JLabel playerNameL, playerSurnameL, playerAgeL, playerOriginL, playerGenderL, invalidInput, areYouSure, playerDataL, cityDataL, tournamentDataL, cityL, tournamentL, playerCountL, notEnough, tournamentPlayersL, playerOneL, playerTwoL, dashL, playerOneScoreL, playerTwoScoreL, tournamentWinnerL;
    static JTextField playerNameTF, playerSurnameTF, playerAgeTF, playerOriginTF, playerGenderTF, tournamentTF, cityTF, playerCountTF, playerOneScoreTF, playerTwoScoreTF;
    static JTextArea playerDisplayTA, cityDisplayTA, tournamentDisplayTA;
    static JScrollPane playerDisplaySP, cityDisplaySP, tournamentDisplaySP;
    static JTable matchScores;
    static boolean dataAlreadyShown = false;
    static String current;
    
    public GameApp(){
     
//        Kullanilan buttonlar.
        exitButton = new JButton("Exit");
        exitButton.setBounds(325, 500, 150, 50);
        exitButton.addActionListener(this);
        addPlayerButton = new JButton("Add Players");
        addPlayerButton.setBounds(100, 100, 150, 50);
        addPlayerButton.addActionListener(this);
        addCityButton = new JButton("Add Cities");
        addCityButton.setBounds(100, 200, 150, 50);
        addCityButton.addActionListener(this);
        addTournamentButton = new JButton("Add Tournaments");
        addTournamentButton.setBounds(100, 300, 150, 50);
        addTournamentButton.addActionListener(this);
        continueButton = new JButton("Continue");
        continueButton.setBounds(325, 400, 150, 50);
        continueButton.addActionListener(this);
        continueButton.addActionListener(new Player());
        continueButton.addActionListener(new City());
        continueButton.addActionListener(new Tournament());
        continueButton.addActionListener(new Simulation());
        returnButton = new JButton("Return <=");
        returnButton.setBounds(100, 450, 150, 50);
        returnButton.addActionListener(this);
        deletePlayerButton = new JButton("Delete Players");
        deletePlayerButton.setBounds(550, 100, 150, 50);
        deletePlayerButton.addActionListener(this);
        deleteCityButton = new JButton("Delete Cities");
        deleteCityButton.setBounds(550, 200, 150, 50);
        deleteCityButton.addActionListener(this);
        deleteTournamentButton = new JButton("Delete Tournaments");
        deleteTournamentButton.setBounds(550, 300, 150, 50);
        deleteTournamentButton.addActionListener(this);
        beginTournamentButton = new JButton("Begin Tournament");
        beginTournamentButton.setBounds(325, 200, 150, 50);
        beginTournamentButton.addActionListener(this);
        yesButton = new JButton("Yes");
        yesButton.setBounds(200, 400, 150, 50);
        yesButton.addActionListener(this);
        noButton = new JButton("No");
        noButton.setBounds(450, 400, 150, 50);
        noButton.addActionListener(this);
        seeSavedData = new JButton("See Saved Data");
        seeSavedData.setBounds(325, 300, 150, 50);
        seeSavedData.addActionListener(this);
        
//        Kullanilan text fieldlar.
        playerNameTF = new JTextField();
        playerNameTF.setBounds(300, 50, 200, 25);
        playerSurnameTF = new JTextField();
        playerSurnameTF.setBounds(300, 100, 200, 25);
        playerAgeTF = new JTextField();
        playerAgeTF.setBounds(300, 150, 200, 25);
        playerOriginTF = new JTextField();
        playerOriginTF.setBounds(300, 200, 200, 25);
        playerGenderTF = new JTextField();
        playerGenderTF.setBounds(300, 250, 200, 25);
        cityTF = new JTextField();
        cityTF.setBounds(300, 200, 200, 25);
        tournamentTF = new JTextField();
        tournamentTF.setBounds(300, 200, 200, 25);
        playerCountTF = new JTextField();
        playerCountTF.setBounds(300, 200, 200, 25);
        playerCountTF.addActionListener(new Tournament());
        playerOneScoreTF = new JTextField();
        playerOneScoreTF.setBounds(300, 200, 200, 25);
        playerOneScoreTF.addActionListener(new Simulation());
        playerTwoScoreTF = new JTextField();
        playerTwoScoreTF.setBounds(300, 200, 200, 25);
        playerTwoScoreTF.addActionListener(new Simulation());
        
//        Kullanilan Labellar.
        playerNameL = new JLabel("Player Name:");
        playerNameL.setBounds(150,50,125,25);
        playerSurnameL = new JLabel("Player Surname:");
        playerSurnameL.setBounds(150,100,125,25);
        playerAgeL = new JLabel("Player Age:");
        playerAgeL.setBounds(150,150,125,25);
        playerOriginL = new JLabel("Player Country:");
        playerOriginL.setBounds(150,200,125,25);
        playerGenderL = new JLabel("Player Gender:");
        playerGenderL.setBounds(150,250,125,25);
        invalidInput = new JLabel("One of the inputs was invalid. Please check again.");
        invalidInput.setBounds(250, 350, 300, 25);
        areYouSure = new JLabel("Are you sure you want to continue?");
        areYouSure.setBounds(300, 150, 300, 25);
        playerDataL = new JLabel("Players");
        playerDataL.setBounds(125, 20, 50, 25);
        cityDataL = new JLabel("Cities");
        cityDataL.setBounds(377, 20, 50, 25);
        tournamentDataL = new JLabel("Tournaments");
        tournamentDataL.setBounds(610, 20, 80, 25);
        cityL = new JLabel("City Name:");
        cityL.setBounds(200, 200, 125, 25);
        tournamentL = new JLabel("Tournament Name:");
        tournamentL.setBounds(175, 200, 125, 25);
        playerCountL = new JLabel("How many players would you like to have play. (enter a power of 2.)");
        playerCountL.setBounds(200, 150, 400, 25);
        notEnough = new JLabel("Not enough players saved for set number.");
        notEnough.setBounds(275, 350, 250, 25);
        tournamentPlayersL = new JLabel();
        tournamentPlayersL.setBounds(100, 100, 600, 25);
        playerOneL = new JLabel();
        playerOneL.setBounds(225, 300, 150, 25);
        playerTwoL = new JLabel();
        playerTwoL.setBounds(475, 300, 150, 25);
        dashL = new JLabel("---");
        dashL.setBounds(398, 300, 25, 25);
        playerOneScoreL = new JLabel();
        playerOneScoreL.setBounds(275, 150, 250, 25);
        playerTwoScoreL = new JLabel();
        playerTwoScoreL.setBounds(275, 150, 250, 25);
        tournamentWinnerL = new JLabel();
        tournamentWinnerL.setBounds(275, 250, 250, 50);
        
//        Kullanilan scroll paneler.
        playerDisplayTA = new JTextArea(200,40);
        playerDisplayTA.setBounds(50, 50, 200, 300);
        playerDisplayTA.setEditable(false);
        playerDisplaySP = new JScrollPane(playerDisplayTA);
        playerDisplaySP.createVerticalScrollBar();
        playerDisplaySP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cityDisplayTA = new JTextArea(200,40);
        cityDisplayTA.setBounds(300, 50, 200, 300);
        cityDisplayTA.setEditable(false);
        cityDisplaySP = new JScrollPane(cityDisplayTA);
        cityDisplaySP.createVerticalScrollBar();
        cityDisplaySP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tournamentDisplayTA = new JTextArea(200,40);
        tournamentDisplayTA.setBounds(550, 50, 200, 300);
        tournamentDisplayTA.setEditable(false);
        tournamentDisplaySP = new JScrollPane(cityDisplayTA);
        tournamentDisplaySP.createVerticalScrollBar();
        tournamentDisplaySP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
//        Tum componentleri frame'e ekle.
        frame.add(exitButton);
        frame.add(addPlayerButton);
        frame.add(addCityButton);
        frame.add(addTournamentButton);
        frame.add(deletePlayerButton);
        frame.add(deleteCityButton);
        frame.add(deleteTournamentButton);
        frame.add(beginTournamentButton);
        frame.add(playerNameTF);
        frame.add(playerSurnameTF);
        frame.add(playerAgeTF);
        frame.add(playerOriginTF);
        frame.add(playerGenderTF);
        frame.add(continueButton);
        frame.add(returnButton);
        frame.add(playerNameL);
        frame.add(playerSurnameL);
        frame.add(playerAgeL);
        frame.add(playerOriginL);
        frame.add(playerGenderL);
        frame.add(invalidInput);
        frame.add(yesButton);
        frame.add(noButton);
        frame.add(areYouSure);
        frame.add(seeSavedData);
        frame.add(playerDataL);
        frame.add(playerDisplayTA);
        frame.add(playerDisplaySP);
        frame.add(cityDataL);
        frame.add(cityDisplayTA);
        frame.add(cityDisplaySP);
        frame.add(tournamentDataL);
        frame.add(tournamentDisplayTA);
        frame.add(tournamentDisplaySP);
        frame.add(cityTF);
        frame.add(cityL);
        frame.add(tournamentTF);
        frame.add(tournamentL);
        frame.add(playerCountTF);
        frame.add(playerCountL);
        frame.add(notEnough);
        frame.add(tournamentPlayersL);
        frame.add(playerOneL);
        frame.add(playerTwoL);
        frame.add(dashL);
        frame.add(playerOneScoreTF);
        frame.add(playerTwoScoreTF);
        frame.add(playerOneScoreL);
        frame.add(playerTwoScoreL);
        
        returnToMenu();
        
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Player.importPlayers();
        City.importCities();
        Tournament.importTournaments();
        
        new GameApp();
    }
    
    public static void showSaved(){
        playerDataL.setVisible(true);
        playerDisplayTA.setVisible(true);
        playerDisplaySP.setVisible(true);
        cityDataL.setVisible(true);
        cityDisplayTA.setVisible(true);
        cityDisplaySP.setVisible(true);
        tournamentDataL.setVisible(true);
        tournamentDisplayTA.setVisible(true);
        tournamentDisplaySP.setVisible(true);
        returnButton.setVisible(true);
        if(dataAlreadyShown == false){
            Player.showPlayers();
            City.showCities();
            Tournament.showTournaments();
        }
        dataAlreadyShown = true;
    }
    
//    String istendiginde integer bulunduran input var mi kontrol eden method.
    public static boolean checkInputForString(String str){
        if(str.equals("")){
            return true;
        }else{
            str = str.replaceAll("[^0-9]", "");
            return !"".equals(str);
        }
    }
    
//    Integer istendiginde integer olmayan input var mi kontrol eden method.
    public static boolean checkInput(String str){
        if(str.equals("")){
            return true;
        }else{
            str = str.replaceAll("[0-9]", "");
            return !"".equals(str);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exitButton){
            System.exit(0);
        }else{
            if(e.getSource() == addPlayerButton){
                exitMenu();
                current = "adding player";
                Player.registerPlayers();
            }else if(e.getSource() == addCityButton){
                exitMenu();
                current = "adding city";
                City.addCity();
            }else if(e.getSource() == addTournamentButton){
                exitMenu();
                current = "adding tournament";
                Tournament.addTournament();
            }else if(e.getSource() == seeSavedData){
                exitMenu();
                showSaved();
            }else if(e.getSource() == beginTournamentButton){
                exitMenu();
                current = "begin tournament";
                Tournament.setPlayerCount();
            }else if(e.getSource() == deletePlayerButton || e.getSource() == deleteCityButton || e.getSource() == deleteTournamentButton){
                exitMenu();
                yesButton.setVisible(true);
                noButton.setVisible(true);
                areYouSure.setVisible(true);
                if(e.getSource() == deletePlayerButton){
                    current = "delete players";
                }else if(e.getSource() == deleteCityButton){
                    current = "delete cities";
                }else if(e.getSource() == deleteTournamentButton){
                    current = "delete tournaments";
                }
            }
        }
        if(e.getSource() == yesButton){
            playerDisplayTA.setText(null);
            cityDisplayTA.setText(null);
            tournamentDisplayTA.setText(null);
            dataAlreadyShown = false;
            if(current == "delete player"){
                Player.deletePlayers();
            }else if(current == "delete city"){
                City.deleteCities();
            }else if(current == "delete tournament"){
                Tournament.deleteTournaments();
            }
            returnToMenu();
        }else if(e.getSource() == noButton){
            returnToMenu();
        }
        if(e.getSource() == returnButton){
            returnToMenu();
        }
    }
    
//    Ana menu elementlerini saklayan method.
    public static void exitMenu(){
        addPlayerButton.setVisible(false);
        addCityButton.setVisible(false);
        addTournamentButton.setVisible(false);
        deletePlayerButton.setVisible(false);
        deleteCityButton.setVisible(false);
        deleteTournamentButton.setVisible(false);
        beginTournamentButton.setVisible(false);
        seeSavedData.setVisible(false);
    }
    
//    Ana menuye geri donmek icin method.
    public static void returnToMenu(){
        continueButton.setVisible(false);
        returnButton.setVisible(false);
        playerNameTF.setVisible(false);
        playerSurnameTF.setVisible(false);
        playerAgeTF.setVisible(false);
        playerOriginTF.setVisible(false);
        playerGenderTF.setVisible(false);
        playerNameL.setVisible(false);
        playerSurnameL.setVisible(false);
        playerAgeL.setVisible(false);
        playerOriginL.setVisible(false);
        playerGenderL.setVisible(false);
        invalidInput.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        areYouSure.setVisible(false);
        playerDisplaySP.setVisible(false);
        playerDisplayTA.setVisible(false);
        cityDisplaySP.setVisible(false);
        cityDisplayTA.setVisible(false);
        tournamentDisplaySP.setVisible(false);
        tournamentDisplayTA.setVisible(false);
        playerDataL.setVisible(false);
        cityDataL.setVisible(false);
        tournamentDataL.setVisible(false);
        cityTF.setVisible(false);
        tournamentTF.setVisible(false);
        cityL.setVisible(false);
        tournamentL.setVisible(false);
        playerCountTF.setVisible(false);
        playerCountL.setVisible(false);
        notEnough.setVisible(false);
        tournamentPlayersL.setVisible(false);
        playerOneL.setVisible(false);
        playerTwoL.setVisible(false);
        dashL.setVisible(false);
        playerOneScoreTF.setVisible(false);
        playerTwoScoreTF.setVisible(false);
        playerOneScoreL.setVisible(false);
        playerTwoScoreL.setVisible(false);
        addPlayerButton.setVisible(true);
        addCityButton.setVisible(true);
        addTournamentButton.setVisible(true);
        deletePlayerButton.setVisible(true);
        deleteCityButton.setVisible(true);
        deleteTournamentButton.setVisible(true);
        beginTournamentButton.setVisible(true);
        seeSavedData.setVisible(true);
    }
}
