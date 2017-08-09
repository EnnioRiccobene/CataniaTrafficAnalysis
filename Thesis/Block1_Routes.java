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
public interface Block1_Routes {
    
    public void internalRoutes(List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2, List<OriginDestDuration> b1Entrance1, 
            List<OriginDestDuration> b1Entrance2, List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1External1, List<OriginDestDuration> b1External2, 
            List<OriginDestDuration> b1);
    
    public void route_to_B2(List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1External1, List<OriginDestDuration> b2Entrance1, 
            List<OriginDestDuration> b1);
    
    public void route_to_B3(List<OriginDestDuration> b1Exit2, List<OriginDestDuration> b1External2, List<OriginDestDuration> b3Entrance1, 
            List<OriginDestDuration> b1);
    
}
