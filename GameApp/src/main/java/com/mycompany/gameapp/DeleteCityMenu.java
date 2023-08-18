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
    GridBagConstraints constraints;
    
    public DeleteCityMenu(){
        constraints = new GridBagConstraints();
        deleteCityMenu = new JFrame("Tennis Tournament");
        
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
        deleteCityMenu.add(returnButton, constraints);
        deleteButton = new JButton("delete");
        constraints.gridx = 2;
        constraints.gridy = 2;
        deleteButton.addActionListener(e -> confirmDeletion());
        deleteCityMenu.add(deleteButton, constraints);
    }
    
    public void initializeTables(){
        cityDisplay = new JTable(City.cityArray,City.cityHeadline);
        cityDisplayPane = new JScrollPane(cityDisplay);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.gridwidth = 3;
        
        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel(City.cityArray,City.cityHeadline) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        cityDisplay.setModel(tableModel);
        deleteCityMenu.add(cityDisplayPane, constraints);
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
            int[] rowsToRemove = cityDisplay.getSelectedRows();
            for(int i = rowsToRemove.length - 1; i >= 0; i--){
                ((DefaultTableModel)cityDisplay.getModel()).removeRow(rowsToRemove[i]);
                City.cities.remove(rowsToRemove[i]);
            }
            City.storeCities();
            City.importCities();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
