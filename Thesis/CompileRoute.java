/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tesi3.tesi3;

import java.io.IOException;
import static java.lang.Math.round;
import java.util.List;

/**
 *
 * @author ennio
 */
public class CompileRoute {
    private final String BaseURL="C:\\Users\\ennio\\Documents\\NetBeansProjects\\Tesi1\\Tesi1\\src\\main\\java\\com\\tesi3\\tesi3\\UrlRequestList\\";
    
    private final String s1 = BaseURL + "Block1\\B1InBlock.txt";
    private final String s2 = BaseURL + "Block1\\B1Exit1.txt";
    private final String s3 = BaseURL + "Block1\\B1Exit2.txt";
    private final String s4 = BaseURL + "Block1\\B1Entrance1.txt";
    private final String s5 = BaseURL + "Block1\\B1Entrance2.txt";
    private final String s6 = BaseURL + "Block2\\B2InBlock.txt";
    private final String s7 = BaseURL + "Block2\\B2Exit1.txt";
    private final String s8 = BaseURL + "Block2\\B2Exit2.txt";
    private final String s9 = BaseURL + "Block2\\B2Exit3.txt";  
    private final String s10 = BaseURL + "Block2\\B2Entrance1.txt";
    private final String s11 = BaseURL + "Block2\\B2Entrance2.txt";
    private final String s12 = BaseURL + "Block2\\B2Entrance3.txt";
    private final String s13 = BaseURL + "Block3\\B3InBlock.txt";
    private final String s14 = BaseURL + "Block3\\B3Exit1.txt";
    private final String s15 = BaseURL + "Block3\\B3Exit2.txt";
    private final String s16 = BaseURL + "Block3\\B3Exit3.txt";
    private final String s17 = BaseURL + "Block3\\B3Exit4.txt"; 
    private final String s18 = BaseURL + "Block3\\B3Entrance1.txt";
    private final String s19 = BaseURL + "Block3\\B3Entrance2.txt";
    private final String s20 = BaseURL + "Block3\\B3Entrance3.txt";
    private final String s21 = BaseURL + "Block3\\B3Entrance4.txt";
    private final String s22 = BaseURL + "Block4\\B4InBlock.txt";
    private final String s23 = BaseURL + "Block4\\B4Exit1.txt";
    private final String s24 = BaseURL + "Block4\\B4Exit2.txt"; 
    private final String s25 = BaseURL + "Block4\\B4Entrance1.txt";
    private final String s26 = BaseURL + "Block4\\B4Entrance2.txt";
    
    private final String e1 = BaseURL + "Block1\\B1External1.txt";
    private final String e2 = BaseURL + "Block1\\B1External2.txt";
    private final String e3 = BaseURL + "Block2\\B2External1.txt";
    private final String e4 = BaseURL + "Block2\\B2External2.txt";
    private final String e5 = BaseURL + "Block2\\B2External3.txt";
    private final String e6 = BaseURL + "Block3\\B3External1.txt";
    private final String e7 = BaseURL + "Block3\\B3External2.txt";
    private final String e8 = BaseURL + "Block3\\B3External3.txt";
    private final String e9 = BaseURL + "Block3\\B3External4.txt";
    private final String e10 = BaseURL + "Block3\\B3External5.txt";
    private final String e11 = BaseURL + "Block4\\B4External1.txt";
    private final String e12 = BaseURL + "Block4\\B4External2.txt";
    
    private UrlOperations op;    
    private OriginDestDuration odd;

    private List<OriginDestDuration> b1InBlock;
    private List<OriginDestDuration> b1Exit1;
    private List<OriginDestDuration> b1Exit2;
    private List<OriginDestDuration> b1Entrance1;
    private List<OriginDestDuration> b1Entrance2;
    private List<OriginDestDuration> b2InBlock;
    private List<OriginDestDuration> b2Exit1;
    private List<OriginDestDuration> b2Exit2;
    private List<OriginDestDuration> b2Exit3;
    private List<OriginDestDuration> b2Entrance1;
    private List<OriginDestDuration> b2Entrance2;
    private List<OriginDestDuration> b2Entrance3;
    private List<OriginDestDuration> b3InBlock;
    private List<OriginDestDuration> b3Exit1;
    private List<OriginDestDuration> b3Exit2;
    private List<OriginDestDuration> b3Entrance1;
    private List<OriginDestDuration> b3Entrance2;
        
    private List<OriginDestDuration> b1External1;
    private List<OriginDestDuration> b1External2;
    private List<OriginDestDuration> b2External1;
    private List<OriginDestDuration> b2External2;
    private List<OriginDestDuration> b2External3;
    private List<OriginDestDuration> b3External1;
    private List<OriginDestDuration> b3External2;
    private List<OriginDestDuration> b3External3;

    public CompileRoute(UrlOperations op, OriginDestDuration odd, List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2, 
            List<OriginDestDuration> b1Entrance1, List<OriginDestDuration> b1Entrance2, List<OriginDestDuration> b2InBlock, List<OriginDestDuration> b2Exit1, 
            List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b2Entrance1, List<OriginDestDuration> b2Entrance2, 
            List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b3InBlock, List<OriginDestDuration> b3Exit1, List<OriginDestDuration> b3Exit2, 
            List<OriginDestDuration> b3Entrance1, List<OriginDestDuration> b3Entrance2, List<OriginDestDuration> b1External1, List<OriginDestDuration> b1External2, 
            List<OriginDestDuration> b2External1, List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b3External1, 
            List<OriginDestDuration> b3External2, List<OriginDestDuration> b3External3) {
        this.op = op;
        this.odd = odd;
        this.b1InBlock = b1InBlock;
        this.b1Exit1 = b1Exit1;
        this.b1Exit2 = b1Exit2;
        this.b1Entrance1 = b1Entrance1;
        this.b1Entrance2 = b1Entrance2;
        this.b2InBlock = b2InBlock;
        this.b2Exit1 = b2Exit1;
        this.b2Exit2 = b2Exit2;
        this.b2Exit3 = b2Exit3;
        this.b2Entrance1 = b2Entrance1;
        this.b2Entrance2 = b2Entrance2;
        this.b2Entrance3 = b2Entrance3;
        this.b3InBlock = b3InBlock;
        this.b3Exit1 = b3Exit1;
        this.b3Exit2 = b3Exit2;
        this.b3Entrance1 = b3Entrance1;
        this.b3Entrance2 = b3Entrance2;
        this.b1External1 = b1External1;
        this.b1External2 = b1External2;
        this.b2External1 = b2External1;
        this.b2External2 = b2External2;
        this.b2External3 = b2External3;
        this.b3External1 = b3External1;
        this.b3External2 = b3External2;
        this.b3External3 = b3External3;
    }

    public UrlOperations getOp() {
        return op;
    }

    public OriginDestDuration getOdd() {
        return odd;
    }

    public List<OriginDestDuration> getB1InBlock() {
        return b1InBlock;
    }

    public List<OriginDestDuration> getB1Exit1() {
        return b1Exit1;
    }

    public List<OriginDestDuration> getB1Exit2() {
        return b1Exit2;
    }

    public List<OriginDestDuration> getB1Entrance1() {
        return b1Entrance1;
    }

    public List<OriginDestDuration> getB1Entrance2() {
        return b1Entrance2;
    }

    public List<OriginDestDuration> getB2InBlock() {
        return b2InBlock;
    }

    public List<OriginDestDuration> getB2Exit1() {
        return b2Exit1;
    }

    public List<OriginDestDuration> getB2Exit2() {
        return b2Exit2;
    }

    public List<OriginDestDuration> getB2Exit3() {
        return b2Exit3;
    }

    public List<OriginDestDuration> getB2Entrance1() {
        return b2Entrance1;
    }

    public List<OriginDestDuration> getB2Entrance2() {
        return b2Entrance2;
    }

    public List<OriginDestDuration> getB2Entrance3() {
        return b2Entrance3;
    }

    public List<OriginDestDuration> getB3InBlock() {
        return b3InBlock;
    }

    public List<OriginDestDuration> getB3Exit1() {
        return b3Exit1;
    }

    public List<OriginDestDuration> getB3Exit2() {
        return b3Exit2;
    }

    public List<OriginDestDuration> getB3Entrance1() {
        return b3Entrance1;
    }

    public List<OriginDestDuration> getB3Entrance2() {
        return b3Entrance2;
    }

    public List<OriginDestDuration> getB1External1() {
        return b1External1;
    }

    public List<OriginDestDuration> getB1External2() {
        return b1External2;
    }

    public List<OriginDestDuration> getB2External1() {
        return b2External1;
    }

    public List<OriginDestDuration> getB2External2() {
        return b2External2;
    }

    public List<OriginDestDuration> getB2External3() {
        return b2External3;
    }

    public List<OriginDestDuration> getB3External1() {
        return b3External1;
    }

    public List<OriginDestDuration> getB3External2() {
        return b3External2;
    }

    public List<OriginDestDuration> getB3External3() {
        return b3External3;
    }
    //Metodo più importante di questa classe, che crea tutti gli oggetti OriginDestDuration, mettendole in delle liste di supporto, organizzate allo stesso modo 
	//di come lo sono i file di testo contenenti gli url delle richieste a Google. Queste liste saranno poi utili per costruire gli oggetti corrispondenti ai percorsi
	//che mancano a causa della suddivisione in blocchi della griglia
    public void fillSupportList(UrlOperations op, OriginDestDuration odd, List<OriginDestDuration> b1InBlock, List<OriginDestDuration> b1Exit1, List<OriginDestDuration> b1Exit2,
        List<OriginDestDuration> b1Entrance1, List<OriginDestDuration> b1Entrance2, List<OriginDestDuration> b2InBlock, List<OriginDestDuration> b2Exit1, 
        List<OriginDestDuration> b2Exit2, List<OriginDestDuration> b2Exit3, List<OriginDestDuration> b2Entrance1, List<OriginDestDuration> b2Entrance2,
        List<OriginDestDuration> b2Entrance3, List<OriginDestDuration> b3InBlock, List<OriginDestDuration> b3Exit1, List<OriginDestDuration> b3Exit2,
        List<OriginDestDuration> b3Entrance1, List<OriginDestDuration> b3Entrance2, List<OriginDestDuration> b1External1, List<OriginDestDuration> b1External2,
        List<OriginDestDuration> b2External1, List<OriginDestDuration> b2External2, List<OriginDestDuration> b2External3, List<OriginDestDuration> b3External1,
        List<OriginDestDuration> b3External2, List<OriginDestDuration> b3External3) throws IOException{
                
            op.createODDObjects(s1, b1InBlock);
            op.createODDObjects(s2, b1Exit1);
            op.createODDObjects(s3, b1Exit2);
            op.createODDObjects(s4, b1Entrance1);
            op.createODDObjects(s5, b1Entrance2);
            op.createODDObjects(s6, b2InBlock);
            op.createODDObjects(s7, b2Exit1);
            op.createODDObjects(s8, b2Exit2);
            op.createODDObjects(s9, b2Exit3);
            op.createODDObjects(s10, b2Entrance1);
            op.createODDObjects(s11, b2Entrance2);
            op.createODDObjects(s12, b2Entrance3);
            op.createODDObjects(s13, b3InBlock);
            op.createODDObjects(s14, b3Exit1);
            op.createODDObjects(s15, b3Exit2);
            op.createODDObjects(s18, b3Entrance1);
            op.createODDObjects(s19, b3Entrance2);
            
            op.createODDObjects(e1, b1External1);
            op.createODDObjects(e2, b1External2);
            op.createODDObjects(e3, b2External1);
            op.createODDObjects(e4, b2External2);
            op.createODDObjects(e5, b2External3);
            op.createODDObjects(e6, b3External1);
            op.createODDObjects(e7, b3External2);
            op.createODDObjects(e8, b3External3);        
    }
    //metodo utile per riempire costruire la stringa che va nel campo duration_text (in minuti) partendo dall'intero contenuto in duration (durata in secondi)
    protected String computeDurText(int dv){
        double a;
        int b;
        String c;
        a = (double) dv / 60;
        b = (int) round(a);
        c = b + " min";
        
        return c;
    }
    //i seguenti due sono i metodi che servono a prendere il percorso di durata minore paragonando 2 o 4 alternative:
    protected int takeLower(int a, int b){
        int res = 0;
        if(a < b) {
            res = a;
        } else if (b < a){
            res = b;
        } else if(a == b){
            res = a;
        }
        return res;
    }
    
    protected int takeLower(int a, int b, int c, int d){
        int res1 = 0, res2 = 0, res = 0;
        if(a < b) {
            res1 = a;
        } else if (b < a){
            res1 = b;
        } else if(a == b){
            res1 = a;
        }
        
        if(c < d) {
            res2 = c;
        } else if (d < c){
            res2 = d;
        } else if(c == d){
            res2 = c;
        }
        
        if(res1 < res2) {
            res = res1;
        } else if (res2 < res1){
            res = res2;
        } else if(res1 == res2){
            res = res1;
        }
        
        return res;
    }
}