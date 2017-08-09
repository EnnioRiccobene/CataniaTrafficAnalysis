/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ennio
 */
 //Classe contenente i metodi che servono a calcolare la media, la moda e la mediana per i vari percorsi
public class MyMath {
    private List<Integer> myvalues;
    private List<OriginDestDuration> myobject;
    
   
    public MyMath(List<Integer> myvalues) {
        this.myvalues = myvalues;
    }
    
	public MyMath() {
    }
    
    public List<OriginDestDuration> getMyobject() {
        return myobject;
    }

    public void setMyobject(List<OriginDestDuration> myobject) {
        this.myobject = myobject;
    }

    public List<Integer> getMyvalues() {
        return myvalues;
    }

    public void setMyvalues(List<Integer> myvalues) {
        this.myvalues = myvalues;
    }
    
    public double media(int[] myvalues){
        double a = 0;
        double b = 0;
        if(myvalues.length >= 1) {
            for(int i = 0; i < myvalues.length; i++){
                b = b + myvalues[i];
            }
            a = b / myvalues.length;
        }
        return a;
    }
    
    public double media(List<Integer> myvalues){
        double a = 0;
        double b = 0;
        if(myvalues.size() >= 1) {
            for(int i = 0; i < myvalues.size(); i++){
                b = b + myvalues.get(i);
            }
            a = b / myvalues.size();
        }
        return a;
    }
    
    
    public double mediana(List<Integer> myvalues){
        Collections.sort(myvalues);
        int middle = myvalues.size() / 2;
        if (myvalues.size()%2 == 1) {
            return myvalues.get(middle);
        } else {
            return (myvalues.get(middle - 1) + myvalues.get(middle)) / 2.0;
        }
    }
    
    
    public double mediana(int[] m){
        Arrays.sort(m);
        int middle = m.length/2;
        if (m.length%2 == 1) {
            return m[middle];
        } else {
            return (m[middle-1] + m[middle]) / 2.0;
        }
    }
    
    public int moda(List<Integer> myvalues){
        int maxValue = 0, maxCount = 0;
        
        for (int i = 0; i < myvalues.size(); ++i) {
            int count = 0;
            for (int j = 0; j < myvalues.size(); ++j) {
                if (myvalues.get(j) == myvalues.get(i)) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = myvalues.get(i);
            }
        }
        return maxValue;
    }
    
    
    public static int moda(int a[]) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < a.length; ++i) {
            int count = 0;
            for (int j = 0; j < a.length; ++j) {
               if (a[j] == a[i]) ++count;
            }
            if (count > maxCount) {
               maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;
    }        
}
