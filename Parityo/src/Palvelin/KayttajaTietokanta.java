/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Palvelin;

import Olioluokka.ParityoHashMapKohteet;
import Olioluokka.ParityoKayttajatiedot;
import Olioluokka.ParityoKohdelomake;
import Olioluokka.ParityoTestilomake;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Totte
 */
public class KayttajaTietokanta {
    
    public Connection yhdista() throws SQLException{
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            //String dbURL = "jdbc:sqlite:C:\\Users\\Totte\\Documents\\NetBeansProjects\\Parityo\\src\\Palvelin\\kayttajatietokanta.db";
            //koulun kone
            String dbURL = "jdbc:sqlite:C:\\Users\\Totte\\Documents\\NetBeansProjects\\Parityo\\src\\Palvelin\\kayttajatietokanta.db";
            c = DriverManager.getConnection(dbURL);
            if (c != null) {
                System.out.println("Connected to the database");
            }
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return c;
    }
    
    public void lisaaKayttaja(ParityoKayttajatiedot kt) throws SQLException{
       
        int op = kt.getOpiskelijanumero();
        String kn = kt.getKayttajanimi();
        String ss = kt.getSalasana();
        
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String sql = ("INSERT INTO kayttajat (kayttajanimi, salasana, opnro) " +
                "VALUES('"+kn+"', '"+ss+ "', "+op+");");
        s.executeUpdate(sql);
        s.close();
        //c.commit(); //autocommit päällä
        c.close();
    }
    public void lisaaLomake(ParityoTestilomake lom)throws SQLException{
        
        int op = lom.getOpiskelijanumero();
        String et = lom.getEtunimi();
        String su = lom.getSukunimi();
        
        Connection c = yhdista();
        Statement s = c.createStatement();
        String sql = ("INSERT INTO lomake (etunimi, sukunimi, opnro) " +
                "VALUES('"+et+"', '"+su+"', "+op+");");
        System.out.println(sql);
        s.executeUpdate(sql);
        s.close();
        c.close();
        
    }
    
    public boolean kayttajanTarkistus(ParityoKayttajatiedot kt)throws SQLException{
        
        String kn = kt.getKayttajanimi();
        String ss = kt.getSalasana();
        
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        boolean vastaus;
        ResultSet rs = s.executeQuery("SELECT kayttajanimi, salasana FROM kayttajat WHERE kayttajanimi = '" +kn+ "' AND salasana = '" +ss+ "';");
        

        
        if(rs.next() == false) return false;
        
        String db_kn = rs.getString("kayttajanimi");
        String db_ss = rs.getString("salasana");
        System.out.println(db_kn);
        System.out.println(db_ss);
        boolean knb = kn.equals(db_kn);
        boolean ssb = ss.equals(db_ss);
        if(knb && ssb) vastaus = true;
        else vastaus = false;
        
        s.close();
        c.close();
        return vastaus;
    }
    
    public int vapaatPaikat(String hakukohde) throws SQLException{
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String haepaikat = "SELECT pmaara FROM hakukohteet WHERE hakukohde = '"+hakukohde+"';";
        ResultSet rs = s.executeQuery(haepaikat);
        int pmaara = rs.getInt("pmaara");
        System.out.println(pmaara);
        
        String haevaraukset = "SELECT COUNT(hakukohde) AS rowcount FROM varaukset WHERE hakukohde = '"+hakukohde+"'";
        rs = s.executeQuery(haevaraukset);
        rs.next();
        int count = rs.getInt("rowcount");
        System.out.println(count);
        
        int vapaat = pmaara-count;
        return vapaat;
    }
    
    public void vapaatHakukohteetLomakkeeseen() throws SQLException{
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String haepaikat = "SELECT hakukohde FROM hakukohteet;";
        ResultSet rs = s.executeQuery(haepaikat);
        rs.next();
        
        // tarkasta tehdäänkö aina uusi puhdas hashmap, vai vanhan päälle (jos tulee erroria tms.)
        ParityoHashMapKohteet phmk = new ParityoHashMapKohteet();
        
        
        while(rs.isAfterLast()){
            String hakukohde = rs.getString("hakukohde");
            int vapaatpaikat = vapaatPaikat(hakukohde);
            ParityoKohdelomake pkl = new ParityoKohdelomake(rs.getString("kaupunki"),
                    rs.getString("hakukohde"), rs.getInt("pmaara"), rs.getString("etaisyys"), 
                    rs.getBoolean("atilanne"), rs.getString("muut"), vapaatpaikat);
            
            phmk.tallennaKohdeHashMap(hakukohde, pkl);
            rs.next();
        }
    }
    
    
    
    public void lisaaHakukohde(String kaupunki, String hakukohde, int pmaara, String etaisyys, int atilanne, String muut) throws SQLException{
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String sql = "INSERT INTO hakukohteet (kaupunki, hakukohde, pmaara, etaisyys, atilanne, muut) "
                + "VALUES('"+kaupunki+"', '"+hakukohde+"', "+pmaara+", '"+etaisyys+"', "+atilanne+", '"+muut+"' );";
        System.out.println(sql);
        s.executeUpdate(sql);
        String haku = "SELECT * FROM hakukohteet WHERE kaupunki = '"+kaupunki+"';";
        ResultSet rs = s.executeQuery(haku);
        System.out.println("Kaupunki: " + rs.getString("kaupunki"));
        System.out.println("Hakukohde: " +rs.getString("hakukohde"));
        System.out.println("Hakupaikkojen lukumäärä: " +rs.getInt("pmaara"));
        System.out.println("Etäisyys: " +rs.getString("etaisyys"));
        System.out.println("Asuntotilanne: " +rs.getBoolean("atilanne"));
        System.out.println("Muita tietoja: " +rs.getString("muut"));
        System.out.println("---------------------------------------------------------");
        s.close();
        c.close();
    }
    
    // TEE Olioluokka hakukohteiden tiedoille
    // naytaKohteet palauttaa taulukon kyseisiä olioita
    // lähetetään taulukko palvelimelta asiakkaalle
    // Olioiden näyttö GUI:ssa käyttäjälle
    // varauksen käsittely olion kautta GUI:ssa
    
    //Naytetaan kayttaja-lista Palvelin UI:n puolella
    public void naytaKayttajat() throws SQLException{
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String sql = "SELECT * FROM kayttajat;";
        ResultSet rs = s.executeQuery(sql);
        rs.next();
        while(!rs.isAfterLast()){
            System.out.println("Opiskelijanumero: " + rs.getInt("opnro"));
            System.out.println("Nimi: " + rs.getString("kayttajanimi"));
            System.out.println("---------------------------------------------------------");
            rs.next();
        }
    }
    //Mahdollisten hakukohteiden nayttaminen Palvelin UI:n puolella
    public void naytaKohteet() throws SQLException{
        Connection c = yhdista();
        Statement s = c.createStatement();
        
        String sql = "SELECT * FROM hakukohteet;";
        ResultSet rs = s.executeQuery(sql);
        rs.next();
        while(!rs.isAfterLast()){
            System.out.println("Kaupunki: " + rs.getString("kaupunki"));
            System.out.println("Hakukohde: " +rs.getString("hakukohde"));
            System.out.println("Hakupaikkojen lukumäärä: " +rs.getInt("pmaara"));
            System.out.println("Etäisyys: " +rs.getString("etaisyys"));
            System.out.println("Asuntotilanne: " +rs.getBoolean("atilanne"));
            System.out.println("Muita tietoja: " +rs.getString("muut"));
            System.out.println("---------------------------------------------------------");
            rs.next();
        }
    }
    
    public static void main(String[] args) throws SQLException{
        
        KayttajaTietokanta k = new KayttajaTietokanta();
        /*
        ParityoTestilomake lom = new ParityoTestilomake();
        lom.setEtunimi("Ressikar");
        lom.setSukunimi("Mahtileka");
        lom.setOpiskelijanumero(696969);
        k.lisaaKayttaja();
        k.lisaaLomake(lom);
        */
        k.vapaatPaikat("Superasema");
    }
    
}
