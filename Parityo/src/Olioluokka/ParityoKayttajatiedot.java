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
//Kayttajan luomiseen kaytettavat tiedot Palvelin UI:n puolelta
public class ParityoKayttajatiedot implements Serializable{
    
    private String kayttajanimi;
    private String salasana;
    private int opiskelijanumero;
    
    public void setKayttajanimi(String kayttajanimi){
        this.kayttajanimi = kayttajanimi;
    }
    
    public void setSalasana(String salasana){
        this.salasana = salasana;
    }
    
    public void setOpiskelijanumero(int opiskelijanumero){
        this.opiskelijanumero = opiskelijanumero;
    }
    
    public String getKayttajanimi(){
        return this.kayttajanimi;
    }
    
    public String getSalasana(){
        return this.salasana;
    }
    
    public int getOpiskelijanumero(){
        return this.opiskelijanumero;
    }
    
    
}
