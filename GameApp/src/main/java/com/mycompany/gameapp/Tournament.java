package com.mycompany.gameapp;

import static com.mycompany.gameapp.GameApp.checkInput;
import static com.mycompany.gameapp.GameApp.checkInputForString;
import static com.mycompany.gameapp.GameApp.cityDisplayTA;
import static com.mycompany.gameapp.GameApp.continueButton;
import static com.mycompany.gameapp.GameApp.current;
import static com.mycompany.gameapp.GameApp.dataAlreadyShown;
import static com.mycompany.gameapp.GameApp.invalidInput;
import static com.mycompany.gameapp.GameApp.notEnough;
import static com.mycompany.gameapp.GameApp.playerCountL;
import static com.mycompany.gameapp.GameApp.playerCountTF;
import static com.mycompany.gameapp.GameApp.playerDisplayTA;
import static com.mycompany.gameapp.GameApp.returnButton;
import static com.mycompany.gameapp.GameApp.tournamentDisplayTA;
import static com.mycompany.gameapp.GameApp.tournamentTF;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tournament implements ActionListener{
    
    public static List<String> tournaments = new ArrayList<>();
    public static int tournamentPlayerCount;
    
    public static void showTournaments(){
        for(String tournament: tournaments){
            GameApp.tournamentDisplayTA.append(tournament + "\n");
        }
    }
    
//    Eklenen turnuvalari text fileda depolamak icin method.
    public static void storeTournaments(){
        Path file = Paths.get("Tournaments.txt");
        try {
            Files.write(file, tournaments, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    Depolanan turnuvalari loadlamak icin method.
    public static void importTournaments(){
        Path file = Paths.get("Tournaments.txt");
        try{
             tournaments = Files.readAllLines(file);
           } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addTournament(){
        GameApp.tournamentTF.setVisible(true);
        GameApp.tournamentL.setVisible(true);
        GameApp.continueButton.setVisible(true);
        GameApp.returnButton.setVisible(true);
    }
    
    public static void deleteTournaments(){
        
    }
    
//    Katilimci sayisi 2'nin kuvveti mi kontrol etmek icin method.
    public static boolean powerOfTwo(int n){
        while(n % 2 == 0){
            n = n / 2;
        }
        return n == 1;
    }
    
//    Turnuvanin katilimci sayisini ayarlamak icin method.
    public static void setPlayerCount(){
        GameApp.playerCountL.setVisible(true);
        GameApp.playerCountTF.setVisible(true);
        GameApp.returnButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == continueButton){
            if(current == "adding tournament"){
                if(checkInputForString(tournamentTF.getText()) == false){
                    Tournament.tournaments.add(tournamentTF.getText());
                    Tournament.storeTournaments();
                    playerDisplayTA.setText(null);
                    cityDisplayTA.setText(null);
                    tournamentDisplayTA.setText(null);
                    dataAlreadyShown = false;
                }else{
                    invalidInput.setVisible(true);
                }
            }
        }else if(e.getSource() == playerCountTF){
            if(checkInput(playerCountTF.getText()) == false && powerOfTwo(Integer.parseInt(playerCountTF.getText())) == true){
                Tournament.tournamentPlayerCount = Integer.parseInt(playerCountTF.getText());
                if(Player.players.size() - Tournament.tournamentPlayerCount >= 0){
                    returnButton.setVisible(false);
                    invalidInput.setVisible(false);
                    playerCountTF.setVisible(false);
                    playerCountL.setVisible(false);
                    current = "tournament info display";
                    Simulation.randomizer();
                }else{
                    notEnough.setVisible(true);
                    invalidInput.setVisible(false);
                }
            }else{
                invalidInput.setVisible(true);
                notEnough.setVisible(false);
            }
        }
    }
}
