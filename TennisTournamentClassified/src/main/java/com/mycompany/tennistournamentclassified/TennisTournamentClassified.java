package com.mycompany.tennistournamentclassified;

import java.util.Scanner;
import java.util.Random;
import static com.mycompany.tennistournamentclassified.Player.players;
import static com.mycompany.tennistournamentclassified.City.cities;
import static com.mycompany.tennistournamentclassified.Player.displayPlayers;
import static com.mycompany.tennistournamentclassified.Player.registerPlayers;
import static com.mycompany.tennistournamentclassified.Tournament.tournaments;
import java.util.ArrayList;

public class TennisTournamentClassified {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
//        Depolanmis datalari import.
        Player.importPlayers();
        City.importCities();
        Tournament.importTournaments();
        
        showSavedData();

        addNew();
        
//        Turnuva katilimci sayisi ayarla.
        Tournament.setSize();
        Random rand = new Random();
        
//        Turnuvanin yapilacagi sehir ve turnuva adi listeden rastgele.
        int tournamentRandInt = rand.nextInt(tournaments.size());
        int cityRandInt = rand.nextInt(cities.size());
        System.out.println("The tournament is named " + tournaments.get(tournamentRandInt) + " and will be played in " + cities.get(cityRandInt) + ".");
        System.out.println("");
        ArrayList<String> playingPlayers = new ArrayList<>();
        for(Player player : Simulation.playingPlayersObjects){
            playingPlayers.add(player.name + " " + player.surname);
        }
        System.out.println("The following players will be playing in the tournament: " + playingPlayers);
        System.out.println("");
        System.out.println("Each game will be comprised of three sets and the winner of two will take all.");
        System.out.println("");
//        Turnuvayi simule et.
        Simulation.simulateTournament();
    }
    
//    Kaydedilmis datalari gostermek icin method.
    public static void showSavedData(){
        System.out.println("Would you like to see saved players, cities or tournaments. (y/n or e: exit)");
        String answer = scan.nextLine();
        if(answer.equalsIgnoreCase("y")){
            System.out.println("Which would you like to view? (Players: 1, Cities: 2, Tournaments: 3)");
            String viewWhichStr = scan.nextLine();
//            Integer olmayan input var mi check.
            boolean hasNonInteger = Player.checkInput(viewWhichStr);
//            Varsa tekrar sor.
            if (hasNonInteger == true) {
                System.out.println("You have entered an incorrect input. Please try again.");
                showSavedData();
            } else {
                int viewWhich = Integer.parseInt(viewWhichStr);
                if(viewWhich == 1){
                    System.out.println("Registered players:");
                    for(Player player: Player.players){
                        displayPlayers.add(player.name + " " + player.surname);
                    }
                    System.out.println(displayPlayers);
                    System.out.println("");
                }else if(viewWhich == 2){
                    System.out.println("Registered cities:");
                    System.out.println(City.cities);
                    System.out.println("");
                }else if(viewWhich == 3){
                    System.out.println("Registered tournaments:");
                    System.out.println(Tournament.tournaments);
                    System.out.println("");
                }else{
                    System.out.println("Invalid input. Please try again.");
                    showSavedData();
                }
                System.out.println("Would you like to delete any of the saved data? (y/n or e: exit)");
                String delete = scan.nextLine();
                if(delete.equalsIgnoreCase("y")){
                    deleteSaved();
                }else if(delete.equalsIgnoreCase("e")){
                    System.out.println("Exiting the program.");
                    System.exit(0);
                }
            }
            }else if(answer.equalsIgnoreCase("n")){
            }else if(answer.equalsIgnoreCase("e")){
                System.out.println("Exiting the program.");
                System.exit(0);
            }else{
                System.out.println("Invalid entry. Please try again.");
                System.out.println("");
                showSavedData();
            }        
    }
    
//    Yeni player, sehir veya turnuva eklemek icin method.
    public static void addNew(){
        System.out.println("Would you like to define a new Player, City or Tournament? (y/n or e : exit)");
        String willDefineNew = scan.nextLine();
//        Yeni player, sehir veya turnuva eklenmek isterse eklenmek isteneni sor.
        if (willDefineNew.equalsIgnoreCase("y")) {
            System.out.println("Which will you define? (Enter 1: Player, 2: City, 3: Tournament)");
            String defineWhichStr = scan.nextLine();
//            Integer olmayan input var mi check.
            boolean hasNonInteger = Player.checkInput(defineWhichStr);
//            Varsa tekrar sor.
            if (hasNonInteger == true) {
                System.out.println("You have entered an incorrect input. Please try again.");
                addNew();
            } else {
                int defineWhich = Integer.parseInt(defineWhichStr);
//                Player eklenmek istenirse.
                if (defineWhich == 1) {
                    Player.registerPlayers();
//                Sehir eklenmek istenirse.
                } else if (defineWhich == 2) {
                    City.addCity();
//                Turnuva eklenmek istenirse.
                } else if (defineWhich == 3) {
                    Tournament.addTournament();
                } else {
                    System.out.println("Invalid input.");
                    addNew();
                }
            }
//        Yeni ekleme yapilmak istenmezse devam et.
        } else if (willDefineNew.equalsIgnoreCase("n")) {
            System.out.println("Proceeding directly to the tournament.");
            System.out.println("");
        } else if (willDefineNew.equalsIgnoreCase("e")) {
            System.out.println("Exiting the program.");
            System.exit(0);
        } else {
            System.out.println("Invalid entry. Please try again.");
            addNew();
        }
//        Yeterli sayida tanimlanmis player, sehir ve turnuva var mi check.
        if (players.size() < 2 || cities.size() < 1 || tournaments.size() < 1) {
            System.out.println("You are lacking a proper amount of one or more variables please define more of whatever is lacking.");
            System.out.println("Player Count: " + players.size() + " City Count: " + cities.size() + " Tournament Count: " + tournaments.size());
            System.out.println("");
            addNew();
        }
    }
    
//    Player, sehir veya turnuva ekledikten sonra baska eklenmek istenen var mi sormak icin method.
    public static void addMore(){
        System.out.println("Would you like to add anything else? (y/n or e: exit)");
        String addMore = scan.nextLine();
        if(addMore.equalsIgnoreCase("y")){
            System.out.println("Which will you add? (Enter 1: Player, 2: City, 3: Tournament)");
            String defineMoreStr = scan.nextLine();
//            Integer olmayan input var mi check.
            boolean hasNonInteger = Player.checkInput(defineMoreStr);
//            Varsa tekrar sor.
            if(hasNonInteger == true){
                System.out.println("You have entered an incorrect input. Please try again.");
                addMore();
            }else{
                int defineMore = Integer.parseInt(defineMoreStr);
                switch(defineMore){
                    case 1 -> {
                        registerPlayers();
                        break;
                    }
                    
                    case 2 -> {
                        City.addCity();
                        break;
                    }
                        
                    case 3 -> {
                        Tournament.addTournament();
                        break;
                    }
                        
                    default -> {
                        System.out.println("Invalid input.");
                        addMore();
                        break;
                    }
                }
            }
        }else if(addMore.equalsIgnoreCase("n")){
            System.out.println("Proceeding directly to the tournament.");
        }else if(addMore.equalsIgnoreCase("e")){
            System.out.println("Exiting program...");
            System.exit(0);
        }else{               
            System.out.println("Invalid entry.");
            addMore();
        }
    }
    
//    Kaydedilmis datalardan hangisinin silecegini soran method.
    public static void deleteSaved(){
        System.out.println("Which will you delete? (Enter 1: Player, 2: City, 3: Tournament or exit: e)");
                String deleteWhichStr = scan.nextLine();
                if(deleteWhichStr.equalsIgnoreCase("e")){
                    System.out.println("Exiting program.");
                    System.exit(0);
                }else{
//                    Integer olmayan input var mi check.
                    boolean hasNonInteger = Player.checkInput(deleteWhichStr);
//                    Varsa tekrar sor.
                    if (hasNonInteger == true) {
                        System.out.println("You have entered an incorrect input. Please try again.");
                        deleteSaved();
                    } else {
                        int deleteWhich = Integer.parseInt(deleteWhichStr);
                        if(deleteWhich == 1){
                            Player.clearPlayers();
                            showSavedData();
                        }else if(deleteWhich == 2){
                            City.clearCities();
                            showSavedData();
                        }else if(deleteWhich == 3){
                            Tournament.clearTournaments();
                            showSavedData();
                        }else{
                            System.out.println("Invalid input. Please try again.");
                            deleteSaved();
                        }
                    }
                }
    }
    
//    Integer istendiginde integer olmayan input var mi kontrol eden method.
    public static boolean checkInputForString(String str){
        if(str.equals("")){
            return true;
        }else{
            str = str.replaceAll("[^0-9]", "");
            return !"".equals(str);
        }
    }
}