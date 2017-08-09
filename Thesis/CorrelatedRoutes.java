/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.sql.Timestamp;

/**
 *
 * @author ennio
 */
 
//Classe costruita per salvare gli indici di correlazione fra due percorsi
public class CorrelatedRoutes implements Comparable<CorrelatedRoutes>{
    private String origin1;
    private String destination1;
    private String origin2;
    private String destination2;
    private double PearsonRank;
    private double KendallRank;
    private double SpearmanRank;
    private Timestamp time;

    public CorrelatedRoutes(String origin1, String destination1, String origin2, String destination2, double PearsonRank, double KendallRank, double SpearmanRank, Timestamp time) {
        this.origin1 = origin1;
        this.destination1 = destination1;
        this.origin2 = origin2;
        this.destination2 = destination2;
        this.PearsonRank = PearsonRank;
        this.KendallRank = KendallRank;
        this.SpearmanRank = SpearmanRank;
        this.time = time;
    }
            
    public CorrelatedRoutes() {
    }    

    public String getOrigin1() {
        return origin1;
    }

    public void setOrigin1(String origin1) {
        this.origin1 = origin1;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getOrigin2() {
        return origin2;
    }

    public void setOrigin2(String origin2) {
        this.origin2 = origin2;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public double getPearsonRank() {
        return PearsonRank;
    }

    public void setPearsonRank(double PearsonRank) {
        this.PearsonRank = PearsonRank;
    }

    public double getKendallRank() {
        return KendallRank;
    }

    public void setKendallRank(double KendallRank) {
        this.KendallRank = KendallRank;
    }

    public double getSpearmanRank() {
        return SpearmanRank;
    }

    public void setSpearmanRank(double SpearmanRank) {
        this.SpearmanRank = SpearmanRank;
    }
    
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CorrelatedRoutes{" + "origin1=" + origin1 + ", destination1=" + destination1 + ", origin2=" + origin2 + ", destination2=" + destination2 + ", PearsonRank=" + PearsonRank + ", KendallRank=" + KendallRank + ", SpearmanRank=" + SpearmanRank + ", time=" + time + '}';
    }       

    @Override
    public int compareTo(CorrelatedRoutes o) {
        return Double.compare(this.getPearsonRank(), o.getPearsonRank());
    }  
    
}
