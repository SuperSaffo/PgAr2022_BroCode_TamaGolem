package it.unibs.fp.tamaGolem;


import it.unibs.fp.librerie.InputDati;

/**
 * Classe main per svolgimento della partita e richiesta se iniziarne una nuova al termine
 */
public class Main {
    public static void main(String[] args) {
        //SCELTA SE RICOMINCIARE UNA NUOVA PARTITA
        do{
            //SETUP DELLA BATTAGLIA
            Battaglia battaglia = new Battaglia();
            battaglia.stampaInfoPartita();
            battaglia.setBattaglia();
            // TERMINAZIONE CICLO FIN QUANDO UNO DEI DUE GIOCATORI NON PERDE
            while(!battaglia.getGiocatore1().isSconfitto() && !battaglia.getGiocatore2().isSconfitto()) {
                battaglia.turnoConPerdente();
            }
            // STAMPA VITTORIA GIOCATORE 1
            if(battaglia.getGiocatore1().isSconfitto()) {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 2 HA VINTO");
                System.out.println("-------------------------");
            }
            // STAMPA VITTORIA GIOCATORE 2
            else {
                System.out.println("-------------------------");
                System.out.println("GIOCATORE 1 HA VINTO");
                System.out.println("-------------------------");
            }

            // STAMPA TABELLA EQUILIBRI
            System.out.println("\n\n\n");
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

