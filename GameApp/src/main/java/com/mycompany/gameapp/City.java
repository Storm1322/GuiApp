package com.mycompany.gameapp;

import static com.mycompany.gameapp.GameApp.checkInputForString;
import static com.mycompany.gameapp.GameApp.cityDisplayTA;
import static com.mycompany.gameapp.GameApp.cityTF;
import static com.mycompany.gameapp.GameApp.dataAlreadyShown;
import static com.mycompany.gameapp.GameApp.invalidInput;
import static com.mycompany.gameapp.GameApp.playerDisplayTA;
import static com.mycompany.gameapp.GameApp.tournamentDisplayTA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

public class City implements ActionListener{
    
    public static List<String> cities = new ArrayList<>();
    public static String[][] cityArray;
    public static String[] cityHeadline = {"City Name"};;
    
    public static void showCities(){
        for(String city: cities){
            GameApp.cityDisplayTA.append(city + "\n");
        }
    }
    
    //    Eklenen sehirleri text fileda depolamak icin method.
    public static void storeCities(){
        Path file = Paths.get("Cities.txt");
        try {
            Files.write(file, cities, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Depolanan sehirleri loadlamak icin method.
    public static void importCities(){
        Path file = Paths.get("Cities.txt");
        try{
            cities = Files.readAllLines(file);
            
            cityArray = new String[cities.size()][1];
            int i = 0;
            for(String city: cities){
                String[] array = new String[1];
                array[0] = city;
                cityArray[i++] = array;
                System.out.println(cityArray);
            }
           }catch (IOException ex){
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addCity(){
        GameApp.cityTF.setVisible(true);
        GameApp.cityL.setVisible(true);
        GameApp.continueButton.setVisible(true);
        GameApp.returnButton.setVisible(true);
    }
    
    public static void deleteCities(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
