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
public interface Block2_Routes {
    
    public void internalRoutes(List<OriginDestDuration> b2Exit1, List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, 
            List<OriginDestDuration> b2Entrance1, List<OriginDestDuration> b2Entrance2, List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b2InBlock, 
            List<OriginDestDuration> b2External1, List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b2);
    
    public void route_to_B1(List<OriginDestDuration> b2Exit1, List<OriginDestDuration> b2External1, List<OriginDestDuration> b1Entrance1, 
            List<OriginDestDuration> b2);
    
    public void route_to_B3(List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b3Entrance2, 
            List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b2);
    
}
