package MainFunctions;

import Olioluokka.ParityoKayttajatiedot;
import java.net.*;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Olioluokka.ParityoTestilomake;
import java.io.DataInputStream;
import java.io.DataOutputStream;
/**
 *
 * @author TotteK & Juusoha
 */
public class ParityoAsiakas {
     public boolean asiakas_palvelin_yhteys(ParityoTestilomake lomake, ParityoKayttajatiedot kt){
        /*
        if (args.length < 1) {
            System.err.println("K�ytt�: java AsiakasEsimerkki " +
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
        boolean stat = a.keskustele(osoite, port, lomake, kt);
        if(!stat){
            return false;
        }

        try {
            Thread.sleep(2000);
        } catch (Exception e) {}
        
    return true;


    } // main()


    // simppeli keskustelu, palauttaa toden jos onnistui
    public boolean keskustele(String osoite, int portti, ParityoTestilomake lomake, ParityoKayttajatiedot kt) {

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
            
            DataOutputStream dataoutput = new DataOutputStream(s.getOutputStream());
            DataInputStream datainput = new DataInputStream(s.getInputStream());

            // l�het� viesti
            os.writeObject(kt);
            
            
            //Boolean problemsh???
            System.out.println("Selvitetään oliko kirjautuminen OK");
            boolean vastaus = datainput.readBoolean();
            System.out.println("Kirjautumisen tulos: "+ vastaus);
            //OTA vastaan viesti
            try{
          
                if(vastaus == false){
                    return vastaus;
                }
                else{
                    System.out.print("Yhteys onnistui");
                }
            }
            catch(Exception e){
                System.out.print(e);
                return false;
            }

            os.writeObject(lomake);

            // vastaanota viesti
            ParityoTestilomake lomake1 = (ParityoTestilomake)(is.readObject());
                       
            // suljetaan yhteys
            s.close();

        // poikkeusten k�sittely
        } catch (Exception e) {
            System.err.println(e);
            if (s != null)
                try {
                    s.close();  // suljetaan varuilta viel� t��ll�kin
                } catch (Exception e2) {}

            return false;
        } // catch

        return true;

    }   // keskustele()
    
    public void lomakkeentaytto(){
        
    }

}