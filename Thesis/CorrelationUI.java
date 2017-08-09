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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ennio
 */
public class CorrelationUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                
        MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("tesi");
        DBCollection table = db.getCollection("cataniatraffic");
        
        List<DBRoute> routeList = new ArrayList<>();
        List<CorrelatedRoutes> x = new ArrayList<CorrelatedRoutes>();
        
		//prendo dal database tutti i dati salvati di ogni percorso e creo i corrispondenti oggetti DBRoute, aggiungendoli in una lista
        for(int i = 0; i < table.count(); i++){
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", i);
            DBObject result = table.findOne(searchQuery);
        
            BasicDBList number = (BasicDBList)result.get("duration_value");
            String origin = (String) result.get("origin");
            String destination = (String) result.get("destination");
                                        
            List<Integer> tmp = new ArrayList<>();
                        
            for(int j = 0; j < number.size(); j++){
                int integer = ((Number)number.get(j)).intValue();                
                tmp.add(integer);                
            }
        
            DBRoute route = new DBRoute(origin, destination, tmp);
            routeList.add(route);
        }
        
        routeList.get(0).getCorrelation(routeList, x);
        //stampo a schermo la lista o almeno i valori più significativi (punti iniziali e finali della lista)              
        System.out.println();
        System.out.println("Oggetti con correlazione minore:");
        System.out.println();
        for(int i = 0; i < 10; i++){
            System.out.println(x.get(i).toString());
        }
        System.out.println();
        System.out.println("Oggetti con correlazione intermedia:");
        System.out.println();
        for(int i = x.size()/2 - 5; i < x.size()/2 + 5; i++){
            System.out.println(x.get(i).toString());
        }
        System.out.println();
        System.out.println("Oggetti con correlazione maggiore:");
        System.out.println();
        for(int i = x.size() - 1; i > x.size() - 10; i--){
            System.out.println(x.get(i).toString());
        }
        System.out.println();
        System.out.println();
        
        DBCollection table2 = db.getCollection("correlatedroutes");
        //salvo in un'altra collection dello stesso db i documenti corrispondenti agli oggetti CorrelatedRoutes
        for(int i = 0; i < x.size(); i++){
            BasicDBObject document = new BasicDBObject();
            document.put("first_origin", x.get(i).getOrigin1());
            document.put("first_destination", x.get(i).getDestination1());
            document.put("second_origin", x.get(i).getOrigin2());
            document.put("second_destination", x.get(i).getDestination2());
            document.put("Pearson_rank", x.get(i).getPearsonRank());
            document.put("Kendall_rank", x.get(i).getKendallRank());
            document.put("Spearman_rank", x.get(i).getSpearmanRank());
            document.put("time", x.get(i).getTime());
            table2.insert(document);
        }
        
    }
    
}
