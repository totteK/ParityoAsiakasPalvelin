/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parityoasiakas;

import java.net.*;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lomake.ParityoTestilomake;
/**
 *
 * @author Totte
 */
public class ParityoAsiakas {
     public void asiakas_palvelin_yhteys(ParityoTestilomake lomake){
        /*
        if (args.length < 1) {
            System.err.println("Käyttö: java AsiakasEsimerkki " +
                               "palvelinosoite [portti]");
            return;
        }

        int portti = 1234;
        if (args.length > 1) {
            portti = Integer.valueOf(args[1]);
        }
        */
        int port; String osoite = "localhost";
        Scanner scanner = new Scanner(System.in);
        System.out.println("portti:");
        port = scanner.nextInt();
        System.out.println(port);

        ParityoAsiakas a = new ParityoAsiakas();

        // yksi viesti suuntaansa
        boolean stat = a.keskustele(osoite, port, lomake);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {}

    


    } // main()


    // simppeli keskustelu, palauttaa toden jos onnistui
    public boolean keskustele(String osoite, int portti, ParityoTestilomake lomake) {

        Socket s = null;

        // yhteydenotto
        try {
            s = new Socket(osoite, portti);     // yhteydenotto
            System.out.println("Yhteys onnistui");
        } catch (Exception e) {
            // yhteys ei varmaankaan onnistunut
            System.err.println(e);
            return false;
        }

        try {

            // luodaan keskustelukanavat
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            //PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            //BufferedReader in =
                //new BufferedReader(new InputStreamReader(s.getInputStream()));

            // lähetä viesti
            os.writeObject(lomake);

            // vastaanota viesti
            ParityoTestilomake lomake1 = (ParityoTestilomake)(is.readObject());
            
            
            int opiskelijanumero = lomake1.getOpiskelijanumero();
            String etunimi = lomake1.getEtunimi();
            String sukunimi = lomake1.getSukunimi();
            System.out.println(opiskelijanumero);
            System.out.println(etunimi);
            System.out.println(sukunimi);

            // suljetaan yhteys
            s.close();

        // poikkeusten käsittely
        } catch (Exception e) {
            System.err.println(e);
            if (s != null)
                try {
                    s.close();  // suljetaan varuilta vielä täälläkin
                } catch (Exception e2) {}

            return false;
        } // catch

        return true;

    }   // keskustele()
    
    public void lomakkeentaytto(){
        
    }

}