package com.mycompany.tennistournamentclassified;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.mycompany.tennistournamentclassified.TennisTournamentClassified.checkInputForString;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Player {
    public String name;
    public String surname;
    public String age;
    public String origin;
    public String gender;
    public int matchesWon;
    
    public Player(String n, String s, String a, String o, String g){
        name = n;
        surname = s;
        age = a;
        origin = o;
        gender = g;
        matchesWon = 0;
    }
    public Player(){    
    }
    
    public static Scanner scan = new Scanner(System.in);
    public static List<Player> players = new ArrayList<>();
    public static final File file = new File("Player.json");
    public static List<String> displayPlayers = new ArrayList<>();
    public static String playerName;
    public static String playerSurname;
    public static String playerAge;
    public static String playerOrigin;
    public static String playerGender; 
    public static int playerBeingAdded;
    
//    Player tanimlamak icin method.
    public static void registerPlayers(){
        System.out.println("How many players would you like to add? (Enter a number.)");
        String playerCountStr = scan.nextLine();
//        Integer olmayan input var mi check.
        boolean hasNonInteger = Player.checkInput(playerCountStr);
//        Varsa tekrar sor.
        if(hasNonInteger == true){
            System.out.println("You have entered an incorrect input. Please try again.");
            registerPlayers();
        }else{
            int playerCount = Integer.parseInt(playerCountStr); 
            for(playerBeingAdded = 1; playerBeingAdded <= playerCount; playerBeingAdded++){
                enterPlayerName();
                Player player = new Player(playerName, playerSurname, playerAge, playerOrigin, playerGender);
                players.add(player);
            }
            storePlayers();
            TennisTournamentClassified.addMore();
        }
    }
    
//    Player ismi istemek icin method.
    public static void enterPlayerName(){
        System.out.println("Please write player number " + playerBeingAdded + "'s name(without surname) below:");
        playerName = scan.nextLine();
        if(checkInputForString(playerName) == true){
            System.out.println("Invalid input. Please try again.");
            enterPlayerName();
        }else{
            enterPlayerSurname();
        }
    }
    
//    Player soyismi istemek icin method.
    public static void enterPlayerSurname(){
        System.out.println("Please write player number " + playerBeingAdded + "'s surname below:");
        playerSurname = scan.nextLine();
        if(checkInputForString(playerSurname) == true){
            System.out.println("Invalid input. Please try again.");
            enterPlayerSurname();
        }else{
            enterPlayerAge();
        }
    }
    
//    Player yasi istemek icin method.
    public static void enterPlayerAge(){
        System.out.println("Please write player number " + playerBeingAdded + "'s age below:");
        playerAge = scan.nextLine();
        if(checkInput(playerAge) == true){
            System.out.println("Invalid input. Please try again.");
            enterPlayerAge();
        }else{
            enterPlayerOrigin();
        }
    }
    
//    Player ulkesi istemek icin method.
    public static void enterPlayerOrigin(){
        System.out.println("Please write player number " + playerBeingAdded + "'s country of origin below:");
        playerOrigin = scan.nextLine();
        if(checkInputForString(playerOrigin) == true){
            System.out.println("Invalid input. Please try again.");
            enterPlayerOrigin();
        }else{
            enterPlayerGender();
        }
    }
    
//    Player cinsiyeti istemek icin method.
    public static void enterPlayerGender(){
        System.out.println("Please write player number " + playerBeingAdded + "'s gender (Male/Female) below:");
        playerGender = scan.nextLine();
        if(!"male".equalsIgnoreCase(playerGender) == true && !"female".equalsIgnoreCase(playerGender) == true){
            System.out.println("Invalid input. Please try again.");
            enterPlayerGender();
        }else{
        }
    }

//    Player isimlerini text dosyasina depolamak icin method.
    public static void storePlayers(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.writeValue(file, players);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

//    Depolanan player isimlerini yuklemek icin method.
    public static void importPlayers(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            if(!file.exists()){
                Path f = Paths.get("Player.json");
                Files.createFile(f);
            }
            Player[] playersTemp = mapper.readValue(file, Player[].class);
            for (Player playersTemp1 : playersTemp) {
                players.add(playersTemp1);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

//    Kaydedilmis playerlari silmek icin method.
    public static void clearPlayers(){
        try {
            Path toDelete = Paths.get("Player.json");
            Files.delete(toDelete);
            Files.createFile(toDelete);
            players.clear();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
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
}