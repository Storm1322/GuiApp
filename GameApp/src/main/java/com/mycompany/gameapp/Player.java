package com.mycompany.gameapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.mycompany.gameapp.GameApp.checkInput;
import static com.mycompany.gameapp.GameApp.checkInputForString;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Player implements ActionListener{
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
    
    public static List<Player> players = new ArrayList<>();
    public static final File file = new File("Player.json");
    public static List<List<String>> playerArray = new ArrayList<>();
    public List<String> playerStatArray = new ArrayList<>();
    public static String playerName;
    public static String playerSurname;
    public static String playerAge;
    public static String playerOrigin;
    public static String playerGender;
    public static String[][] array;
    public static String[] playerTable = {"Name", "Surname", "Age", "Origin", "Gender", "Match Wins"};
    
    public static void showPlayers(){
        for(Player player: players){
            String playerDisplay = player.name + " " + player.surname;
            GameApp.playerDisplayTA.append(playerDisplay + "\n");
        }
    }
    
    public static void deletePlayers(){
        
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
    @SuppressWarnings("empty-statement")
    public static void importPlayers(){
        if(players.isEmpty() == false){
            players.clear();
        }
        if(playerArray.isEmpty() == false){
            playerArray.clear();
        }
        ObjectMapper mapper = new ObjectMapper();
        try{
            if(!file.exists()){
                Path f = Paths.get("Player.json");
                Files.createFile(f);
            }
            
            Player[] playersTemp = mapper.readValue(file, Player[].class);
            for (Player playersTemp1 : playersTemp) {
                players.add(playersTemp1);
                String matchesWonStr = String.valueOf(playersTemp1.matchesWon);
                Player accessor = new Player();
                accessor.playerStatArray.add(playersTemp1.name);
                accessor.playerStatArray.add(playersTemp1.surname);
                accessor.playerStatArray.add(playersTemp1.age);
                accessor.playerStatArray.add(playersTemp1.origin);
                accessor.playerStatArray.add(playersTemp1.gender);
                accessor.playerStatArray.add(matchesWonStr);
                playerArray.add(accessor.playerStatArray);
                accessor.playerStatArray = new ArrayList<>();
            }
            
            array = new String[players.size()][6];
            int i = 0;
            for (List<String> nestedList : playerArray) {
                array[i++] = nestedList.toArray(new String[0]);
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
            ex.printStackTrace();
        }
    }
    
    public static void registerPlayers(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameApp.current == "adding player"){
            if(false){
                Player player = new Player(playerName, playerSurname, playerAge, playerOrigin, playerGender);
                players.add(player);
                storePlayers();
                GameApp.playerDisplayTA.setText(null);
                GameApp.cityDisplayTA.setText(null);
                GameApp.tournamentDisplayTA.setText(null);
                GameApp.dataAlreadyShown = false;
                showMessageDialog(null, "Successfully added a player.");
                GameApp.returnToMenu();
            }else{
                GameApp.invalidInput.setVisible(true);
            }
        }
    }
}
