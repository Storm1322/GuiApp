package com.mycompany.gameapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.DefaultTableModel;

public class DeleteCityMenu extends JFrame implements ActionListener{
    static JFrame deleteCityMenu;
    static JButton returnButton, deleteButton;
    static JTable cityDisplay;
    static JScrollPane cityDisplayPane;
    
    public DeleteCityMenu(){
        deleteCityMenu = new JFrame("Tennis Tournament");
        
        initializeButtons();
        
        initializeTables();
        
        addComponentsToFrame();
        
        frameSettings();
    }
    
    public static void initializeButtons(){
        returnButton = new JButton("Return <=");
        returnButton.setBounds(325, 450, 150, 50);
        returnButton.addActionListener(e -> returnToMenu());
        deleteButton = new JButton("delete");
        deleteButton.setBounds(325, 450, 150, 50);
        deleteButton.addActionListener(e -> confirmDeletion());
    }
    
    public static void initializeTables(){
        cityDisplay = new JTable(City.cityArray,City.cityHeadline);
        cityDisplay.setBounds(0, 0, 800, 450);
        cityDisplayPane = new JScrollPane(cityDisplay);
       
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(City.cityArray,City.cityHeadline) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        cityDisplay.setModel(tableModel);
    }
    
    public static void addComponentsToFrame(){
        deleteCityMenu.add(returnButton);
        deleteCityMenu.add(cityDisplayPane);
        deleteCityMenu.add(deleteButton);       
    }
    
    public static void frameSettings(){
        deleteCityMenu.setSize(800, 600);
        deleteCityMenu.setLayout(new GridBagLayout());
        deleteCityMenu.setVisible(true);
        deleteCityMenu.setLocationRelativeTo(null);
        deleteCityMenu.setResizable(false);
        deleteCityMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void returnToMenu(){
        deleteCityMenu.dispose();
        new MainMenu();
    }
    
    public static void confirmDeletion(){
        int answer = showConfirmDialog(null, "Are you sure?", "Select an option...", JOptionPane.YES_NO_OPTION);
        if (answer == 0){
            int rowToRemove = cityDisplay.getSelectedRow();
            ((DefaultTableModel)cityDisplay.getModel()).removeRow(rowToRemove);
            City.cities.remove(rowToRemove);
            City.storeCities();
            City.importCities();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
