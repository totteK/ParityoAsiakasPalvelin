package Olioluokka;
//Käyttäjän syöttämät tiedot GUI:n puolelta
import java.io.Serializable;


public class ParityoTestilomake implements Serializable{
    private int opiskelijanumero;
    private String etunimi;
    private String sukunimi;
    
    
    public void setOpiskelijanumero(int opiskelijanumero){
        this.opiskelijanumero = opiskelijanumero;
    }
    
    public void setEtunimi(String etunimi){
        this.etunimi = etunimi;
    }
     
     public void setSukunimi(String sukunimi){
        this.sukunimi = sukunimi;
    }
    
    public int getOpiskelijanumero(){
        return this.opiskelijanumero;
    }
    
    public String getEtunimi(){
        return this.etunimi;
    }
    
    public String getSukunimi(){
        return this.sukunimi;
    }
               
}