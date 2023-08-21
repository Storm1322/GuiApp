package com.mycompany.gameapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Player {

    public String name;
    public String surname;
    public String age;
    public String origin;
    public String gender;
    public int matchesWon;

    public Player(String n, String s, String a, String o, String g) {
        name = n;
        surname = s;
        age = a;
        origin = o;
        gender = g;
        matchesWon = 0;
    }

    public Player() {
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

    //    Player isimlerini text dosyasina depolamak icin method.
    public static void storePlayers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, players);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    Depolanan player isimlerini yuklemek icin method.
    public static void importPlayers() {
        if (players.isEmpty() == false) {
            players.clear();
        }
        if (playerArray.isEmpty() == false) {
            playerArray.clear();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (!file.exists()) {
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
