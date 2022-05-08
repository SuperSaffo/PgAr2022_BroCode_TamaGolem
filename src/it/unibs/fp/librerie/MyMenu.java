package it.unibs.fp.librerie;

import it.unibs.fp.mylib2.InputDati;

public class MyMenu {
    final private static String CORNICE = "--------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

    private String titolo;
    private String [] voci;

    public MyMenu (String titolo, String [] voci)
    {
        this.titolo = titolo;
        this.voci = voci;
    }

    public int scegli ()
    {
        stampaMenu();
        return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
    }

    public void stampaMenu ()
    {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i=0; i<voci.length; i++)
        {
            System.out.println( (i+1) + "\t" + voci[i]);
        }
        System.out.println();
        System.out.println(VOCE_USCITA);
        System.out.println();
    }

    public int scegliNoZero ()
    {
        stampaMenuNoZero();
        return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 1, voci.length);
    }


    public void stampaMenuNoZero() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);
        for (int i=0; i<voci.length; i++)
        {
            System.out.println( (i+1) + "\t" + voci[i]);
        }
        System.out.println();
    }

}