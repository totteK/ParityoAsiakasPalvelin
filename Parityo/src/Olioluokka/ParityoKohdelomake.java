/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Olioluokka;

import java.io.Serializable;
/**
 *
 * @author Totte
 */
public class ParityoKohdelomake implements Serializable {
   
    private String kaupunki;
    private String hakukohde;
    private int pmaara;
    private String etaisyys;
    private boolean atilanne;
    private String muut;
    private int vapaat;
    
    public ParityoKohdelomake(String kaupunki, String hakukohde, int pmaara, String etaisyys, boolean atilanne, String muut, int vapaat){
        this.kaupunki = kaupunki;
        this.hakukohde = hakukohde;
        this.pmaara = pmaara;
        this.etaisyys = etaisyys;
        this.atilanne = atilanne;
        this.muut = muut;
        this.vapaat = vapaat;
    }
    
    
    public void setKaupunki(String kaupunki){
        this.kaupunki = kaupunki;
    }
    
    public void setHakukohde(String hakukohde){
        this.hakukohde = hakukohde;
    }
     
    public void setPaikkojenmaara(int pmaara){
        this.pmaara = pmaara;
    }
    
    public void setEtaisyys(String etaisyys){
        this.etaisyys = etaisyys;
    }
    
    public void setAsuntotilanne(boolean atilanne){
        this.atilanne = atilanne;
    }
    
    public void setMuuttiedot(String muut){
        this.muut = muut;
    }
    
    public void setVapaat(int vapaat){
        this.vapaat = vapaat;
    }
    
    
    public String getKaupunki(){
        return this.kaupunki;
    }
    
    public String getHakukohde(){
        return this.hakukohde;
    }
    
    public int getPaikkojenmaara(){
        return this.pmaara;
    }
    
    public String getEtaisyys(){
        return this.etaisyys;
    }
    
    public boolean getAsuntotilanne(){
        return this.atilanne;
    }
    
    public String getMuuttiedot(){
        return this.muut;
    }
    
    public int getVapaat(){
        return this.vapaat;
    }
    
}
 