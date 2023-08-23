package com.mycompany.gameapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Tournament{
    
    public static List<Tournament> tournaments = new ArrayList<>();
    public List<String> tournamentInfoArray = new ArrayList<>();
    public static List<List<String>> tournamentList = new ArrayList<>();
    public static String[][] tournamentArray;
    public static String[] tournamentHeadline = {"Tournament Name"};
    public static final File file = new File("Tournament.json");
    public static int tournamentPlayerCount;
    public String name;
    
        public Tournament(){
        }
        
        public Tournament(String n){
            name = n;
        }
    
//    Eklenen turnuvalari text fileda depolamak icin method.
    public static void storeTournaments(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, tournaments);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
//    Depolanan turnuvalari loadlamak icin method.
    public static void importTournaments(){
        if(tournaments.isEmpty() == false){
            tournaments.clear();
        }
        if (tournamentList.isEmpty() == false) {
            tournamentList.clear();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (!file.exists()) {
                Path f = Paths.get("Tournament.json");
                Files.createFile(f);
            }
            Tournament[] tournamentsTemp = mapper.readValue(file, Tournament[].class);
            for (Tournament tournamentsTemp1 : tournamentsTemp) {
                tournaments.add(tournamentsTemp1);
                Tournament accessor = new Tournament();
                accessor.tournamentInfoArray.add(tournamentsTemp1.name);
                tournamentList.add(accessor.tournamentInfoArray);
                accessor.tournamentInfoArray = new ArrayList<>();
            }
            tournamentArray = new String[tournaments.size()][1];
            int i = 0;
            for (List<String> nestedList : tournamentList) {
                tournamentArray[i++] = nestedList.toArray(new String[0]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void saveResults(){
        
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
