package com.mycompany.gameapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;

public class Simulation implements ActionListener{
    
    public static ArrayList<Integer> pickedNumbers = new ArrayList<>();
    public static ArrayList<String> playingPlayers = new ArrayList<>();
    public static List<Player> playingPlayersObjects = new ArrayList<>();
    public static List<Player> currentPlayersObjects = new ArrayList<>();
    public static List<Player> nextRoundObjects = new ArrayList<>();
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
        simulateTournament();
    }
    
//    Turnuvayi simule eden method.
    public static void simulateTournament(){
//        Tek oyuncu kalinca kazanan olarak printle.
        if (playingPlayersObjects.size() == 1) {
            startAgain();
        }
        matchCount++;
//        Rastgele 2 oyuncuyu eslestir.
        Random rand = new Random();
        int firstPlayerIndex = rand.nextInt(playingPlayersObjects.size());
        int secondPlayerIndex = rand.nextInt(playingPlayersObjects.size());
//        Iki random index birbirine esitse 2. indexten 1 cikar veya ekle.
        if (firstPlayerIndex == secondPlayerIndex && firstPlayerIndex > 0) {
            secondPlayerIndex = secondPlayerIndex - 1;
        } else if (firstPlayerIndex == secondPlayerIndex && firstPlayerIndex == 0) {
            secondPlayerIndex = secondPlayerIndex + 1;
        }
        currentPlayersObjects.add(playingPlayersObjects.get(firstPlayerIndex));
        currentPlayersObjects.add(playingPlayersObjects.get(secondPlayerIndex));
        
        playerOneSetScore = 0;
        playerTwoSetScore = 0;
        
        playingPlayers.clear();
        for(Player player: playingPlayersObjects){
            playingPlayers.add(player.name + " " + player.surname);
        }
        tournamentInfoDisplay();
    }
    
    public static void tournamentInfoDisplay(){
        GameApp.tournamentPlayersL.setText("Players of this round are: " + playingPlayers);
        GameApp.tournamentPlayersL.setVisible(true);
        GameApp.continueButton.setVisible(true);
    }
    
//    Userdan skor girdisi istemek icin method.
    private static void scoreEntry() {
        showMessageDialog(null, "Starting tournament simulation.\n You are expected to enter set scores for both players. \n Enter 60 for player and either 45, 30, 15 or 0 for the other. \n First player to 4 sets wins.");
        
        String firstPlayer = currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname;
        String secondPlayer = currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname;
        
//        2 set kazanmis oyuncu var mi check.
        if (playerOneSetScore == 4) {
            currentPlayersObjects.get(0).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(0));
            currentPlayersObjects.clear();
            Player.storePlayers();
        } else if (playerTwoSetScore == 4) {
            int winnerInt = playingPlayersObjects.indexOf(currentPlayersObjects.get(1));
            playingPlayersObjects.get(winnerInt).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(1));
            currentPlayersObjects.clear();
            Player.storePlayers();
        } else {
            playerOneScoreEntry();
        }
    }
    
    //    Birinci playerin skor girdi methodu.
    private static void playerOneScoreEntry(){
        String firstPlayer = currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname;
        GameApp.playerOneScoreTF.setVisible(true);
    }
    
//    Ikinci playerin skor girdi methodu.
    private static void playerTwoScoreEntry(){
        String secondPlayer = currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname;
    }
    
    private static void scoreCheck(){
        String firstPlayer = currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname;
        String secondPlayer = currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname;
//            Izin verilmeyen skorlar icin check ve tekrar girdi iste.
            if (playerOneScore != 60 && playerTwoScore != 60) {
                System.out.println("Impossible score. Re-enter scores.");
                scoreEntry();
            } else if (playerOneScore == 60 && playerTwoScore == 60) {
                System.out.println("Impossible score. Please re-enter the results.");
                scoreEntry();
            } else if (playerTwoScore != 0 && playerTwoScore != 15 && playerTwoScore != 30 && playerTwoScore != 45 && playerOneScore == 60) {
                System.out.println("Impossible score. Please re-enter the results.");
                scoreEntry();
            } else if (playerOneScore != 0 && playerOneScore != 15 && playerOneScore != 30 && playerOneScore != 45 && playerTwoScore == 60) {
                System.out.println("Impossible score. Please re-enter the scores.");
                scoreEntry();
//            Skorlara gore kazanani belirle.
            } else {
                switch (playerOneScore) {
                    case 0:
                        System.out.println(secondPlayer + " has won the set.");
                        System.out.println("");
                        playerTwoSetScore++;
                        scoreEntry();
                        break;

                    case 15:
                        System.out.println(secondPlayer + " has won the set.");
                        System.out.println("");
                        playerTwoSetScore++;
                        scoreEntry();
                        break;

                    case 30:
                        System.out.println(secondPlayer + " has won the set.");
                        System.out.println("");
                        playerTwoSetScore++;
                        scoreEntry();
                        break;

                    case 45:
                        System.out.println(secondPlayer + " has won the set.");
                        System.out.println("");
                        playerTwoSetScore++;
                        scoreEntry();
                        break;

                    case 60:
                        System.out.println(firstPlayer + " has won the set.");
                        System.out.println("");
                        playerOneSetScore++;
                        scoreEntry();
                        break;

                    default:
                        System.out.println("Invalid entry.");
            }
        }
    }
    
//    Yeniden baslatmak icin method.
    public static void startAgain(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == GameApp.continueButton){
            GameApp.playerOneL.setText(currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname + " " + playerOneScore);
            GameApp.playerTwoL.setText(playerTwoScore + " " + currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname);
            GameApp.playerOneL.setVisible(true);
            GameApp.playerTwoL.setVisible(true);
            GameApp.dashL.setVisible(true);
            GameApp.tournamentPlayersL.setVisible(false);
            GameApp.continueButton.setVisible(false);
            scoreEntry();
        }else if(e.getSource() == GameApp.playerOneScoreTF){
            
        }else if(e.getSource() == GameApp.playerTwoScoreTF){
            
        }
    }
}
