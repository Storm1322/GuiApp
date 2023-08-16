package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class AddTournamentMenu extends JFrame implements ActionListener{
    static JFrame addTournamentMenu;
    static JButton returnButton;
    static JTextField tournamentNameTF;
    static JLabel addData, tournamentNameL, invalidName;
    
    public AddTournamentMenu(){
        addTournamentMenu = new JFrame("Tennis Tournament");
        
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
        addData = new JLabel("Please write into the appropriate box below and press enter.");
        addData.setBounds(220, 50, 360, 30);
        tournamentNameL = new JLabel("Player Name:");
        tournamentNameL.setBounds(150,150,125,25);
        invalidName = new JLabel("Invalid entry.");
        invalidName.setBounds(550, 150, 100, 25);
        invalidName.setVisible(false);
    }
    
    public static void initializeTextFields(){
        tournamentNameTF = new JTextField();
        tournamentNameTF.setBounds(300, 150, 200, 25);
        tournamentNameTF.addActionListener(e -> nameInput());
    }
    
    public static void addComponentsToFrame(){
        addTournamentMenu.add(returnButton);
        addTournamentMenu.add(addData);
        addTournamentMenu.add(tournamentNameL);
        addTournamentMenu.add(invalidName);
        addTournamentMenu.add(tournamentNameTF);
    }
    
    public static void frameSettings(){
        addTournamentMenu.setSize(800, 600);
        addTournamentMenu.setLayout(null);
        addTournamentMenu.setVisible(true);
        addTournamentMenu.setLocationRelativeTo(null);
        addTournamentMenu.setResizable(false);
        addTournamentMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void nameInput(){
        if(GameApp.checkInputForString(tournamentNameTF.getText()) == false){
            Tournament.tournaments.add(tournamentNameTF.getText());
            Tournament.storeTournaments();
            invalidName.setVisible(false);
            showMessageDialog(null, "Successfully added a tournament.");
        }else{
            invalidName.setVisible(true);
        }
    }
    
    public static void returnToMenu(){
        addTournamentMenu.dispose();
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
