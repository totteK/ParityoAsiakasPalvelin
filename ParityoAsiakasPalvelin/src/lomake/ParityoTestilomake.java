/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomake;
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