package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class AddPlayerMenu extends JFrame implements ActionListener{
    static JFrame addPlayerMenu;
    static JTextField playerNameTF, playerSurnameTF, playerAgeTF, playerOriginTF, playerGenderTF;
    static JButton returnButton;
    static JLabel addData, invalidName, invalidSurname, invalidAge, invalidOrigin, invalidGender, playerNameL, playerSurnameL, playerAgeL, playerOriginL, playerGenderL;

    public AddPlayerMenu(){
        addPlayerMenu = new JFrame("Tennis Tournament");
        
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
    }
    
    public static void initializeLabels(){
        addData = new JLabel("Please write into the appropriate boxes below and press enter.");
        addData.setBounds(220, 20, 360, 30);
        invalidName = new JLabel("Invalid entry.");
        invalidName.setBounds(550, 50, 100, 25);
        invalidName.setVisible(false);
        invalidSurname = new JLabel("Invalid entry.");
        invalidSurname.setBounds(550, 100, 100, 25);
        invalidSurname.setVisible(false);
        invalidAge = new JLabel("Invalid entry.");
        invalidAge.setBounds(550, 150, 100, 25);
        invalidAge.setVisible(false);
        invalidOrigin = new JLabel("Invalid entry.");
        invalidOrigin.setBounds(550, 200, 100, 25);
        invalidOrigin.setVisible(false);
        invalidGender = new JLabel("Invalid entry.");
        invalidGender.setBounds(550, 250, 100, 25);
        invalidGender.setVisible(false);
        playerNameL = new JLabel("Player Name:");
        playerNameL.setBounds(150,50,125,25);
        playerSurnameL = new JLabel("Player Surname:");
        playerSurnameL.setBounds(150,100,125,25);
        playerAgeL = new JLabel("Player Age:");
        playerAgeL.setBounds(150,150,125,25);
        playerOriginL = new JLabel("Player Country:");
        playerOriginL.setBounds(150,200,125,25);
        playerGenderL = new JLabel("Player Gender:");
        playerGenderL.setBounds(150,250,125,25);
    }
    
    public static void initializeTextFields(){
        playerNameTF = new JTextField();
        playerNameTF.setBounds(300, 50, 200, 25);
        playerNameTF.addActionListener(e -> nameInput());
        playerSurnameTF = new JTextField();
        playerSurnameTF.setBounds(300, 100, 200, 25);
        playerSurnameTF.addActionListener(e -> surnameInput());
        playerAgeTF = new JTextField();
        playerAgeTF.setBounds(300, 150, 200, 25);
        playerAgeTF.addActionListener(e -> ageInput());
        playerOriginTF = new JTextField();
        playerOriginTF.setBounds(300, 200, 200, 25);
        playerOriginTF.addActionListener(e -> originInput());
        playerGenderTF = new JTextField();
        playerGenderTF.setBounds(300, 250, 200, 25);
        playerGenderTF.addActionListener(e -> genderInput());
    }
    
    public static void addComponentsToFrame(){
        addPlayerMenu.add(playerNameTF);
        addPlayerMenu.add(playerSurnameTF);
        addPlayerMenu.add(playerAgeTF);
        addPlayerMenu.add(playerOriginTF);
        addPlayerMenu.add(playerGenderTF);
        addPlayerMenu.add(addData);
        addPlayerMenu.add(returnButton);
        addPlayerMenu.add(invalidName);
        addPlayerMenu.add(invalidSurname);
        addPlayerMenu.add(invalidAge);
        addPlayerMenu.add(invalidOrigin);
        addPlayerMenu.add(invalidGender);
        addPlayerMenu.add(playerNameL);
        addPlayerMenu.add(playerSurnameL);
        addPlayerMenu.add(playerAgeL);
        addPlayerMenu.add(playerOriginL);
        addPlayerMenu.add(playerGenderL);
    }
    
    public static void frameSettings(){
        addPlayerMenu.setSize(800, 600);
        addPlayerMenu.setLayout(null);
        addPlayerMenu.setVisible(true);
        addPlayerMenu.setLocationRelativeTo(null);
        addPlayerMenu.setResizable(false);
        addPlayerMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        addPlayerMenu.dispose();
        new MainMenu();
    }
    
    public static void nameInput(){
        String name = playerNameTF.getText();
        if(GameApp.checkInputForString(name) == false){
            playerNameTF.setEnabled(false);
            invalidName.setVisible(false);
            Player.playerName = name.trim();
            checkAdd();
        }else{
            invalidName.setVisible(true);
        }
    }
    
    public static void surnameInput(){
        String surname = playerSurnameTF.getText();
        if(GameApp.checkInputForString(surname) == false){
            playerSurnameTF.setEnabled(false);
            invalidSurname.setVisible(false);
            Player.playerSurname = surname.trim();
            checkAdd();
        }else{
            invalidSurname.setVisible(true);
        }
    }
    
    public static void ageInput(){
        String age = playerAgeTF.getText();
        if(GameApp.checkInput(age) == false){
            playerAgeTF.setEnabled(false);
            invalidAge.setVisible(false);
            Player.playerAge = age.trim();
            checkAdd();
        }else{
            invalidAge.setVisible(true);
        }
    }
    
    public static void originInput(){
        String origin = playerOriginTF.getText();
        if(GameApp.checkInputForString(origin) == false){
            playerOriginTF.setEnabled(false);
            invalidOrigin.setVisible(false);
            Player.playerOrigin = origin.trim();
            checkAdd();
        }else{
            invalidOrigin.setVisible(true);
        }
    }
    
    public static void genderInput(){
        String gender = playerGenderTF.getText();
        if(GameApp.checkInputForString(gender) == false){
            playerGenderTF.setEnabled(false);
            invalidGender.setVisible(false);
            Player.playerGender = gender.trim();
            checkAdd();
        }else{
            invalidGender.setVisible(true);
        }
    }
    
    public static boolean allEntered(){
        return (playerNameTF.isEnabled() == false  && playerSurnameTF.isEnabled() == false && playerAgeTF.isEnabled() == false && playerOriginTF.isEnabled() == false && playerGenderTF.isEnabled() == false);
    }
    
    public static void checkAdd(){
        if(allEntered()){
            Player player = new Player(Player.playerName, Player.playerSurname, Player.playerAge, Player.playerOrigin, Player.playerGender);
            Player.players.add(player);
            Player.storePlayers();
            showMessageDialog(null, "Successfully added a player.");
            returnToMenu();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
