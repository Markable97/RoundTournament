/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addmatches;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author march
 */
public class MainClass {
    
    public static void main(String[] args) throws SQLException{
        System.out.println("Начало программы");
        DataBase db = new DataBase();
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<String> list = db.getTeamsFromDB(1);
        String[] str = null;
        if (!list.isEmpty()){
            str =  list.toArray(new String[list.size()]);
            System.out.println("кол-во команд = " + str.length);
        }else{
            System.out.println("кол-во команд пусто ");
        }
        if(str.length > 0){
            int teams = str.length; //кол-во команд 
            int halfTour = teams - 1;//кол-во туров 1 круга 
            int totalRound = halfTour * 2; //кол-во туров за 2 круга 
            int matchesPerRaund = teams / 2; //кол-во матчей в кадом туре 
            int home, away, swap; 
            for(int round = 0; round < totalRound; round++){ 
                //System.out.println("Тур " + (round + 1)); 
                for(int match = 0; match < matchesPerRaund; match++){ 
                    home = (round + match)%(teams - 1); 
                    away = (teams - 1 - match + round) % (teams - 1); 
                    if (match == 0) { 
                        away = teams - 1; 
                    } 
                    if (round >= halfTour) { 
                        swap = home; 
                        home = away; 
                        away = swap; 
                    } 
                    //System.out.println(str[home] + " vs " + str[away]);
                    matches.add(new Match(round + 1, str[home], str[away]));
                } 
            }
            System.out.println("Матчи: \n" + matches.toString());
            DataBase dataBase = new DataBase();
            dataBase.sendingDB(matches);
        }else{
            System.out.println("Нет команд");
        }
        
    }     
}

