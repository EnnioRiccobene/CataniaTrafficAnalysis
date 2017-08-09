/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import com.google.maps.GeoApiContext;

/**
 *
 * @author ennio
 */
public class RequestUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Apiurl a = new Apiurl();
        GeoApiContext context1 = new GeoApiContext().setApiKey(a.getApikey1());
        GeoApiContext context2 = new GeoApiContext().setApiKey(a.getApikey2());
        GeoApiContext context3 = new GeoApiContext().setApiKey(a.getApikey3());
        GeoApiContext context4 = new GeoApiContext().setApiKey(a.getApikey4());
        GeoApiContext context5 = new GeoApiContext().setApiKey(a.getApikey5());
        GeoApiContext context6 = new GeoApiContext().setApiKey(a.getApikey6());
        GeoApiContext context7 = new GeoApiContext().setApiKey(a.getApikey7());
        GeoApiContext context8 = new GeoApiContext().setApiKey(a.getApikey8());
        GeoApiContext context9 = new GeoApiContext().setApiKey(a.getApikey9());
        GeoApiContext context10 = new GeoApiContext().setApiKey(a.getApikey10());
        
        Block4 b1 = new Block4(context1, context2, context3, context4, context5, context6, context7, context8, context9, context10);
        b1.distanceToEntrance2();
        //variando il metodo precedente ottengo l'elenco di tutti i diversi URL di risposta, da copiare nei vari file.txt che poi dovranno essere puliti 
    }
    
}
