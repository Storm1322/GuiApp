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

public class City{
    
    public static List<String> cities = new ArrayList<>();
    public static String[][] cityArray;
    public static String[] cityHeadline = {"City Name"};;
    
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
        if(cities.isEmpty() == false){
            cities.clear();
        }
        Path file = Paths.get("Cities.txt");
        try{
            cities = Files.readAllLines(file);
            cityArray = new String[cities.size()][1];
            int i = 0;
            for(String city: cities){
                String[] array = new String[1];
                array[0] = city;
                cityArray[i++] = array;
            }
        }catch (IOException ex){
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
