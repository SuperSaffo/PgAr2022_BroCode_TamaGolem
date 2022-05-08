package it.unibs.fp.tamagolem;

import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.librerie.InputDati;

import java.util.Random;

/**
 * Classe main
 * <p>Svolgimento della partita</p>
 */

public class Main {
    public static void main(String[] args) {

        Battaglia battaglia = new Battaglia();
        battaglia.setBattaglia();

        //SCELTA SE RICOMINCIARE UNA NUOVA PARTITA
        do{
            // TERMINAZIONE CICLO FIN QUANDO UNO DEI DUE GIOCATORI NON PERDE
            while(!battaglia.getGiocatore1().isSconfitto() && !battaglia.getGiocatore2().isSconfitto()) {
                battaglia.turnoConPerdente();
            }
            // STAMPA VITTORIA GIOCATORE 1
            if(battaglia.getGiocatore1().isSconfitto()) {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 1 HA VINTO");
                System.out.println("-------------------------");
            }
            // STAMPA VITTORIA GIOCATORE 2
            else {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 2 HA VINTO");
                System.out.println("-------------------------");
            }

            // STAMPA TABELLA EQUILIBRI
            battaglia.stampaEquilibrioBattaglia();

        }while(nuovaPartita());

    }

    /**
     * Metodo che chiede all'utente d'iniziare una nuova partita
     * @see InputDati#yesOrNo(String)
     * @return ritorna true se desidera continuare, false se vuole smettere di giocare
     */
    public static boolean nuovaPartita (){
        if (InputDati.yesOrNo("Nuova partita?")){
           return  true;
        }
        return  false;
    }
}

