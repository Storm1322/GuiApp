package com.mycompany.tennistournamentclassified;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import static com.mycompany.tennistournamentclassified.Tournament.tournamentPlayerCount;
import static com.mycompany.tennistournamentclassified.Player.players;
import java.util.List;

public class Simulation {

    public static ArrayList<Integer> pickedNumbers = new ArrayList<>();
    public static List<Player> playingPlayersObjects = new ArrayList<>();
    public static List<Player> currentPlayersObjects = new ArrayList<>();
    public static List<Player> nextRoundObjects = new ArrayList<>();
    public static int matchCount = 0;
    public static int roundCount = 1;
    public static Scanner scan = new Scanner(System.in);
    static int playerOneSetScore = 0;
    static int playerTwoSetScore = 0;
    static int playerOneScore;
    static int playerTwoScore;

//    Katilan oyunculari tanimlanan oyuncular arasindan rastgele secmek icin metod.
    public static void randomizer() {
        Random rand = new Random();
        Player player = new Player();
        for (int i = 0; i < tournamentPlayerCount;) {
            int playerInt = rand.nextInt(players.size());
            if(pickedNumbers.contains(playerInt)){
                continue;
            }else{
                pickedNumbers.add(playerInt);
                player = players.get(playerInt);
                playingPlayersObjects.add(player);
                i++;
            }
        }
    }
    
//    Turnuvayi simule eden method.
    public static void simulateTournament() {
//        Tek oyuncu kalinca kazanan olarak printle.
        if (playingPlayersObjects.size() == 1) {
            System.out.println("The winner of the tournament is: " + playingPlayersObjects.get(0).name + " " + playingPlayersObjects.get(0).surname + ". He has won " + playingPlayersObjects.get(0).matchesWon + " matches until now.");
            System.out.println("");
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

        System.out.println("The players of this match are " + playingPlayersObjects.get(firstPlayerIndex).name + " " + playingPlayersObjects.get(firstPlayerIndex).surname + " and " + playingPlayersObjects.get(secondPlayerIndex).name + " " + playingPlayersObjects.get(secondPlayerIndex).surname);
        System.out.println("");
        playerOneSetScore = 0;
        playerTwoSetScore = 0;
//        Skorlari iste.
        scoreEntry();

//        Indexi buyuk olan playeri once sil.
        if (firstPlayerIndex > secondPlayerIndex) {
            playingPlayersObjects.remove(firstPlayerIndex);
            playingPlayersObjects.remove(secondPlayerIndex);
        } else {
            playingPlayersObjects.remove(secondPlayerIndex);
            playingPlayersObjects.remove(firstPlayerIndex);
        }
//        Rounddaki tum maclar bitince sonraki rounda gec.
        if (matchCount == tournamentPlayerCount / Math.pow(2, roundCount)) {
            roundCount++;
            matchCount = 0;
            playingPlayersObjects.addAll(nextRoundObjects);
            if(playingPlayersObjects.size() == 1) {
            } else {
                ArrayList<String> playingPlayers = new ArrayList<>();
                playingPlayers.clear();
                for(Player player: playingPlayersObjects){
                    playingPlayers.add(player.name + " " + player.surname);
                }
                System.out.println("Players moving over to the next round are: " + playingPlayers);
            }
            nextRoundObjects.clear();
            simulateTournament();
        }
        simulateTournament();
    }

//    Userdan skor girdisi istemek icin method.
    private static void scoreEntry() {
        String firstPlayer = currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname;
        String secondPlayer = currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname;
        System.out.println("The current set scores are:");
        System.out.println(firstPlayer + " " + playerOneSetScore + " - " + playerTwoSetScore + " " + secondPlayer);
        System.out.println("");

//        2 set kazanmis oyuncu var mi check.
        if (playerOneSetScore == 4) {
            System.out.println(firstPlayer + " has won the match.");
            System.out.println("");
            currentPlayersObjects.get(0).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(0));
            currentPlayersObjects.clear();
            Player.storePlayers();
        } else if (playerTwoSetScore == 4) {
            System.out.println(secondPlayer + " has won the match.");
            System.out.println("");
            int winnerInt = playingPlayersObjects.indexOf(currentPlayersObjects.get(1));
            playingPlayersObjects.get(winnerInt).matchesWon++;
            nextRoundObjects.add(currentPlayersObjects.get(1));
            currentPlayersObjects.clear();
            Player.storePlayers();
        } else {
            System.out.println("Please enter the results. (60 for one player and either 0, 15, 30 or 45 for the other. First to four sets wins.)");
            System.out.println("");
            playerOneScoreEntry();
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
    }
    
//    Birinci playerin skor girdi methodu.
    private static void playerOneScoreEntry(){
        String firstPlayer = currentPlayersObjects.get(0).name + " " + currentPlayersObjects.get(0).surname;
        System.out.println("Please enter score for player " + firstPlayer);
        String playerOneScoreStr = scan.nextLine();
//        Integer olmayan input var mi check.
        boolean hasNonInteger = Player.checkInput(playerOneScoreStr);
//        Varsa tekrar sor.
        if (hasNonInteger == true){
            System.out.println("You have entered an incorrect input. Please try again.");
            System.out.println("");
            playerOneScoreEntry();
        }else{
            playerOneScore = Integer.parseInt(playerOneScoreStr);
            if(playerOneScore != 60 && playerOneScore != 45 && playerOneScore != 30 && playerOneScore != 15 && playerOneScore != 0){
                System.out.println("Invalid entry. Try again.");
                playerOneScoreEntry();
            }else{
                playerTwoScoreEntry();
            }
        }
    }
    
//    Ikinci playerin skor girdi methodu.
    private static void playerTwoScoreEntry(){
        String secondPlayer = currentPlayersObjects.get(1).name + " " + currentPlayersObjects.get(1).surname;
        System.out.println("Please enter score for player " + secondPlayer);
        String playerTwoScoreStr = scan.nextLine();
//        Integer olmayan input var mi check.
        boolean hasNonInteger = Player.checkInput(playerTwoScoreStr);
//        Varsa tekrar sor.
        if (hasNonInteger == true){
            System.out.println("You have entered an incorrect input. Please try again.");
            System.out.println("");
            playerTwoScoreEntry();
        }else{
            playerTwoScore = Integer.parseInt(playerTwoScoreStr);
            if(playerTwoScore != 60 && playerTwoScore != 45 && playerTwoScore != 30 && playerTwoScore != 15 && playerTwoScore != 0){
                System.out.println("Invalid entry. Try again.");
                playerTwoScoreEntry();
            }
        }
    }
    
//    Yeniden baslatmak icin method.
    public static void startAgain(){
        System.out.println("Would you like to start a new tournament? (y/n)");
        String startNew = scan.nextLine();
        if(startNew.equalsIgnoreCase("y")){
            System.out.println("Starting a new tournament.");
            System.out.println("");
            TennisTournamentClassified.main(null);
        }else if(startNew.equalsIgnoreCase("n")){
            System.out.println("Exiting program.");
            System.exit(0);
        }else{
            System.out.println("Invalid input. Try again.");
            startAgain();
        }
    }
}