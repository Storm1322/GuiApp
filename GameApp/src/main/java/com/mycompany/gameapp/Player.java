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
import static com.mycompany.gameapp.GameApp.playerAgeTF;
import static com.mycompany.gameapp.GameApp.playerGenderTF;
import static com.mycompany.gameapp.GameApp.playerNameTF;
import static com.mycompany.gameapp.GameApp.playerOriginTF;
import static com.mycompany.gameapp.GameApp.playerSurnameTF;
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
    public static List<ArrayList<String>> playerArray = new ArrayList<>();
    public static ArrayList<String> playerStatArray = new ArrayList<>();
    public static String playerName;
    public static String playerSurname;
    public static String playerAge;
    public static String playerOrigin;
    public static String playerGender;
    
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
    public static void importPlayers(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            if(!file.exists()){
                Path f = Paths.get("Player.json");
                Files.createFile(f);
            }
            
            String playerTable[] = {"Name", "Surname", "Age", "Origin", "Gender", "Matches Won"};
            
            Player[] playersTemp = mapper.readValue(file, Player[].class);
            for (Player playersTemp1 : playersTemp) {
                players.add(playersTemp1);
                String matchesWonStr = String.valueOf(playersTemp1.matchesWon);
                playerStatArray.add(playersTemp1.name);
                playerStatArray.add(playersTemp1.surname);
                playerStatArray.add(playersTemp1.age);
                playerStatArray.add(playersTemp1.origin);
                playerStatArray.add(playersTemp1.gender);
                playerStatArray.add(matchesWonStr);
                playerArray.add(playerStatArray);
            }
            GameApp.showPlayers = new JTable((Object[][]) playerArray.toArray(), playerTable);
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
        playerAddMenu();
    }
    
    public static void playerAddMenu(){
        GameApp.returnButton.setVisible(true);
        GameApp.playerNameTF.setVisible(true);
        GameApp.playerSurnameTF.setVisible(true);
        GameApp.playerAgeTF.setVisible(true);
        GameApp.playerOriginTF.setVisible(true);
        GameApp.playerGenderTF.setVisible(true);
        GameApp.continueButton.setVisible(true);
        GameApp.playerNameL.setVisible(true);
        GameApp.playerSurnameL.setVisible(true);
        GameApp.playerAgeL.setVisible(true);
        GameApp.playerOriginL.setVisible(true);
        GameApp.playerGenderL.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(GameApp.current == "adding player"){
            if(checkInputForString(playerNameTF.getText()) == false && checkInputForString(playerSurnameTF.getText()) == false && checkInput(playerAgeTF.getText()) == false && checkInputForString(playerOriginTF.getText()) == false && checkInputForString(playerGenderTF.getText()) == false){
                playerName = playerNameTF.getText();
                playerSurname = playerSurnameTF.getText();
                playerAge = playerAgeTF.getText();
                playerOrigin = playerOriginTF.getText();
                playerGender = playerGenderTF.getText();
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
