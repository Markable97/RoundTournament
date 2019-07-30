/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addmatches;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author march
 */
public class DataBase {
    String user = "root";
    String password = "7913194";
    String url = "jdbc:mysql://localhost:3306/football_main";
    Connection connect;
    String sql = "select team_name "
                   + "from teams where id_division = ?";
    String sqlInsert = "insert into matches "
                     + "set id_division = 1, "
                     + "id_season = 3, "
                     + "id_tour = ?, "
                     + "team_home = (select id_team from teams " +
                       " where team_name like ?)," +
                       "team_guest = (select id_team from teams " +
                       " where team_name like ?)";
    PreparedStatement statement;
    PreparedStatement insertMatch;
    ResultSet resultSet;
    
    public DataBase(){
        try {
            this.connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void sendingDB(ArrayList<Match> matches) throws SQLException{
        int i = 0;
        try {
            insertMatch = connect.prepareStatement(sqlInsert);
            for(Match match : matches){
                insertMatch.setInt(1, match.tour);
                insertMatch.setString(2, match.home);
                insertMatch.setString(3, match.away);
                insertMatch.executeUpdate();
                System.out.println("Матч добавлен: " + match.toString());
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            System.out.println("Добавлено матчей = " + i);
            insertMatch.close();
            connect.close();
        }
    }
    
    ArrayList<String> getTeamsFromDB(int id_division) throws SQLException{
        try {
            statement = connect.prepareStatement(sql);
            statement.setInt(1, id_division);
            resultSet = statement.executeQuery();
            ArrayList<String> list = new ArrayList();
            while(resultSet.next()){
                list.add(resultSet.getString(1));
            }
            System.out.println("Список команд: \n" + list.toString());
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            resultSet.close();
            statement.close();
            connect.close();
        }finally{
            resultSet.close();
            statement.close();
            connect.close();
        }
        return null;
    }
    
}
