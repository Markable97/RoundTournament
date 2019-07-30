/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addmatches;

/**
 *
 * @author march
 */
public class Match {
    int tour;
    String home;
    String away;

    public Match(int tour, String home, String away) {
        this.tour = tour;
        this.home = home;
        this.away = away;
    }

    @Override
    public String toString() {
        return "Match{" + "tour=" + tour + ", home=" + home + ", away=" + away + "}\n";
    }
    
    
}
