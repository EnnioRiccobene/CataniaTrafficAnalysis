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
public class Block {    
    double[] lat = new double[4];
    double[] lng = new double[4];
    GeoApiContext context1, context2, context3, context4, context5, context6, context7, context8, context9, context10;//sono le 10 ApiKey

    public Block(GeoApiContext context1, GeoApiContext context2, GeoApiContext context3, GeoApiContext context4, GeoApiContext context5, GeoApiContext context6, GeoApiContext context7, GeoApiContext context8, GeoApiContext context9, GeoApiContext context10) {
        this.context1 = context1;
        this.context2 = context2;
        this.context3 = context3;
        this.context4 = context4;
        this.context5 = context5;
        this.context6 = context6;
        this.context7 = context7;
        this.context8 = context8;
        this.context9 = context9;
        this.context10 = context10;
    }

    public double[] getLat() {
        return lat;
    }

    public void setLat(double[] lat) {
        this.lat = lat;
    }

    public double[] getLng() {
        return lng;
    }

    public void setLng(double[] lng) {
        this.lng = lng;
    }   
    
}
