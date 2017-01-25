/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parityoasiakas;

import lomake.ParityoTestilomake;
/**
 *
 * @author Totte
 */
public class ParityoMain {
    //GUI:n käynnistys
        /*Login ikkuna(tiedot, yhteys, tietojen lähetys)
         *Lomake auki (Yhteys ja autentikointi suoritettu)
         *Tietojen syöttö GUI:ssa ParityoTestilomakkeeseen
         *Lomake lähetetään palvelimelle(odotetaan vastaus lomakkeen saapumisesta)
    */
    
    public static void main(String[] args){
        ParityoGUI gui = new ParityoGUI(); //Luo olion GUI-luokasta
        ParityoTestilomake lomake = gui.txtGUI(); //Käynnistää GUI:n
        ParityoAsiakas asiakas = new ParityoAsiakas();
        asiakas.asiakas_palvelin_yhteys(lomake);
    }
}
