/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author ennio
 */
 //Classe contenente gli oggetti che rappresentano ogni percorso in un singolo istante di tempo e strutturata allo stesso modo dei dati in Json che fornisce Google
public class OriginDestDuration {
    private String origin;//indirizzo di origine
    private String destination;//indirizzo di destinazione
    private String dur_text;//durata nel traffico in minuti
    private int dur_value;//durata nel traffico espressa in secondi
    private Timestamp hour;//momento in cui è stato ricevuto il dato da Google e creato l'oggetto

    public OriginDestDuration() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDur_text() {
        return dur_text;
    }

    public void setDur_text(String dur_text) {
        this.dur_text = dur_text;
    }

    public int getDur_value() {
        return dur_value;
    }

    public void setDur_value(int dur_value) {
        this.dur_value = dur_value;
    }

    public Timestamp getHour() {
        return hour;
    }

    public void setHour(Timestamp hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "OriginDestDuration{" + "origin=" + origin + ", destination=" + destination + ", dur_text=" + dur_text + ", dur_value=" + dur_value + ", hour=" + hour + '}';
    }
    
    @Override
    public boolean equals(Object object2) {
        return object2 instanceof OriginDestDuration && origin.equals(((OriginDestDuration)object2).origin) && destination.equals(((OriginDestDuration)object2).destination) 
                && hour.equals(((OriginDestDuration)object2).hour) && dur_text.equals(((OriginDestDuration)object2).dur_text);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.origin);
        hash = 59 * hash + Objects.hashCode(this.destination);
        hash = 59 * hash + Objects.hashCode(this.dur_text);
        hash = 59 * hash + this.dur_value;
        hash = 59 * hash + Objects.hashCode(this.hour);
        return hash;
    }
    
}
