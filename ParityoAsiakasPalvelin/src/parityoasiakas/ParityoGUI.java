/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parityoasiakas;

import java.util.Scanner;
import lomake.ParityoTestilomake;
/**
 *
 * @author Totte
 */
public class ParityoGUI {
     
     public ParityoTestilomake txtGUI(){
        ParityoTestilomake lomake = new ParityoTestilomake();
        Scanner input = new Scanner(System.in);
        System.out.println("Kirjoita opiskelijanumerosi");
        lomake.setOpiskelijanumero(input.nextInt());
        System.out.println("Kirjoita etunimesi");
        lomake.setEtunimi(input.next());
        System.out.println("Kirjoita sukunimesi");
        lomake.setSukunimi(input.next());
        
        return lomake;
     }
    
}


