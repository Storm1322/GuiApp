package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame{
    static JFrame mainMenu;
    static JButton addPlayerButton, addCityButton, addTournamentButton, deletePlayerButton, deleteCityButton, deleteTournamentButton, beginTournamentButton, seeSavedData;
    
    public MainMenu(){
        mainMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        addPlayerButton = new JButton("Add Players");
        addPlayerButton.setBounds(100, 100, 150, 50);
        addPlayerButton.addActionListener(e -> launchAddPlayerFrame());
        addCityButton = new JButton("Add Cities");
        addCityButton.setBounds(100, 200, 150, 50);
        addCityButton.addActionListener(e -> launchAddCityFrame());
        addTournamentButton = new JButton("Add Tournaments");
        addTournamentButton.setBounds(100, 300, 150, 50);
        addTournamentButton.addActionListener(e -> launchAddTournamentFrame());
        deletePlayerButton = new JButton("Delete Players");
        deletePlayerButton.setBounds(550, 100, 150, 50);
        deletePlayerButton.addActionListener(e -> launchDeletePlayerFrame());
        deleteCityButton = new JButton("Delete Cities");
        deleteCityButton.setBounds(550, 200, 150, 50);
        deleteCityButton.addActionListener(e -> launchDeleteCityFrame());
        deleteTournamentButton = new JButton("Delete Tournaments");
        deleteTournamentButton.setBounds(550, 300, 150, 50);
        deleteTournamentButton.addActionListener(e -> launchDeleteTournamentFrame());
        beginTournamentButton = new JButton("Begin Tournament");
        beginTournamentButton.setBounds(325, 200, 150, 50);
        beginTournamentButton.addActionListener(e -> launchBeginTournamentFrame());
        seeSavedData = new JButton("See Saved Data");
        seeSavedData.setBounds(325, 300, 150, 50);
        seeSavedData.addActionListener(e -> launchSavedDataFrame());
    }
    
    public static void addComponentsToFrame(){
        mainMenu.add(GameApp.exitButton);
        mainMenu.add(addPlayerButton);
        mainMenu.add(addCityButton);
        mainMenu.add(addTournamentButton);
        mainMenu.add(deletePlayerButton);
        mainMenu.add(deleteCityButton);
        mainMenu.add(deleteTournamentButton);
        mainMenu.add(beginTournamentButton);
        mainMenu.add(seeSavedData);
    }
    
    public static void frameSettings(){
        mainMenu.setSize(800, 600);
        mainMenu.setLayout(null);
        mainMenu.setVisible(true);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setResizable(false);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void launchAddPlayerFrame(){
        new AddPlayerMenu();
        mainMenu.dispose();
    }
    
    public static void launchAddCityFrame(){
        new AddCityMenu();
        mainMenu.dispose();
    }
    
    public static void launchAddTournamentFrame(){
        new AddTournamentMenu();
        mainMenu.dispose();
    }
    
    public static void launchDeletePlayerFrame(){
        new DeletePlayersMenu();
        mainMenu.dispose();
    }
    
    public static void launchDeleteCityFrame(){
        new DeleteCityMenu();
        mainMenu.dispose();
    }
    
    public static void launchDeleteTournamentFrame(){
        new DeleteTournamentMenu();
        mainMenu.dispose();
    }
    
    public static void launchBeginTournamentFrame(){
        new BeginTournamentMenu();
        mainMenu.dispose();
    }
    
    public static void launchSavedDataFrame(){
        mainMenu.dispose();
        new SavedDataMenu();
    }
}
