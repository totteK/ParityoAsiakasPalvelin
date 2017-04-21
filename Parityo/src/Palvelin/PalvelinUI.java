/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Palvelin;

import Olioluokka.ParityoKayttajatiedot;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author tottek
 */
public class PalvelinUI {
    
    public static void main(String[] args) throws SQLException{
        PalvelinUI ui = new PalvelinUI();
        System.out.println("Valitse toiminto:");
        System.out.println("1 = Lisää hakukohde");
        System.out.println("2 = Lisää käyttäjä");
        System.out.println("3 = Näytä kaikki käyttäjät");
        System.out.println("4 = Näytä kaikki hakukohteet");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if(i == 1){        
            ui.hakukohde();
        }
        if(i == 2){
            ui.lisaakaytaja();
        }
        if(i == 3){
            ui.naytaKayttajat();
        }
        if(i == 4){
            ui.naytaKohteet();
        }
    }
    
    public void hakukohde() throws SQLException{
        Scanner input = new Scanner(System.in);
        String kaupunki, hakukohde, etaisyys, muut;
        int pmaara, atilanne;
        System.out.println("Syötä kaupunki: ");
        kaupunki = input.next();
        System.out.println("Syötä hakukohde: ");
        hakukohde = input.next();
        System.out.println("Syötä hakupaikkojen määrä: ");
        pmaara = input.nextInt();
        System.out.println("Syötä etäisyys: ");
        etaisyys = input.next();
        System.out.println("Syötä asuntotilanne (0 = ei asuntoa, 1 = asunto): " );
        atilanne = input.nextInt();
        System.out.println("Syötä muut tiedot (vapaamuotoinen): ");
        muut = input.next();
        
        KayttajaTietokanta ktDB = new KayttajaTietokanta();
        ktDB.lisaaHakukohde(kaupunki, hakukohde, pmaara, etaisyys, atilanne, muut);
    }
    
    public void lisaakaytaja() throws SQLException{
        Scanner input = new Scanner(System.in);
        int opiskelijanumero;
        String kayttajanimi, salasana;
        System.out.println("Syötä opiskelija numero");
        opiskelijanumero = input.nextInt();
        System.out.println("Syötä kayttajanimi: ");
        kayttajanimi = input.next();
        System.out.println("Syötä salasana: ");
        salasana = input.next();
        
        KayttajaTietokanta ktDB = new KayttajaTietokanta();
     
        ParityoKayttajatiedot kayttie = new ParityoKayttajatiedot();
        kayttie.setOpiskelijanumero(opiskelijanumero);
        kayttie.setKayttajanimi(kayttajanimi);
        kayttie.setSalasana(salasana);
        
        ktDB.lisaaKayttaja(kayttie);
        
    }
    
    public void naytaKayttajat() throws SQLException{
        KayttajaTietokanta ktDB = new KayttajaTietokanta();
        ktDB.naytaKayttajat();
    }
    
    public void naytaKohteet() throws SQLException{
        KayttajaTietokanta ktDB = new KayttajaTietokanta();
        ktDB.naytaKohteet();
    }
    
}
