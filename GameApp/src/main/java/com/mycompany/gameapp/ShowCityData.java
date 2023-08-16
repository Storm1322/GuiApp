package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ShowCityData extends JFrame implements ActionListener{
    static JFrame showCityDataMenu; 
    static JButton returnButton;
    static JTable cityDisplay;
    static JScrollPane cityDisplayPane;
    
    public ShowCityData(){
        showCityDataMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
    }
    
    public static void initializeTables(){
        cityDisplay = new JTable(City.cities.size(),1);
        cityDisplay.setBounds(100, 50, 600, 350);
        cityDisplayPane = new JScrollPane(cityDisplay);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(City.cityArray, City.cityHeadline) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        cityDisplay.setModel(tableModel);

        cityDisplay.setModel(tableModel);
    }
    
    public static void addComponentsToFrame(){
        showCityDataMenu.add(cityDisplayPane);
        showCityDataMenu.add(returnButton);
    }
    
    public static void frameSettings(){
        showCityDataMenu.setSize(800, 600);
        showCityDataMenu.setLayout(new GridLayout(2,1));
        showCityDataMenu.setVisible(true);
        showCityDataMenu.setLocationRelativeTo(null);
        showCityDataMenu.setResizable(false);
        showCityDataMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        showCityDataMenu.dispose();
        new MainMenu();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
