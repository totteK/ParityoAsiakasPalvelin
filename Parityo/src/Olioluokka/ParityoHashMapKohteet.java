/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Olioluokka;

import Palvelin.KayttajaTietokanta;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Totte
 */
public class ParityoHashMapKohteet implements Serializable {
    
    HashMap<String,ParityoKohdelomake> kohteet = new HashMap<String, ParityoKohdelomake>();
    KayttajaTietokanta kt = new KayttajaTietokanta();
    
    public ParityoHashMapKohteet() throws SQLException {
        paivitaHashMap();
    }
    
    // tallennetaan parametri value kohtaan key hashmappiin
    public void tallennaKohdeHashMap(String key, ParityoKohdelomake value){
        kohteet.put(key, value);
    }
    
    public void paivitaHashMap() throws SQLException{
        kt.vapaatHakukohteetLomakkeeseen();
    }
    
    
    

}
