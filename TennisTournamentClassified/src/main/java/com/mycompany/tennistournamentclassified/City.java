package com.mycompany.tennistournamentclassified;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class City {
    
    public static List<String> cities = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);
    
//    Sehir eklemek icin method.
    public static void addCity(){
        importCities();
        System.out.println("Please write the city's name below:");
        String cityName = scan.nextLine();
        cities.add(cityName);
        storeCities();
        TennisTournamentClassified.addMore();
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
           } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Kaydedilmis sehirleri silmek icin method.
    public static void clearCities(){
        try {
            Path file = Paths.get("Cities.txt");
            Files.delete(file);
            Files.createFile(file);
            cities.clear();
        } catch (IOException ex) {
        }
    }
}