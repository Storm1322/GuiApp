package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameApp extends JFrame{
    static JButton exitButton;
    static JLabel invalidInput;
    
    public GameApp(){
        exitButton = new JButton("Exit");
        exitButton.setBounds(325, 500, 150, 50);
        exitButton.addActionListener(e -> shutDown());
        invalidInput = new JLabel("One of the inputs were incorrect. Please check again.");
        invalidInput.setBounds(250, 250, 300, 50);
        
        new MainMenu();
    }
    
    public static void main(String[] args) {
        Player.importPlayers();
        City.importCities();
        Tournament.importTournaments();
        
        new GameApp();
    }
    
    public static void shutDown(){
        System.exit(0);
    }
    
//    String istendiginde integer bulunduran input var mi kontrol eden method.
    public static boolean checkInputForString(String str){
        if(str.equals("")){
            return true;
        }else{
            str = str.replaceAll("[a-zA-Z_ -]", "");
            return !"".equals(str);
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
