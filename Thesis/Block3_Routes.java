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
public interface Block3_Routes {
    
    public void internalRoutes(List<OriginDestDuration> b3Exit1, List<OriginDestDuration> b3Exit2, List<OriginDestDuration> b3Exit3, List<OriginDestDuration> b3Exit4, 
            List<OriginDestDuration> b3Entrance1, List<OriginDestDuration> b3Entrance2, List<OriginDestDuration> b3Entrance3, List<OriginDestDuration> b3Entrance4, 
            List<OriginDestDuration> b3InBlock, List<OriginDestDuration> b3External1, List<OriginDestDuration> b3External2, List<OriginDestDuration> b3External3, 
            List<OriginDestDuration> b3External4, List<OriginDestDuration> b3External5, List<OriginDestDuration> b3);
    
    public void route_to_B1(List<OriginDestDuration> b3Exit1, List<OriginDestDuration> b3External1, List<OriginDestDuration> b1Entrance2, 
            List<OriginDestDuration> b3);
    
    public void route_to_B2(List<OriginDestDuration> b3Exit2, List<OriginDestDuration> b3External2, List<OriginDestDuration> b3External3, 
            List<OriginDestDuration> b2Entrance2, List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b3);
    
}
