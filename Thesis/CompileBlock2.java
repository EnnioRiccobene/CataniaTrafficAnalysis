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
public class CompileBlock2 extends CompileRoute implements Block2_Routes{
	//classe contenente i metodi che prendono gli oggetti dalle varie liste di supporto, che sono organizzate come i file di testo contenenti gli URL dei vari percorsi
	//e mettono i vari oggetti che contengono in un unica lista che verrà poi salvata sul database. Il metodo internalRoutes fa solo questo passaggio, gli altri si 
	//occupano anche di calcolare i percorsi che da un blocco finiscono in un altro, salvando nel campo duration solo il più breve per ogni possibile rotta che
	//dalla stessa origine porta alla stessa destinazione
    public CompileBlock2(UrlOperations op, OriginDestDuration odd, List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2, 
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
    public void internalRoutes(List<OriginDestDuration> b2Exit1, List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b2Entrance1, 
            List<OriginDestDuration> b2Entrance2, List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b2InBlock, List<OriginDestDuration> b2External1, 
            List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b2) {
        
        for(int i = 0; i < b2Exit1.size(); i++){
            b2.add(b2Exit1.get(i));
        }
        for(int i = 0; i < b2Exit2.size(); i++){
            b2.add(b2Exit2.get(i));
        }
        for(int i = 0; i < b2Exit3.size(); i++){
            b2.add(b2Exit3.get(i));
        }
        for(int i = 0; i < b2Entrance1.size(); i++){
            b2.add(b2Entrance1.get(i));
        }
        for(int i = 0; i < b2Entrance2.size(); i++){
            b2.add(b2Entrance2.get(i));
        }
        for(int i = 0; i < b2Entrance3.size(); i++){
            b2.add(b2Entrance3.get(i));
        }
        
        for(int i = 0; i < b2.size() - 1; i++) {
            if (b2.get(i).equals(b2.get(i + 1))) {                 
                b2.remove(b2.get(i));
            } 
        }
        
        for(int i = 0; i < b2InBlock.size(); i++){
            b2.add(b2InBlock.get(i));
        }
        b2.add(b2External1.get(0));
        b2.add(b2External2.get(0));
        b2.add(b2External3.get(0));
    }

    @Override
    public void route_to_B1(List<OriginDestDuration> b2Exit1, List<OriginDestDuration> b2External1, List<OriginDestDuration> b1Entrance1, List<OriginDestDuration> b2) {
        
        for(int i = 0; i < b2Exit1.size(); i++){
            
            OriginDestDuration o1 = new OriginDestDuration();            
                
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            
            int dur0, dur01, dur_m0;
            
            dur01 = b2Exit1.get(i).getDur_value();
            dur_m0 = b2External1.get(0).getDur_value();
            
            dur0 = dur01 + dur_m0;
            
            o1.setOrigin(b2Exit1.get(i).getOrigin());
            o1.setDestination(b2External1.get(0).getDestination());
            o1.setDur_value(dur0);
            o1.setDur_text(computeDurText(o1.getDur_value()));
            o1.setHour(currentTimestamp);
                
            b2.add(o1);
            
            for(int j = 0; j < b1Entrance1.size(); j++){
                OriginDestDuration o = new OriginDestDuration();
                
                int dur, dur1, dur_m, dur2;
                dur1 = b2Exit1.get(i).getDur_value();
                dur_m = b2External1.get(0).getDur_value();
                dur2 = b1Entrance1.get(j).getDur_value();
                dur = dur1 + dur_m + dur2;
                
                o.setOrigin(b2Exit1.get(i).getOrigin());
                o.setDestination(b1Entrance1.get(j).getDestination());
                o.setDur_value(dur);
                o.setDur_text(computeDurText(o.getDur_value()));
                o.setHour(currentTimestamp);
                
                b2.add(o);
                
                OriginDestDuration o2 = new OriginDestDuration();
                
                int durz;
                durz = dur_m + dur2;
                
                o2.setOrigin(b2External1.get(0).getOrigin());
                o2.setDestination(b1Entrance1.get(j).getDestination());
                o2.setDur_value(durz);
                o2.setDur_text(computeDurText(o2.getDur_value()));
                o2.setHour(currentTimestamp);
                
                b2.add(o2);                
            }
        }
    }

    @Override
    public void route_to_B3(List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b3Entrance2, List<OriginDestDuration> b2External2, 
            List<OriginDestDuration> b2External3, List<OriginDestDuration> b2) {
        
        for(int i = 0; i < b2Exit2.size(); i++){         
            
            OriginDestDuration o0 = new OriginDestDuration();
                
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
            
            if(i != 0 && 1 != 1){
                int dur0, dur00, dur01, dur001, dur_m0, dur_m01, durn;
            
                dur01 = b2Exit2.get(i).getDur_value();
                dur_m0 = b2External2.get(0).getDur_value();
                dur_m01 = b2External3.get(0).getDur_value();
                dur001 = b2Exit3.get(i).getDur_value();            
                dur0 = dur01 + dur_m0;
                dur00 = dur001 + dur_m01;
                durn = takeLower(dur0, dur00);
            
                o0.setOrigin(b2Exit2.get(i).getOrigin());
                o0.setDestination(b2External2.get(0).getDestination());
                o0.setDur_value(durn);
                o0.setDur_text(computeDurText(o0.getDur_value()));
                o0.setHour(currentTimestamp);
                
                b2.add(o0);
                
            } else if(i == 0){
                
                int dur_m0, dur_m01, durn, dur01, dur001, dur0, dur00;
                
                dur01 = b2Exit2.get(1).getDur_value();
                dur_m0 = b2External2.get(0).getDur_value();
                dur_m01 = b2External3.get(0).getDur_value();
                dur001 = b2Exit3.get(i).getDur_value();
                dur0 = dur01 + dur_m0;
                dur00 = dur001 + dur_m01;
                durn = takeLower(dur0, dur00);
                
                o0.setOrigin(b2Exit2.get(1).getOrigin());
                o0.setDestination(b2External2.get(0).getDestination());
                o0.setDur_value(durn);
                o0.setDur_text(computeDurText(o0.getDur_value()));
                o0.setHour(currentTimestamp);
                
                b2.add(o0);
            }
            
            for(int j = 0; j < b3Entrance2.size(); j++){
                
                int dur, dur22, dur32, dur3;
                
                dur22 = b2External2.get(0).getDur_value();
                dur32 = b2External3.get(0).getDur_value();
                
                dur3 = b3Entrance2.get(j).getDur_value();
                
                OriginDestDuration o = new OriginDestDuration();
                
                if(i != 0 && i != 1){ 
                    
                    int dura, durb;
                    
                    dura = b2Exit2.get(i).getDur_value() + dur22;
                    durb = b2Exit3.get(i).getDur_value() + dur32;
                    dur = dur3 + takeLower(dura, durb);
                    
                    o.setOrigin(b2Exit2.get(i).getOrigin());
                    o.setDestination(b3Entrance2.get(j).getDestination());
                    o.setDur_value(dur);
                    o.setDur_text(computeDurText(o.getDur_value()));
                    o.setHour(currentTimestamp);  
                    
                    b2.add(o);
                    
                } else {
                    
                    if(i == 0){
                        
                        int durx;
                        durx = dur3 + dur22;
                        o.setOrigin(b2External2.get(0).getOrigin());
                        o.setDestination(b3Entrance2.get(j).getDestination());
                        o.setDur_value(durx);
                        o.setDur_text(computeDurText(o.getDur_value()));
                        o.setHour(currentTimestamp);
                        
                        OriginDestDuration o1 = new OriginDestDuration();
                        
                        int dura, dura1, durb, durb1, durf;
                        dura1 = b2Exit2.get(1).getDur_value(); 
                        dura = dura1 + b2External2.get(0).getDur_value();
                        durb1 = b2Exit3.get(0).getDur_value(); 
                        durb = durb1 + b2External3.get(0).getDur_value();
                        durf = takeLower(dura, durb) + dur3;
                        
                        o1.setOrigin(b2Exit2.get(1).getOrigin()); 
                        o1.setDestination(b3Entrance2.get(j).getDestination());
                        o1.setDur_value(durf);
                        o1.setDur_text(computeDurText(o1.getDur_value()));
                        o1.setHour(currentTimestamp);
                        
                        b2.add(o);
                        b2.add(o1); 
                        
                    } else if (i == 1){ 
                        
                        int durx;
                        durx = dur3 + dur32;
                        o.setOrigin(b2External3.get(0).getOrigin());
                        o.setDestination(b3Entrance2.get(j).getDestination());
                        o.setDur_value(durx);
                        o.setDur_text(computeDurText(o.getDur_value()));
                        o.setHour(currentTimestamp);                                               
                        
                        b2.add(o); 
                    }
                }                
            }            
        }
    }    
}