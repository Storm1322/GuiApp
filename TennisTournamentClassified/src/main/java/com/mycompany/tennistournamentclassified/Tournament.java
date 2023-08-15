package com.mycompany.tennistournamentclassified;

import java.util.ArrayList;
import java.util.Scanner;
import static com.mycompany.tennistournamentclassified.Player.players;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tournament {
    
    public static List<String> tournaments = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);
    public static int tournamentPlayerCount;
    
//    Turnuva adi ekletmek icin method.
    public static void addTournament(){
        importTournaments();
        System.out.println("Please write the tournament's name below:");
        String tournamentName = scan.nextLine();
        tournaments.add(tournamentName);
        storeTournaments();
        TennisTournamentClassified.addMore();
    }
    
//    Eklenen turnuvalari text fileda depolamak icin method.
    public static void storeTournaments(){
        Path file = Paths.get("Tournaments.txt");
        try {
            Files.write(file, tournaments, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Depolanan turnuvalari loadlamak icin method.
    public static void importTournaments(){
        Path file = Paths.get("Tournaments.txt");
        try{
             tournaments = Files.readAllLines(file);
           } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Kaydedilmis turnuvalari silmek icin method.
    public static void clearTournaments(){
        try {
            Path file = Paths.get("Tournaments.txt");
            Files.delete(file);
            Files.createFile(file);
            tournaments.clear();
        } catch (IOException ex) {
        }
    }
    
//    Katilimci sayisi 2'nin kuvveti mi kontrol etmek icin method.
    public static boolean powerOfTwo(int n){
        while(n % 2 == 0){
            n = n / 2;
        }
        return n == 1;
    }
    
//    Turnuvanin katilimci sayisini ayarlamak icin method.
    public static void setSize(){
        System.out.println("Please set a size for the tournament. (Any power of 2: 2, 4, 8, 16...)");
        
        String tournamentPlayerCountStr = scan.nextLine();
//        Integer olmayan input var mi check.
        boolean hasNonInteger = Player.checkInput(tournamentPlayerCountStr);
//        Varsa tekrar sor.
        if(hasNonInteger == true){
            System.out.println("You have entered an incorrect input. Please try again.");
            setSize();
        }else{
            tournamentPlayerCount = Integer.parseInt(tournamentPlayerCountStr);
//            Istenen katilimci sayisi tanimlanan oyuncu sayisindan fazlaysa tekrar sor.
            if(players.size() - tournamentPlayerCount < 0){
                System.out.println("Not enough players defined for the set size, please try again.");
                setSize();
//            Katilimci sayisi 2'nin kuvveti degilse tekrar iste.
            }else if(powerOfTwo(tournamentPlayerCount) == false){
                System.out.println("You have selected an invalid number of players for the tournament. Please try again.");
                setSize();
            }else{
                System.out.println("Size has been successfully set. Picking " + tournamentPlayerCount + " players randomly.");
                System.out.println("");
                Simulation randSelect = new Simulation();
                randSelect.randomizer();
            }
        }
    }
}