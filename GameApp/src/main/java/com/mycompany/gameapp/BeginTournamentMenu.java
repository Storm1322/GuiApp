package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import static javax.swing.JOptionPane.showMessageDialog;

public class BeginTournamentMenu extends JFrame implements ActionListener{
    static JFrame beginTournamentMenu;
    static JButton returnButton;
    static JTextField playerCountTF;
    static JLabel invalidInput, playerCountL;
    static int playerCount;
    
    public BeginTournamentMenu(){
        beginTournamentMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTextFields();
        
        initializeLabels();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
    }
    
    public static void initializeTextFields(){
        playerCountTF = new JTextField();
        playerCountTF.setBounds(300, 200, 200, 25);
        playerCountTF.addActionListener(e -> setPlayerCount());
    }
    
    public static void initializeLabels(){
        playerCountL = new JLabel("How many players would you like to have play. (enter a power of 2 and press enter.)");
        playerCountL.setBounds(165, 150, 470, 50);
        invalidInput = new JLabel("Invalid input. Please check again.");
        invalidInput.setBounds(300, 250, 200, 50);
        invalidInput.setVisible(false);
    }
    
    public static void addComponentsToFrame(){
        beginTournamentMenu.add(returnButton);
        beginTournamentMenu.add(playerCountTF);
        beginTournamentMenu.add(playerCountL);
        beginTournamentMenu.add(invalidInput);
    }
    
    public static void frameSettings(){
        beginTournamentMenu.setSize(800, 600);
        beginTournamentMenu.setLayout(null);
        beginTournamentMenu.setVisible(true);
        beginTournamentMenu.setLocationRelativeTo(null);
        beginTournamentMenu.setResizable(false);
        beginTournamentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        beginTournamentMenu.dispose();
        new MainMenu();
    }
    
    //    Turnuvanin katilimci sayisini ayarlamak icin method.
    public static void setPlayerCount(){
        if(GameApp.checkInput(playerCountTF.getText()) == false){
            playerCount = Integer.parseInt(playerCountTF.getText());
            if(Tournament.powerOfTwo(playerCount) == true){
                invalidInput.setVisible(false);
                Tournament.tournamentPlayerCount = playerCount;
                Simulation.randomizer();
                showMessageDialog(null, "Successfully set player count of the tournament.");
                new SimulateTournamentMenu();
                beginTournamentMenu.dispose();
            }else{
                invalidInput.setVisible(true);
            }
        }else{
            invalidInput.setVisible(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
