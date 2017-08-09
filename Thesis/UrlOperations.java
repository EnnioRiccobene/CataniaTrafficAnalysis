/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author ennio
 */
public class UrlOperations {
    String sourceFileName;
    String destinationFileName;
    List<OriginDestDuration> pl;
    OriginDestDuration od;

    public UrlOperations(String sourceFileName, String destinationFileName) {
        this.sourceFileName = sourceFileName;
        this.destinationFileName = destinationFileName;
    }

    public UrlOperations() {
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getDestinationFileName() {
        return destinationFileName;
    }

    public void setDestinationFileName(String destinationFileName) {
        this.destinationFileName = destinationFileName;
    }

    public List<OriginDestDuration> getPl() {
        return pl;
    }

    public OriginDestDuration getOd() {
        return od;
    }
    
    //i primi tre metodi sono stati usati per "pulire" il contenuto dei file di testo contenenti gli URL delle risposte di Google, allo stato attuale del progetto questo
    //lavoro è già stato fatto
    public void copyFile(String sourceFileName,String destinationFileName) { 

      BufferedReader br = null;
      PrintWriter pw = null;
      int count = 1; 

      try {
          br = new BufferedReader(new FileReader( sourceFileName ));
    	  pw =  new PrintWriter(new FileWriter( destinationFileName ));

          String line;
          while ((line = br.readLine()) != null) {
              if ((count % 2) == 0) {
			pw.println(line);
		} 
              count++;
          }

          br.close();
          pw.close();
      }catch (Exception e) {
	  e.printStackTrace();
      }
    
    }
    
    public void GetUrl(String sourceFileName,String destinationFileName) {
        
        BufferedReader br = null;
        PrintWriter pw = null;
        
        try {
          br = new BufferedReader(new FileReader( sourceFileName ));
    	  pw =  new PrintWriter(new FileWriter( destinationFileName ));

          String line;
          while ((line = br.readLine()) != null) {
              String[] parts = line.split(" ");
              String part3 = parts[2];
              pw.println(part3);
             
          }

          br.close();
          pw.close();
      }catch (Exception e) {
	  e.printStackTrace();
      }
        
    }
    
    public static String[] SaveUrl(String sourceFileName){
        BufferedReader br = null;  
        String[] line = null;
        try {
          br = new BufferedReader(new FileReader(sourceFileName)); 
                    
          int count = 0;
          while ((line[count] = br.readLine()) != null) {
              //UrlRequest[count] = line;
              count++;
             
          }

          br.close();
          
      }catch (Exception e) {
	  e.printStackTrace();
      }
        
        return line;
    }    
    //metodo che restituisce una lista contenente tutti gli url per le richieste a Google, partendo dalle informazioni scritte in un file di testo
    public static  ArrayList<String> ReadUrl(String sourceFileName){    
        File fileDir = new File(sourceFileName);
            BufferedReader br = null;  
            ArrayList<String> line = new ArrayList<String>();
            try {
                br = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(fileDir), "UTF8"));
                    
                int count = 0;
                String str;
                while ((str = br.readLine()) != null) {
                    line.add(str + "&departure_time=" + new DateTime().getMillis());//si prende la parte statica dell'url e gli si aggiunge di volta in volta il departure time espresso in millisecondi. Serve ad avere il dato nel traffico in tempo reale
                    count++;
             
                }

                br.close();
          
            }catch (Exception e) {
            e.printStackTrace();
        }
        
        return line;
    }
    //metodo più importante della classe con la quale, partendo dagli URL di richiesta a Google, si fa il parsing e si costruiscono gli oggetti OriginDestDuration
	//corrispondenti al singolo percorso
    public void createODDObjects(String sourceFileName, List<OriginDestDuration> pl) throws MalformedURLException, IOException {
        ArrayList<String> UrlRequest = null;
        
        UrlRequest = ReadUrl(sourceFileName);
        
        for(int k = 0; k < UrlRequest.size(); k++){
            URL url = new URL(UrlRequest.get(k));
            InputStreamReader reader = new InputStreamReader(url.openStream());
            SRD sr = new Gson().fromJson(reader, SRD.class);
            
            if(sr.getRows().get(0).getElements().get(0).getDistance().getValue() != 0) {
                OriginDestDuration od = new OriginDestDuration();

                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());

                od.setDestination(sr.getDestination_addresses().get(0));
                od.setOrigin(sr.getOrigin_addresses().get(0));
                od.setDur_text(sr.getRows().get(0).getElements().get(0).getDuration_in_traffic().getText());
                od.setDur_value(sr.getRows().get(0).getElements().get(0).getDuration_in_traffic().getValue());   
                od.setHour(currentTimestamp);
                //con duration-value diviso 60 ottengo il valore double di duration-text                              
                pl.add(od);
            }                                         
        }        
    }   
}