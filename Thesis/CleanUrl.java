/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ennio
 */
public class CleanUrl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String pre = "C:\\Users\\ennio\\Documents\\NetBeansProjects\\Tesi1\\Tesi1\\src\\main\\java\\com\\tesi3\\tesi3\\UrlRequestList\\";//path comune ai file di testo
        List<String> s = new ArrayList<>();        
        //i vari file di testo:
        String s16 = pre + "Block3\\B3Entrance3.txt";
        String s17 = pre + "Block3\\B3Entrance4.txt";
        String s18 = pre + "Block3\\B3Exit1.txt";
        String s19 = pre + "Block3\\B3Exit2.txt";
        String s20 = pre + "Block3\\B3Exit3.txt";
        String s21 = pre + "Block3\\B3Exit4.txt";       
        String s22 = pre + "Block4\\B4InBlock.txt";
        String s23 = pre + "Block4\\B4Entrance1.txt";
        String s24 = pre + "Block4\\B4Entrance2.txt";
        String s25 = pre + "Block4\\B4Exit1.txt";
        String s26 = pre + "Block4\\B4Exit2.txt";    
        
        List<String> d = new ArrayList<>();        
        
        String d16 = pre + "Block3\\B3Entrance3_t.txt";
        String d17 = pre + "Block3\\B3Entrance4_t.txt";
        String d18 = pre + "Block3\\B3Exit1_t.txt";
        String d19 = pre + "Block3\\B3Exit2_t.txt";
        String d20 = pre + "Block3\\B3Exit3_t.txt";
        String d21 = pre + "Block3\\B3Exit4_t.txt";       
        String d22 = pre + "Block4\\B4InBlock_T.txt";
        String d23 = pre + "Block4\\B4Entrance1_t.txt";
        String d24 = pre + "Block4\\B4Entrance2_t.txt";
        String d25 = pre + "Block4\\B4Exit1_t.txt";
        String d26 = pre + "Block4\\B4Exit2_t.txt"; 
        
        s.add(s16);
        s.add(s17);
        s.add(s18);
        s.add(s19);
        s.add(s20);
        s.add(s21);
        s.add(s22);
        s.add(s23);
        s.add(s24);
        s.add(s25);
        s.add(s26);
        
        d.add(d16);
        d.add(d17);
        d.add(d18);
        d.add(d19);
        d.add(d20);
        d.add(d21);
        d.add(d22);
        d.add(d23);
        d.add(d24);
        d.add(d25);
        d.add(d26);
        
        UrlOperations op = new UrlOperations();
        //vengono puliti i file di testo al fine di avere in ognuno solo gli url delle richieste a Google
        for(int i = 0; i < 11; i++) {            
            op.copyFile(s.get(i), d.get(i));
            
            op.GetUrl(d.get(i), s.get(i));
        }
        
    }
    
}
