package com.mycompany.gameapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Simulation implements ActionListener{
    
    public static ArrayList<Integer> pickedNumbers = new ArrayList<>();
    public static ArrayList<String> playingPlayers = new ArrayList<>();
    public static String[][] playingPlayersArray;
    public static String[] participants = {"Participants"};
    public static List<Player> playingPlayersObjects = new ArrayList<>();
    public static List<Player> currentPlayersObjects = new ArrayList<>();
    public static List<Player> nextRoundObjects = new ArrayList<>();
    public static int firstPlayerIndex;
    public static int secondPlayerIndex;
    public static int matchCount = 0;
    public static int roundCount = 1;
    static int playerOneSetScore = 0;
    static int playerTwoSetScore = 0;
    static int playerOneScore;
    static int playerTwoScore;
    
//    Katilan oyunculari tanimlanan oyuncular arasindan rastgele secmek icin metod.
    public static void randomizer() {
        Random rand = new Random();
        Player player = new Player();
        for (int i = 0; i < Tournament.tournamentPlayerCount;) {
            int playerInt = rand.nextInt(Player.players.size());
            if(pickedNumbers.contains(playerInt)){
                continue;
            }else{
                pickedNumbers.add(playerInt);
                player = Player.players.get(playerInt);
                playingPlayersObjects.add(player);
                i++;
            }
        }
        beginTournament();
    }
    
    public static void beginTournament(){
        playingPlayers.clear();
        playingPlayersArray = new String[Tournament.tournamentPlayerCount][1];
        int i = 0;
        for(Player player: playingPlayersObjects){
            String[] array = new String[1];
            array[0] = player.name + " " + player.surname;
            playingPlayersArray[i++] = array;
            playingPlayers.add(player.name + " " + player.surname);
        }
    }
    
//    Turnuvayi simule eden method.
    public static void simulateTournament(){
//        Tek oyuncu kalinca kazanan olarak printle.
        if (playingPlayersObjects.size() == 1) {
        //        Rounddaki tum maclar bitince sonraki rounda gec.
        }else if (matchCount == Tournament.tournamentPlayerCount / Math.pow(2, roundCount)) {
            roundCount++;
            matchCount = 0;
            playingPlayersObjects.addAll(nextRoundObjects);
            if(playingPlayersObjects.size() == 1) {
            } else {
                playingPlayers.clear();
                for(Player player: playingPlayersObjects){
                    playingPlayers.add(player.name + " " + player.surname);
                }
            }
            nextRoundObjects.clear();
            playingPlayers.clear();
        }else{
        for(Player player: playingPlayersObjects){
            playingPlayers.add(player.name + " " + player.surname);
        }
        if(playingPlayers.size() > 1){
            matchCount++;
//            Rastgele 2 oyuncuyu eslestir.
            Random rand = new Random();
            firstPlayerIndex = rand.nextInt(playingPlayersObjects.size());
            secondPlayerIndex = rand.nextInt(playingPlayersObjects.size());
//            Iki random index birbirine esitse 2. indexten 1 cikar veya ekle.
            if (firstPlayerIndex == secondPlayerIndex && firstPlayerIndex > 0) {
                secondPlayerIndex = secondPlayerIndex - 1;
            } else if (firstPlayerIndex == secondPlayerIndex && firstPlayerIndex == 0) {
                secondPlayerIndex = secondPlayerIndex + 1;
            }
            currentPlayersObjects.add(playingPlayersObjects.get(firstPlayerIndex));
            currentPlayersObjects.add(playingPlayersObjects.get(secondPlayerIndex));
            
            playerOneSetScore = 0;
            playerTwoSetScore = 0;
        }
        }
    }
    
    public static void removePlayedPlayers(){
//        Indexi buyuk olan playeri once sil.
        if (firstPlayerIndex > secondPlayerIndex) {
            playingPlayersObjects.remove(firstPlayerIndex);
            playingPlayersObjects.remove(secondPlayerIndex);
        } else {
            playingPlayersObjects.remove(secondPlayerIndex);
            playingPlayersObjects.remove(firstPlayerIndex);
        }
    }
    
//    Userdan skor girdisi istemek icin method.
    private static void winnerCheck() {
//        2 set kazanmis oyuncu var mi check.
        if (playerOneSetScore == 4) {
            currentPlayersObjects.get(0).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(0));
            currentPlayersObjects.clear();
            SimulateTournamentMenu.playerOneL.setVisible(false);
            SimulateTournamentMenu.playerTwoL.setVisible(false);
            Player.storePlayers();
            removePlayedPlayers();
            SimulateTournamentMenu.simulateTournament();
        } else if (playerTwoSetScore == 4) {
            int winnerInt = playingPlayersObjects.indexOf(currentPlayersObjects.get(1));
            playingPlayersObjects.get(winnerInt).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(1));
            currentPlayersObjects.clear();
            SimulateTournamentMenu.playerOneL.setVisible(false);
            SimulateTournamentMenu.playerTwoL.setVisible(false);
            Player.storePlayers();
            removePlayedPlayers();
            SimulateTournamentMenu.simulateTournament();
        } else {
            SimulateTournamentMenu.playerOneScoreEntry();
        }
    }
    
    protected static void scoreCheck(){
//            Izin verilmeyen skorlar icin check ve tekrar girdi iste.
            if (playerOneScore != 60 && playerTwoScore != 60) {
                SimulateTournamentMenu.invalidInput.setVisible(true);
                SimulateTournamentMenu.playerOneScoreEntry();
            } else if (playerOneScore == 60 && playerTwoScore == 60) {
                SimulateTournamentMenu.invalidInput.setVisible(true);
                SimulateTournamentMenu.playerOneScoreEntry();
            } else if (playerTwoScore != 0 && playerTwoScore != 15 && playerTwoScore != 30 && playerTwoScore != 45 && playerOneScore == 60) {
                SimulateTournamentMenu.invalidInput.setVisible(true);
                SimulateTournamentMenu.playerOneScoreEntry();
            } else if (playerOneScore != 0 && playerOneScore != 15 && playerOneScore != 30 && playerOneScore != 45 && playerTwoScore == 60) {
                SimulateTournamentMenu.invalidInput.setVisible(true);
                SimulateTournamentMenu.playerOneScoreEntry();
//            Skorlara gore kazanani belirle.
            } else {
                GameApp.invalidInput.setVisible(false);
                switch (playerOneScore) {
                    case 0:
                        playerTwoSetScore++;
                        SimulateTournamentMenu.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + "    " + playerOneSetScore);
                        SimulateTournamentMenu.playerTwoL.setText(playerTwoSetScore + "    " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
                        winnerCheck();
                        break;

                    case 15:
                        playerTwoSetScore++;
                        SimulateTournamentMenu.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + "    " + playerOneSetScore);
                        SimulateTournamentMenu.playerTwoL.setText(playerTwoSetScore + "    " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
                        winnerCheck();
                        break;

                    case 30:
                        playerTwoSetScore++;
                        SimulateTournamentMenu.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + "    " + playerOneSetScore);
                        SimulateTournamentMenu.playerTwoL.setText(playerTwoSetScore + "    " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
                        winnerCheck();
                        break;

                    case 45:
                        playerTwoSetScore++;
                        SimulateTournamentMenu.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + "    " + playerOneSetScore);
                        SimulateTournamentMenu.playerTwoL.setText(playerTwoSetScore + "    " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
                        winnerCheck();
                        break;

                    case 60:
                        playerOneSetScore++;
                        SimulateTournamentMenu.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + "    " + playerOneSetScore);
                        SimulateTournamentMenu.playerTwoL.setText(playerTwoSetScore + "    " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
                        winnerCheck();
                        break;
            }
        }
    }
    
//    Yeniden baslatmak icin method.
    public static void startAgain(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameApp.current == "tournament info display"){
            if(e.getSource() == GameApp.continueButton){
                GameApp.dashL.setVisible(true);
                GameApp.tournamentPlayersL.setVisible(false);
                GameApp.continueButton.setVisible(false);
                showMessageDialog(null, "Starting tournament simulation.\n You are expected to enter set scores for both players. \n Enter 60 for one player and either 45, 30, 15 or 0 for the other. \n First player to 4 sets wins.");
                GameApp.current = "simulating tournament";
                simulateTournament();
            }
        }else if(GameApp.current == "moving to next round"){
            GameApp.current = "simulating tournament";
            GameApp.tournamentPlayersL.setVisible(false);
            simulateTournament();
        }else if(e.getSource() == GameApp.playerOneScoreTF){
        }else if(e.getSource() == GameApp.playerTwoScoreTF){
            if(GameApp.checkInput(GameApp.playerTwoScoreTF.getText()) == false){
                playerTwoScore = Integer.parseInt(GameApp.playerTwoScoreTF.getText());
                if(playerTwoScore != 60 && playerTwoScore != 45 && playerTwoScore != 30 && playerTwoScore != 15 && playerTwoScore != 0){
                    GameApp.invalidInput.setVisible(true);
                }else{
                    GameApp.playerTwoScoreTF.setVisible(false);
                    GameApp.playerTwoScoreTF.removeAll();
                    GameApp.invalidInput.setVisible(false);
                    scoreCheck();
                }
            }else{
                GameApp.invalidInput.setVisible(true);
            }
        }
    }
}
