/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import static com.tesi3.tesi3.MyMath.correlation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

/**
 *
 * @author ennio
 */
 //Classe nella quale vengono salvati i dati presi dal database al fine di fare poi la correlazione tra i vari percorsi
public class DBRoute {
    private String origin;
    private String destination;
    private List<Integer> duration;//lista contenente i valori di durata nel traffico per ogni percorso

    public DBRoute(String origin, String destination, List<Integer> duration) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
    }

    public DBRoute() {
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

    public List<Integer> getDuration() {
        return duration;
    }

    public void setDuration(List<Integer> duration) {
        this.duration = duration;
    }
    
    private double correlationIndex(DBRoute a, DBRoute b){
        return correlation(a.getDuration(), b.getDuration());
    }
    
	//Il seguente metodo serve a prendere il valore "duration" dagli oggetti DBRoute e di ricavarne un corrispondente array di double 
	//per usarlo come argomento dei metodi della libreria commons.math checalcolano i vari indici di correlazione
    private double[] listToArray(DBRoute a){        
        List<Double> d = new ArrayList<>();
        for(int i = 0; i < a.getDuration().size(); i++){
            d.add((double)a.getDuration().get(i));
        }
        double[] arr = new double[d.size()];
        for(int i = 0; i < d.size(); i++) arr[i] = d.get(i);
        
        return arr;
    }
    
	//Metodo con la quale si calcolano tutte le correlazioni fra i vari percorsi e si costruiscono gli oggetti CorrelatedRoutes, questi vengono salvati
	//in una lista ordinata (in base all'indice di Pearson) crescente che infine verrà caricata nel database
    public void getCorrelation(List<DBRoute> a, List<CorrelatedRoutes> x){
        
        int count = 0;
        
        for(int i = 0; i < a.size(); i++){            
            for(int j = 0; j < a.size(); j++){                
                if(j > i){   
                    PearsonsCorrelation pc = new PearsonsCorrelation();
                    KendallsCorrelation kc = new KendallsCorrelation();
                    SpearmansCorrelation sc = new SpearmansCorrelation();
                    
                    double pcIndex = pc.correlation(listToArray(a.get(i)), listToArray(a.get(j)));//calcolo l'indice di correlazione di Pearson usando la libreria commons.math
                    double kcIndex = kc.correlation(listToArray(a.get(i)), listToArray(a.get(j)));//calcolo l'indice di correlazione di Kendall usando la libreria commons.math
                    double scIndex = sc.correlation(listToArray(a.get(i)), listToArray(a.get(j)));//calcolo l'indice di correlazione di Spearman usando la libreria commons.math
                                        
                    Calendar calendar = Calendar.getInstance();
                    Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
                    
                    CorrelatedRoutes cr = new CorrelatedRoutes(a.get(i).getOrigin(), a.get(i).getDestination(),
                        a.get(j).getOrigin(), a.get(j).getDestination(), pcIndex, kcIndex, scIndex, currentTimestamp);
                    
                    x.add(cr);
                    count = count + 1;
                }
            }
        }
        Collections.sort(x); //con una ArrayList ottengo una lista ordinata in ordine crescente (di indice di correlazione più alto) di tutte le coppie di rotte
        System.out.println("Numero di combinazioni di rotte di cui è stata trovata la correlazione: " + count);
    }

    @Override
    public String toString() {
        String a = "[";
        for(int i = 0; i < duration.size(); i++){
            if(i < duration.size() - 1){
                a = a + duration.get(i) + ", ";
            } else {
                a = a + duration.get(i) + "]";
            }            
        }
        return "DBRoute{" + "origin=" + origin + ", destination=" + destination + ", duration=" + a + '}';
    }
    
    
    
}
