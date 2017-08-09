/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;

/**
 *
 * @author ennio
 */
public class Block1 extends Block{   
    //Classe che si occupa di fare le richieste a Google per i punti del primo blocco
	//gli Switch-case nei metodi, servono a usare in maniera equilibrata tutte e 10 le ApiKey
    public Block1(GeoApiContext context1, GeoApiContext context2, GeoApiContext context3, GeoApiContext context4, GeoApiContext context5, GeoApiContext context6, GeoApiContext context7, GeoApiContext context8, GeoApiContext context9, GeoApiContext context10) {
        super(context1, context2, context3, context4, context5, context6, context7, context8, context9, context10);
        lat[0] = 37.52189553;
        lat[1] = 37.53748772;
        lat[2] = 37.55307991;
        lng[0] = 15.03716658;
        lng[1] = 15.05330715;
        lng[2] = 15.06944772;
    }
	
	//richieste per i punti interni al blocco esclusi ingressi e uscite
    public void distanceInBlock() {
        GeoApiContext context; 
        int c = 1;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    for(int h = 0; h < 3; h++) {
                        if((lat[k] != lat[1] || lng[h] != lng[2]) && (lat[k] != lat[0] || lng[h] != lng[1]) && 
                                (lat[i] != lat[1] || lng[j] != lng[2]) && (lat[i] != lat[0] || lng[j] != lng[1]) && (i != k || j != h)){
                            switch (c) {
                                case 1: context = context1;
                                break;
                                case 2: context = context2;
                                break;
                                case 3: context = context3;
                                break;
                                case 4: context = context4;
                                break;
                                case 5: context = context5;
                                break;
                                case 6: context = context6;
                                break;
                                case 7: context = context7;
                                break;
                                case 8: context = context8;
                                break;
                                case 9: context = context9;
                                break;
                                case 10: context = context10;
                                break;
                                default: c = 1; context = context1;
                            }
                            
                            DistanceMatrixApi.newRequest(context)
                                    .origins(new LatLng(lat[i], lng[j]))
                                    .destinations(new LatLng(lat[k], lng[h]))
                                    .awaitIgnoreError();
                            
                            c = c + 1;
                            
                        } 
                    }
                }
            }
        }        
    }
    //richieste per i percorsi che partono dai punti interni del blocco e arrivano al primo punto di uscita
    public void distanceToExit1() {
        GeoApiContext context; 
        int c = 3;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {                                  
                if((i != 1) || (j != 2)){
                    switch (c) {
                        case 1: context = context1;
                        break;
                        case 2: context = context2;
                        break;
                        case 3: context = context3;
                        break;
                        case 4: context = context4;
                        break;
                        case 5: context = context5;
                        break;
                        case 6: context = context6;
                        break;
                        case 7: context = context7;
                        break;
                        case 8: context = context8;
                        break;
                        case 9: context = context9;
                        break;
                        case 10: context = context10;
                        break;
                        default: c = 1; context = context1;
                    }
                    
                    DistanceMatrixApi.newRequest(context)
                        .origins(new LatLng(lat[i], lng[j]))
                        .destinations(new LatLng(lat[1], lng[2]))
                        .awaitIgnoreError(); 
                    
                    c = c + 1;   
                    
                }                                   
            }            
        }
    }
    //richieste per i percorsi che partono dai punti interni del blocco e arrivano al secondo punto di uscita
    public void distanceToExit2() {
        GeoApiContext context; 
        int c = 1;
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {                                  
                if((i != 0) || (j != 1)){
                    switch (c) {
                        case 1: context = context1;
                        break;
                        case 2: context = context2;
                        break;
                        case 3: context = context3;
                        break;
                        case 4: context = context4;
                        break;
                        case 5: context = context5;
                        break;
                        case 6: context = context6;
                        break;
                        case 7: context = context7;
                        break;
                        case 8: context = context8;
                        break;
                        case 9: context = context9;
                        break;
                        case 10: context = context10;
                        break;
                        default: c = 1; context = context1;
                    }
                    
                    DistanceMatrixApi.newRequest(context)
                        .origins(new LatLng(lat[i], lng[j]))
                        .destinations(new LatLng(lat[0], lng[1]))
                        .awaitIgnoreError();                     
                    
                    c = c + 1;                            
                }                                    
            }            
        }
    }   
    
	//richiesta del percorso di collegamento tra il blocco 1 e il blocco 2
    public void externalB1_1toB2_1() {
        DistanceMatrixApi.newRequest(context9) 
            .origins(new LatLng(lat[1], lng[2]))
            .destinations(new LatLng(37.53748772, 15.08558828))
            .awaitIgnoreError();
    }
    //richiesta del percorso di collegamento tra il blocco 1 e il blocco 3
    public void externalB1_2toB3_1() {
        DistanceMatrixApi.newRequest(context10) 
            .origins(new LatLng(lat[0], lng[1]))
            .destinations(new LatLng(37.50630334, 15.05330715))
            .awaitIgnoreError();
    }    
	//richiesta per i percorsi che partono dal primo punto di entrata del blocco e arrivano a tutti gli altri interni allo stesso
    public void distanceToEntrance1() {
        GeoApiContext context; 
        int c = 1;
        
        for(int k = 0; k < 3; k++) {
            for(int h = 0; h < 3; h++) {                                  
                if((k != 1) || (h != 2)){
                    switch (c) {
                        case 1: context = context1;
                        break;
                        case 2: context = context2;
                        break;
                        case 3: context = context3;
                        break;
                        case 4: context = context4;
                        break;
                        case 5: context = context5;
                        break;
                        case 6: context = context6;
                        break;
                        case 7: context = context7;
                        break;
                        case 8: context = context8;
                        break;
                        case 9: context = context9;
                        break;
                        case 10: context = context10;
                        break;
                        default: c = 1; context = context1;
                    }
                    
                    DistanceMatrixApi.newRequest(context)
                        .origins(new LatLng(lat[1], lng[2]))
                        .destinations(new LatLng(lat[k], lng[h]))
                        .awaitIgnoreError(); 
                    
                    c = c + 1;                       
                }                                 
            }            
        }
    }
	//richiesta per i percorsi che partono dal secondo punto di entrata del blocco e arrivano a tutti gli altri interni allo stesso
    public void distanceToEntrance2() {        
        GeoApiContext context; 
        int c = 9;
        
        for(int k = 0; k < 3; k++) {
            for(int h = 0; h < 3; h++) {                                  
                if((k != 0) || (h != 1)){
                    switch (c) {
                        case 1: context = context1;
                        break;
                        case 2: context = context2;
                        break;
                        case 3: context = context3;
                        break;
                        case 4: context = context4;
                        break;
                        case 5: context = context5;
                        break;
                        case 6: context = context6;
                        break;
                        case 7: context = context7;
                        break;
                        case 8: context = context8;
                        break;
                        case 9: context = context9;
                        break;
                        case 10: context = context10;
                        break;
                        default: c = 1; context = context1;
                    }
                    
                    DistanceMatrixApi.newRequest(context)
                        .origins(new LatLng(lat[0], lng[1]))
                        .destinations(new LatLng(lat[k], lng[h]))
                        .awaitIgnoreError(); 
                    
                    c = c + 1;                       
                }                                 
            }            
        }
    }
}