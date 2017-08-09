/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.util.List;

/**
 *
 * @author ennio
 */
 //Classe utile per il parsing Json
public class SRD {
    private List<String> destination_addresses;
    private List<String> origin_addresses;
    private List<Row> rows;

    public List<String> getDestination_addresses() {
        return destination_addresses;
    }

    public List<String> getOrigin_addresses() {
        return origin_addresses;
    }

    public List<Row> getRows() {
        return rows;
    }
    
}
