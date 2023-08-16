package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SavedDataMenu extends JFrame implements ActionListener{
    static JFrame savedDataMenu = new JFrame("Tennis Tournament");
    static JButton player, city, tournament, returnButton;
    static JLabel seeWhich;

    public SavedDataMenu(){
        
        initializeButtons();
        
        initializeLabels();
        
        addComponentsToFrame();
        
        frameSettings();
    }

    public static void initializeButtons(){
        player = new JButton("Player");
        player.setBounds(100, 300, 150, 50);
        player.addActionListener(e -> showPlayerData());
        city = new JButton("City");
        city.setBounds(325, 300, 150, 50);
        city.addActionListener(e -> showCityData());
        tournament = new JButton("Tournament");
        tournament.setBounds(550, 300, 150, 50);
        tournament.addActionListener(e -> showTournamentData());
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
    }
    
    public static void initializeLabels(){
        seeWhich = new JLabel("Which data would you like to view?");
        seeWhich.setBounds(300, 150, 200, 30);
    }
    
    public static void addComponentsToFrame(){
        savedDataMenu.add(player);
        savedDataMenu.add(city);
        savedDataMenu.add(tournament);
        savedDataMenu.add(seeWhich);
        savedDataMenu.add(returnButton);
    }
    
    public static void frameSettings(){
        savedDataMenu.setSize(800, 600);
        savedDataMenu.setLayout(null);
        savedDataMenu.setVisible(true);
        savedDataMenu.setLocationRelativeTo(null);
        savedDataMenu.setResizable(false);
        savedDataMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        savedDataMenu.dispose();
        new MainMenu();
    }
    
    public static void showPlayerData(){
        new ShowPlayerData();
        savedDataMenu.dispose();
    }
    
    public static void showCityData(){
        
    }
    
    public static void showTournamentData(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
