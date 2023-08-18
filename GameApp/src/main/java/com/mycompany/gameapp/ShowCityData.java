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
    GridBagConstraints constraints;
    
    public ShowCityData(){
        constraints = new GridBagConstraints();
        showCityDataMenu = new JFrame("Tennis Tournament");
        
        frameSettings();
        
        initializeButtons();
        
        initializeTables();
    }
    
    public void initializeButtons(){
        returnButton = new JButton("Return <=");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        returnButton.addActionListener(e -> returnToMenu());
        showCityDataMenu.add(returnButton, constraints);
    }
    
    public void initializeTables(){
        cityDisplay = new JTable(City.cities.size(),1);
        cityDisplayPane = new JScrollPane(cityDisplay);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(City.cityArray, City.cityHeadline) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        cityDisplay.setModel(tableModel);
        showCityDataMenu.add(cityDisplayPane, constraints);
    }
    
    public static void frameSettings(){
        showCityDataMenu.setSize(800, 600);
        showCityDataMenu.setLayout(new GridBagLayout());
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
