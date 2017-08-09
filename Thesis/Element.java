/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

/**
 *
 * @author ennio
 */
 //Classe utile per il parsing Json
public class Element {
    private General duration;
    private General distance;
    private String status; 
    private General duration_in_traffic;

    public General getDuration() {
        return duration;
    }

    public General getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
    }   

    public General getDuration_in_traffic() {
        return duration_in_traffic;
    }
        
}
