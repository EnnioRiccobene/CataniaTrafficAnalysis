/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ennio
 */
public class MongoUI {
    
    private static Timer timer = new Timer();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Calendar calendar = Calendar.getInstance();
                      
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("tesi");
        DBCollection table = db.getCollection("cataniatraffic");
            
        UrlOperations op = new UrlOperations();    
        OriginDestDuration odd = new OriginDestDuration();
        //dichiarazione delle liste di supporto che inizialmente sono vuote
        List<OriginDestDuration> b1InBlock = new ArrayList();
        List<OriginDestDuration> b1Exit1 = new ArrayList();
        List<OriginDestDuration> b1Exit2 = new ArrayList();
        List<OriginDestDuration> b1Entrance1 = new ArrayList();
        List<OriginDestDuration> b1Entrance2 = new ArrayList();
        List<OriginDestDuration> b2InBlock = new ArrayList();
        List<OriginDestDuration> b2Exit1 = new ArrayList();
        List<OriginDestDuration> b2Exit2 = new ArrayList();
        List<OriginDestDuration> b2Exit3 = new ArrayList();
        List<OriginDestDuration> b2Entrance1 = new ArrayList();
        List<OriginDestDuration> b2Entrance2 = new ArrayList();
        List<OriginDestDuration> b2Entrance3 = new ArrayList();
        List<OriginDestDuration> b3InBlock = new ArrayList();
        List<OriginDestDuration> b3Exit1 = new ArrayList();
        List<OriginDestDuration> b3Exit2 = new ArrayList();
        List<OriginDestDuration> b3Entrance1 = new ArrayList();
        List<OriginDestDuration> b3Entrance2 = new ArrayList();
        
        List<OriginDestDuration> b1External1 = new ArrayList();
        List<OriginDestDuration> b1External2 = new ArrayList();
        List<OriginDestDuration> b2External1 = new ArrayList();
        List<OriginDestDuration> b2External2 = new ArrayList();
        List<OriginDestDuration> b2External3 = new ArrayList();
        List<OriginDestDuration> b3External1 = new ArrayList();
        List<OriginDestDuration> b3External2 = new ArrayList();
        List<OriginDestDuration> b3External3 = new ArrayList();
        
        CompileBlock1 sl1 = new CompileBlock1(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
            b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
        CompileBlock2 sl2 = new CompileBlock2(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
            b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
        CompileBlock3 sl3 = new CompileBlock3(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
            b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
        //il seguente metodo riempie tutte le liste di supporto creando gli oggetti grazie ai dati forniti da Google
        try {
            sl1.fillSupportList(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2, b2Entrance3, b3InBlock,
                    b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
        } catch (IOException ex) {
            Logger.getLogger(mongoprova3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<OriginDestDuration> b = new ArrayList();
		//vengono creati tutti gli oggetti di collegamento tra più blocchi e il totale degli oggetti viene salvato, sempre con lo stesso ordine, nella lista b
        sl1.internalRoutes(b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b1InBlock, b1External1, b1External2, b);
        sl1.route_to_B2(b1Exit1, b1External1, b2Entrance1, b);
        sl1.route_to_B3(b1Exit2, b1External2, b3Entrance1, b);
        
        sl2.internalRoutes(b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2, b2Entrance3, b2InBlock, b2External1, b2External2, b2External3, b);
        sl2.route_to_B1(b2Exit1, b2External1, b1Entrance1, b);
        sl2.route_to_B3(b2Exit2, b2Exit3, b3Entrance2, b2External2, b2External3, b);
        
        sl3.internalRoutes(b3Exit1, b3Exit2, b3Exit2, b3Exit2, b3Entrance1, b3Entrance2, b3Entrance2, b3Entrance2, b3InBlock, b3External1, b3External2, b3External3, b3External3, 
            b3External3, b);
        sl3.route_to_B1(b3Exit1, b3External1, b1Entrance2, b);
        sl3.route_to_B2(b3Exit2, b3External2, b3External3, b2Entrance2, b2Entrance3, b);
        //A questo punto le liste di supporto non servono più
        Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());        
        System.out.println("Sto cominciando a salvare i dati sul db. Ora: " + currentTimestamp);
        //Con questo for viene creato il database, la collection "cataniatraffic" e tutti i documenti corrispondenti ai 1394 percorsi i quali
		//vengono salvati per la prima volta nel database
        for(int i = 0; i < b.size(); i++){
            BasicDBObject document = new BasicDBObject();
            document.put("_id", i);
            document.put("destination", b.get(i).getDestination());
            document.put("origin", b.get(i).getOrigin());
			//duration_value, duration_text, timestamp, vengono creari come array che verranno aggiornate nei successivi cicli di esecuzione
            List<Integer> duration_value = new ArrayList<>();
            duration_value.add(b.get(i).getDur_value());
            document.put("duration_value", duration_value);
            
            List<String> duration_text = new ArrayList<>();
            duration_text.add(b.get(i).getDur_text());
            document.put("duration_text", duration_text);
            
            List<Timestamp> timestamp = new ArrayList<>();
            timestamp.add(b.get(i).getHour());
            document.put("timestamp", timestamp);
        
            table.insert(document);//i documenti creati vengono inseriti nel database
            
            //aggiunta del calcolo e di media, moda e mediana:            
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", i);
            DBObject result = table.findOne(searchQuery);
            
            BasicDBList number = (BasicDBList)result.get("duration_value");
            
            
            List<Integer> tmp = new ArrayList<>();
            //viene prelevato, per ogni documento, l'array contenente i valori interi di durata espressi in secondi, dalla quale vengono calcolati i valori di
			//media, moda e mediana; anch'essi salvati come array
            for(int j = 0; j < number.size(); j++){
                int integer = ((Number)number.get(j)).intValue(); 
                
                tmp.add(integer);                
            }
            MyMath m = new MyMath(tmp);
            double media, mediana;
            int moda;
            media = m.media(tmp);
            mediana = m.mediana(tmp);
            moda = m.moda(tmp);
            
            //aggiunta degli array di media, moda e mediana:
            
            List<Double> media_array = new ArrayList<>();
            media_array.add(media);
            document.put("media", media_array);
            
            List<Integer> moda_array = new ArrayList<>();
            moda_array.add(moda);
            document.put("moda", moda_array);
            
            List<Double> mediana_array = new ArrayList<>();
            mediana_array.add(mediana);
            document.put("mediana", mediana_array);
            
            BasicDBObject query = new BasicDBObject();
            query.put("_id", i);
                
            BasicDBObject newDocument3 = new BasicDBObject();
                newDocument3.put("media", media);
                BasicDBObject updateObj3 = new BasicDBObject();
                updateObj3.put("$push", newDocument3);
                table.update(query, updateObj3);
                
                BasicDBObject newDocument4 = new BasicDBObject();
                newDocument4.put("moda", moda);
                BasicDBObject updateObj4 = new BasicDBObject();
                updateObj4.put("$push", newDocument4);
                table.update(query, updateObj4);
                
                BasicDBObject newDocument5 = new BasicDBObject();
                newDocument5.put("mediana", mediana);
                BasicDBObject updateObj5 = new BasicDBObject();
                updateObj5.put("$push", newDocument5);
                table.update(query, updateObj5);
            
        }
        
        Timestamp currentTimestamp1 = new java.sql.Timestamp(calendar.getTime().getTime());
        System.out.println("Dati salvati sul db. Ora: " + currentTimestamp1);
        int a = 1000*60*30;
        
        Thread.sleep(a); //dopo una pausa di 30 minuti, viene lanciato il thread TrafficTask, il quale ripeterà la sua esecuzione ogni 30 minuti, finchè il programma non verrà interrotto manualmente
        */
        timer.schedule (new MongoUI.TrafficTask(),0,1000*60*30); //esegue ogni 30 minuti 
    }
    
	//Thread che viene eseguito ogni 30 minuti e che svolge un lavoro simile al precedente, con la differenza che esegue solo query di update sui documenti 
	//che sono già stati creati e non esegue mai la query di insert. Vengono solo aggiornati gli array, i campi che non lo sono non vengono cambiati, in quanto il loro 
	//valore non muta nel tempo (ad esempio: indirizzo di origine e di destinazione per ogni percorso non vengono toccati)
    private static class TrafficTask extends TimerTask {
        @Override
        public void run() {
            
            Calendar calendar = Calendar.getInstance();
            
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("tesi");
            DBCollection table = db.getCollection("cataniatraffic");
            
            UrlOperations op = new UrlOperations();    
            OriginDestDuration odd = new OriginDestDuration();
        
            List<OriginDestDuration> b1InBlock = new ArrayList();
            List<OriginDestDuration> b1Exit1 = new ArrayList();
            List<OriginDestDuration> b1Exit2 = new ArrayList();
            List<OriginDestDuration> b1Entrance1 = new ArrayList();
            List<OriginDestDuration> b1Entrance2 = new ArrayList();
            List<OriginDestDuration> b2InBlock = new ArrayList();
            List<OriginDestDuration> b2Exit1 = new ArrayList();
            List<OriginDestDuration> b2Exit2 = new ArrayList();
            List<OriginDestDuration> b2Exit3 = new ArrayList();
            List<OriginDestDuration> b2Entrance1 = new ArrayList();
            List<OriginDestDuration> b2Entrance2 = new ArrayList();
            List<OriginDestDuration> b2Entrance3 = new ArrayList();
            List<OriginDestDuration> b3InBlock = new ArrayList();
            List<OriginDestDuration> b3Exit1 = new ArrayList();
            List<OriginDestDuration> b3Exit2 = new ArrayList();
            List<OriginDestDuration> b3Entrance1 = new ArrayList();
            List<OriginDestDuration> b3Entrance2 = new ArrayList();
        
            List<OriginDestDuration> b1External1 = new ArrayList();
            List<OriginDestDuration> b1External2 = new ArrayList();
            List<OriginDestDuration> b2External1 = new ArrayList();
            List<OriginDestDuration> b2External2 = new ArrayList();
            List<OriginDestDuration> b2External3 = new ArrayList();
            List<OriginDestDuration> b3External1 = new ArrayList();
            List<OriginDestDuration> b3External2 = new ArrayList();
            List<OriginDestDuration> b3External3 = new ArrayList();
        
            CompileBlock1 sl1 = new CompileBlock1(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
                b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
            CompileBlock2 sl2 = new CompileBlock2(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
                b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
            CompileBlock3 sl3 = new CompileBlock3(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2,
                b2Entrance3, b3InBlock, b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
        
            try {
                sl1.fillSupportList(op, odd, b1InBlock, b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b2InBlock, b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2, b2Entrance3, b3InBlock,
                        b3Exit1, b3Exit2, b3Entrance1, b3Entrance2, b1External1, b1External2, b2External1, b2External2, b2External3, b3External1, b3External2, b3External3);
            } catch (IOException ex) {
                Logger.getLogger(mongoprova3.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            List<OriginDestDuration> b = new ArrayList();

            sl1.internalRoutes(b1Exit1, b1Exit2, b1Entrance1, b1Entrance2, b1InBlock, b1External1, b1External2, b);
            sl1.route_to_B2(b1Exit1, b1External1, b2Entrance1, b);
            sl1.route_to_B3(b1Exit2, b1External2, b3Entrance1, b);
        
            sl2.internalRoutes(b2Exit1, b2Exit2, b2Exit3, b2Entrance1, b2Entrance2, b2Entrance3, b2InBlock, b2External1, b2External2, b2External3, b);
            sl2.route_to_B1(b2Exit1, b2External1, b1Entrance1, b);
            sl2.route_to_B3(b2Exit2, b2Exit3, b3Entrance2, b2External2, b2External3, b);
        
            sl3.internalRoutes(b3Exit1, b3Exit2, b3Exit2, b3Exit2, b3Entrance1, b3Entrance2, b3Entrance2, b3Entrance2, b3InBlock, b3External1, b3External2, b3External3, b3External3, 
                b3External3, b);
            sl3.route_to_B1(b3Exit1, b3External1, b1Entrance2, b);
            sl3.route_to_B2(b3Exit2, b3External2, b3External3, b2Entrance2, b2Entrance3, b);
            
            Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());        
            System.out.println("Sto cominciando a salvare i dati sul db. Ora: " + currentTimestamp);
            
            for(int i = 0; i < b.size(); i++){
                //update
                BasicDBObject query = new BasicDBObject();
                query.put("_id", i);

                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("duration_value", b.get(i).getDur_value());
                BasicDBObject updateObj = new BasicDBObject();
                updateObj.put("$push", newDocument);
                table.update(query, updateObj);
                
                BasicDBObject newDocument1 = new BasicDBObject();
                newDocument1.put("duration_text", b.get(i).getDur_text());
                BasicDBObject updateObj1 = new BasicDBObject();
                updateObj1.put("$push", newDocument1);
                table.update(query, updateObj1);
                
                BasicDBObject newDocument2 = new BasicDBObject();
                newDocument2.put("timestamp", b.get(i).getHour());
                BasicDBObject updateObj2 = new BasicDBObject();
                updateObj2.put("$push", newDocument2);
                table.update(query, updateObj2);
                
                //aggiunta del calcolo di media, moda e mediana:
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.put("_id", i);
                DBObject result = table.findOne(searchQuery);
            
                BasicDBList number = (BasicDBList)result.get("duration_value");
                        
                List<Integer> tmp = new ArrayList<>();
                        
                for(int j = 0; j < number.size(); j++){
                    int integer = ((Number)number.get(j)).intValue(); 
                
                    tmp.add(integer);                
                }
                MyMath m = new MyMath(tmp);
                double media, mediana;
                int moda;
                media = m.media(tmp);
                mediana = m.mediana(tmp);
                moda = m.moda(tmp);
            
                //update degli array di media, moda e mediana:
                BasicDBObject newDocument3 = new BasicDBObject();
                newDocument3.put("media", media);
                BasicDBObject updateObj3 = new BasicDBObject();
                updateObj3.put("$push", newDocument3);
                table.update(query, updateObj3);
                
                BasicDBObject newDocument4 = new BasicDBObject();
                newDocument4.put("moda", moda);
                BasicDBObject updateObj4 = new BasicDBObject();
                updateObj4.put("$push", newDocument4);
                table.update(query, updateObj4);
                
                BasicDBObject newDocument5 = new BasicDBObject();
                newDocument5.put("mediana", mediana);
                BasicDBObject updateObj5 = new BasicDBObject();
                updateObj5.put("$push", newDocument5);
                table.update(query, updateObj5);
            }
            
            Timestamp currentTimestamp1 = new java.sql.Timestamp(calendar.getTime().getTime());
            System.out.println("Dati salvati sul db. Ora: " + currentTimestamp1);                      
            
        }
    }    
}
