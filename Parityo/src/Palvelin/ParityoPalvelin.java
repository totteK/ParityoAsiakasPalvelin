package Palvelin;

import Olioluokka.ParityoKayttajatiedot;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import Olioluokka.ParityoTestilomake;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author TotteK & Juusoha
 */
public class ParityoPalvelin {
// PalvelinEsimerkki.java
// Kuuntelee porttia, yhteydest� otta vastaan kokonaislukuja, vastaa neli�im�ll�
// sulkee yhteyden jollei tule kokonaislukua


    // kuunteleva palvelint�pseli
    ServerSocket ss = null;

    public static void main(String[] args) {

        ParityoPalvelin p;

        if (args.length > 0)
            p = new ParityoPalvelin(Integer.valueOf(args[0]));
        else
            p = new ParityoPalvelin();

        p.kuuntele();

    } // main()


    // konstruktorit avaavat yhteyden kuuntelulle
    public ParityoPalvelin(int portti) {

        try {
            ss = new ServerSocket(portti);
            System.out.println("Kuunnellaan porttia " + portti);
        } catch (Exception e) {
            System.err.println(e);
            ss = null;
        }
    }

    public ParityoPalvelin() {
        this(49549);
    }


    // kuuntelu "s�ie" (s�ikeistet��n my�hemmin kurssilla)
    public void kuuntele() {

        try {
            while (true) {

                // odotetaan uutta yhteytt�
                Socket cs = ss.accept();

                // luodaan ja k�ynnistet�� uusi palvelijas�ie
                new Palvelija(cs).start();
            }
        } catch (Exception e) {
            System.err.println(e);
            ss = null;
        }
    }   // kuuntele()

    // palvelee yhden yhteyden
    public class Palvelija extends Thread {

        Socket asiakas;

        public Palvelija(Socket cs) {
            //super ajaa Thread luokan constructorin
            super();
            asiakas = cs;
        }

        public void run() {

            try {
                Thread.State state = Thread.currentThread().getState();
                System.out.println(Thread.currentThread().getName());
                System.out.println("state = " + state);

                System.out.println("Uusi yhteys: " + asiakas.getInetAddress() +
                                   ":" + asiakas.getPort());

                // virrat k�ytt�kelpoiseen muotoon
                ObjectOutputStream os = new ObjectOutputStream(asiakas.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(asiakas.getInputStream());
                
                DataOutputStream dataoutput = new DataOutputStream(asiakas.getOutputStream());
                DataInputStream datainput = new DataInputStream(asiakas.getInputStream());
                
                //Boolean arvolle oma input stream?????
                
                ParityoKayttajatiedot kt = (ParityoKayttajatiedot)is.readObject();
                KayttajaTietokanta db = new KayttajaTietokanta();
                boolean vastaus = db.kayttajanTarkistus(kt);
                System.out.println("kt: " +vastaus);
                dataoutput.writeBoolean(vastaus);
                System.out.println("boolean yritetty lähettää");
                if(!vastaus) return;
               
                
                
                ParityoTestilomake lomake = (ParityoTestilomake)is.readObject();
                System.out.println("lomake luetu'd");
                
               
              
                db.lisaaLomake(lomake);
                System.out.println("lome lisattu'd");
                /*int opiskelijanumero = lomake.getOpiskelijanumero();
                String etunimi = lomake.getEtunimi();
                String sukunimi = lomake.getSukunimi();
                System.out.println(opiskelijanumero);
                System.out.println(etunimi);
                System.out.println(sukunimi);
                        */
                        
                //Scanner input = new Scanner(is);
                
                // luetaan "pyynt�"
               

                // l�hetet��n vastaus
                os.writeObject(lomake);
                System.out.println("kirjotin streamiin lul");
                
                

                asiakas.close();
                System.out.println("lol");

            } catch (Exception e) {
                System.err.println(e);
            }

        }   // run()

    } // class Palvelija

} // class