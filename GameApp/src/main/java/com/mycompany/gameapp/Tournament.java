package com.mycompany.gameapp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tournament{
    
    public static List<String> tournaments = new ArrayList<>();
    public static String[][] tournamentArray;
    public static String[] tournamentHeadline = {"Tournament Name"};;
    public static int tournamentPlayerCount;
    
//    Eklenen turnuvalari text fileda depolamak icin method.
    public static void storeTournaments(){
        Path file = Paths.get("Tournaments.txt");
        try{
            Files.write(file, tournaments, StandardCharsets.UTF_8);
        }catch(IOException ex){
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Depolanan turnuvalari loadlamak icin method.
    public static void importTournaments(){
        if(tournaments.isEmpty() == false){
            tournaments.clear();
        }
        Path file = Paths.get("Tournaments.txt");
        try{
            tournaments = Files.readAllLines(file);
            tournamentArray = new String[tournaments.size()][1];
            int i = 0;
            for(String city: tournaments){
                String[] array = new String[1];
                array[0] = city;
                tournamentArray[i++] = array;
            }
        }catch(IOException ex){
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Katilimci sayisi 2'nin kuvveti mi kontrol etmek icin method.
    public static boolean powerOfTwo(int n){
        if(n < 2){
            return false;
        }else{
            while(n % 2 == 0){
                n = n / 2;
            }
            return n == 1;
        }
    }
}
