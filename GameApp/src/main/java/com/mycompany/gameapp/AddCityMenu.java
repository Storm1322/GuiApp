package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class AddCityMenu extends JFrame{
    static JFrame addCityMenu;
    static JButton returnButton, saveButton;
    static JTextField cityNameTF;
    static JLabel addData, cityNameL, invalidName;
    
    
    public AddCityMenu(){
        addCityMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeLabels();
        
        initializeTextFields();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
        saveButton = new JButton("Save");
        saveButton.setBounds(325, 350, 150, 50);
        saveButton.addActionListener(e -> nameInput());
    }
    
    public static void initializeLabels(){
        addData = new JLabel("Please write into the appropriate box below and press enter.");
        addData.setBounds(220, 50, 360, 30);
        cityNameL = new JLabel("City Name:");
        cityNameL.setBounds(150,150,125,25);
        invalidName = new JLabel("Invalid entry.");
        invalidName.setBounds(550, 150, 100, 25);
        invalidName.setVisible(false);
    }
    
    public static void initializeTextFields(){
        cityNameTF = new JTextField();
        cityNameTF.setBounds(300, 150, 200, 25);
        cityNameTF.addActionListener(e -> nameInput());
    }
    
    public static void addComponentsToFrame(){
        addCityMenu.add(returnButton);
        addCityMenu.add(addData);
        addCityMenu.add(cityNameL);
        addCityMenu.add(invalidName);
        addCityMenu.add(cityNameTF);
        addCityMenu.add(saveButton);
    }
    
    public static void nameInput(){
        if(GameApp.checkInputForString(cityNameTF.getText().trim()) == false){
            City.cities.add(cityNameTF.getText().trim());
            City.storeCities();
            City.importCities();
            invalidName.setVisible(false);
            showMessageDialog(null, "Successfully added a city.");
            returnToMenu();
        }else{
            invalidName.setVisible(true);
        }
    }
    
    public static void frameSettings(){
        addCityMenu.setSize(800, 600);
        addCityMenu.setLayout(null);
        addCityMenu.setVisible(true);
        addCityMenu.setLocationRelativeTo(null);
        addCityMenu.setResizable(false);
        addCityMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        addCityMenu.dispose();
        new MainMenu();
    }
}
