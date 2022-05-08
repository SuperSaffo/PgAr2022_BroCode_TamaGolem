package it.unibs.fp.tamagolem;

import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        boolean scelta = false;

        Battaglia battaglia = new Battaglia();
        battaglia.setBattaglia();

        //SCELTA SE RICOMINCIARE UNA NUOVA PARTITA
        do{
            while(!battaglia.getGiocatore1().isSconfitto() && !battaglia.getGiocatore2().isSconfitto()) {
                battaglia.turnoConPerdente();
            }

            if(battaglia.getGiocatore1().isSconfitto()) {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 1 HA VINTO");
                System.out.println("-------------------------");
            }
            else {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 2 HA VINTO");
                System.out.println("-------------------------");
            }

        }while(scelta);


    }

    public static void (){

    }
    



}
