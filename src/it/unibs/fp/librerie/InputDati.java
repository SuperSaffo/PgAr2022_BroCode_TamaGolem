package it.unibs.fp.librerie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputDati {private static final String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
    private static final String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
    private static final String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere";
    private static final String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
    private static final String MESSAGGIO_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";
    private static final char RISPOSTA_SI = 'S';
    private static final char RISPOSTA_NO = 'N';
    private static final Scanner lettore = creaScanner();

    public InputDati() {
    }

    private static Scanner creaScanner() {
        Scanner creato = new Scanner(System.in);
        return creato;
    }


    public static char leggiChar(String messaggio) {
        boolean finito = false;
        char valoreLetto = 0;

        do {
            System.out.print(messaggio);
            String lettura = lettore.next();
            if (lettura.length() > 0) {
                valoreLetto = lettura.charAt(0);
                finito = true;
            } else {
                System.out.println("Attenzione: non hai inserito alcun carattere");
            }
        } while(!finito);

        return valoreLetto;
    }

    public static char leggiUpperChar(String messaggio, String ammissibili) {
        boolean finito = false;
        boolean var3 = false;

        char valoreLetto;
        do {
            valoreLetto = leggiChar(messaggio);
            valoreLetto = Character.toUpperCase(valoreLetto);
            if (ammissibili.indexOf(valoreLetto) != -1) {
                finito = true;
            } else {
                System.out.println("Attenzione: i caratteri ammissibili sono: " + ammissibili);
            }
        } while(!finito);

        return valoreLetto;
    }

    public static int leggiIntero(String messaggio) {
        boolean finito = false;
        int valoreLetto = 0;

        do {
            System.out.print(messaggio);

            try {
                valoreLetto = lettore.nextInt();
                finito = true;
            } catch (InputMismatchException var5) {
                System.out.println("Attenzione: il dato inserito non e' nel formato corretto");
                String var4 = lettore.next();
            }
        } while(!finito);

        return valoreLetto;
    }

    public static int leggiIntero(String messaggio, int minimo, int massimo) {
        boolean finito = false;
        boolean var4 = false;

        int valoreLetto;
        do {
            valoreLetto = leggiIntero(messaggio);
            if (valoreLetto >= minimo && valoreLetto <= massimo) {
                finito = true;
            } else if (valoreLetto < minimo) {
                System.out.println("Attenzione: e' richiesto un valore maggiore o uguale a " + minimo);
            } else {
                System.out.println("Attenzione: e' richiesto un valore minore o uguale a " + massimo);
            }
        } while(!finito);

        return valoreLetto;
    }



    public static boolean yesOrNo(String messaggio) {
        String mioMessaggio = messaggio + "(S/N)";
        String var10001 = String.valueOf('S');
        char valoreLetto = leggiUpperChar(mioMessaggio, var10001 + "N");
        return valoreLetto == 'S';
    }
}
