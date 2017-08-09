/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ennio
 */
public class CompileBlock1 extends CompileRoute implements Block1_Routes{
	//classe contenente i metodi che prendono gli oggetti dalle varie liste di supporto, che sono organizzate come i file di testo contenenti gli URL dei vari percorsi
	//e mettono i vari oggetti che contengono in un unica lista che verrà poi salvata sul database. Il metodo internalRoutes fa solo questo passaggio, gli altri si 
	//occupano anche di calcolare i percorsi che da un blocco finiscono in un altro, salvando nel campo duration solo il più breve per ogni possibile rotta che
	//dalla stessa origine porta alla stessa destinazione
    public CompileBlock1(UrlOperations op, OriginDestDuration odd, List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2, 
            List<OriginDestDuration> b1Entrance1, List<OriginDestDuration> b1Entrance2, List<OriginDestDuration> b2InBlock, List<OriginDestDuration> b2Exit1, 
            List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b2Entrance1, List<OriginDestDuration> b2Entrance2, 
            List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b3InBlock, List<OriginDestDuration> b3Exit1, List<OriginDestDuration> b3Exit2, 
            List<OriginDestDuration> b3Entrance1, List<OriginDestDuration> b3Entrance2, List<OriginDestDuration> b1External1, List<OriginDestDuration> b1External2, 
            List<OriginDestDuration> b2External1, List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b3External1, 
            List<OriginDestDuration> b3External2, List<OriginDestDuration> b3External3) {
        super(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2, b2Entrance3, b3InBlock, b3Exit1, 
                b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
    }
    
    @Override
    public void internalRoutes(List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2, List<OriginDestDuration> b1Entrance1, List<OriginDestDuration> b1Entrance2, 
            List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1External1, List<OriginDestDuration> b1External2, List<OriginDestDuration> b1) {
        
        for(int i = 0; i < b1Exit1.size(); i++){
            b1.add(b1Exit1.get(i));
        }
        for(int i = 0; i < b1Exit2.size(); i++){
            b1.add(b1Exit2.get(i));
        }
        for(int i = 0; i < b1Entrance1.size(); i++){
            b1.add(b1Entrance1.get(i));
        }
        for(int i = 0; i < b1Entrance2.size(); i++){
            b1.add(b1Entrance2.get(i));
        }
        
        for(int i = 0; i < b1.size() - 1; i++) {
            if (b1.get(i).equals(b1.get(i + 1))) {                 
                b1.remove(b1.get(i));
            } 
        }
        
        for(int i = 0; i < b1InBlock.size(); i++){
            b1.add(b1InBlock.get(i));
        }
        b1.add(b1External1.get(0));
        b1.add(b1External2.get(0));
        
    }

    @Override
    public void route_to_B2(List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1External1, List<OriginDestDuration> b2Entrance1, List<OriginDestDuration> b1) {
        
        for(int i = 0; i < b1Exit1.size(); i++){
            
            OriginDestDuration o1 = new OriginDestDuration();
                
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            
            int dur0, dur01, dur_m0;
            
            dur01 = b1Exit1.get(i).getDur_value();
            dur_m0 = b1External1.get(0).getDur_value();
            
            dur0 = dur01 + dur_m0;
            
            o1.setOrigin(b1Exit1.get(i).getOrigin());
            o1.setDestination(b1External1.get(0).getDestination());
            o1.setDur_value(dur0);
            o1.setDur_text(computeDurText(o1.getDur_value()));
            o1.setHour(currentTimestamp);
                
            b1.add(o1);
            
            for(int j = 0; j < b2Entrance1.size(); j++){
                OriginDestDuration o = new OriginDestDuration();
                
                int dur, dur1, dur_m, dur2;
                dur1 = b1Exit1.get(i).getDur_value();
                dur_m = b1External1.get(0).getDur_value();
                dur2 = b2Entrance1.get(j).getDur_value();
                dur = dur1 + dur_m + dur2;
                
                o.setOrigin(b1Exit1.get(i).getOrigin());
                o.setDestination(b2Entrance1.get(j).getDestination());
                o.setDur_value(dur);
                o.setDur_text(computeDurText(o.getDur_value()));
                o.setHour(currentTimestamp);
                
                b1.add(o);
                
                OriginDestDuration o2 = new OriginDestDuration();
                
                int durx;
                durx = dur_m + dur2;
                
                o2.setOrigin(b1External1.get(0).getOrigin());
                o2.setDestination(b2Entrance1.get(j).getDestination());
                o2.setDur_value(durx);
                o2.setDur_text(computeDurText(o2.getDur_value()));
                o2.setHour(currentTimestamp);
                
                b1.add(o2);
            }                       
        }
    }

    @Override
    public void route_to_B3(List<OriginDestDuration> b1Exit2, List<OriginDestDuration> b1External2, List<OriginDestDuration> b3Entrance1, List<OriginDestDuration> b1) {
        
        for(int i = 0; i < b1Exit2.size(); i++){
            
            OriginDestDuration o1 = new OriginDestDuration();
                
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            
            int dur0, dur01, dur_m0;
            
            dur01 = b1Exit2.get(i).getDur_value();
            dur_m0 = b1External2.get(0).getDur_value();
            
            dur0 = dur01 + dur_m0;
            
            o1.setOrigin(b1Exit2.get(i).getOrigin());
            o1.setDestination(b1External2.get(0).getDestination());
            o1.setDur_value(dur0);
            o1.setDur_text(computeDurText(o1.getDur_value()));
            o1.setHour(currentTimestamp);
                
            b1.add(o1);
            
            for(int j = 0; j < b3Entrance1.size(); j++){
                OriginDestDuration o = new OriginDestDuration();
                
                int dur, dur1, dur_m, dur2;
                dur1 = b1Exit2.get(i).getDur_value();
                dur_m = b1External2.get(0).getDur_value();
                dur2 = b3Entrance1.get(j).getDur_value();
                dur = dur1 + dur_m + dur2;
                
                o.setOrigin(b1Exit2.get(i).getOrigin());
                o.setDestination(b3Entrance1.get(j).getDestination());
                o.setDur_value(dur);
                o.setDur_text(computeDurText(o.getDur_value()));
                o.setHour(currentTimestamp);
                
                b1.add(o);
                
                OriginDestDuration o2 = new OriginDestDuration();
                
                int durx;                
                durx = dur_m + dur2;
                
                o2.setOrigin(b1External2.get(0).getOrigin());
                o2.setDestination(b3Entrance1.get(j).getDestination());
                o2.setDur_value(durx);
                o2.setDur_text(computeDurText(o2.getDur_value()));
                o2.setHour(currentTimestamp);
                
                b1.add(o2);
            }
        }
    }  
}