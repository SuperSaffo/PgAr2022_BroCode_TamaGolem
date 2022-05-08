package it.unibs.fp.tamagolem;

import it.unibs.fp.librerie.MyMenu;

import java.util.ArrayList;
import java.util.Collections;

public class Battaglia {
    public static final int N = Elementi.values().length;                                   //NUMERO ELEMENTI
    public static final int P = (int) Math.ceil((double)(N + 1) / 3) + 1;                   //PIETRE PER GOLEM
    public static final int G = (int) Math.ceil(((double)(N - 1) * (N - 2))/(2 * P));       //GOLEM PER GIOCATORE
    public static final int S = (int) Math.ceil(((double)(2 * G * P) / N)) * N;             //PIETRE COMUNI
    public static final int V = 100;                                                        //VITA GOLEM
    public static final int MAX_DANNO = 100;                                                //DANNO MASSIMO

    private Equilibrio equilibrio;
    private ArrayList<Elementi> pietreComuni;
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private int posPietra1 = 0;
    private int posPietra2 = 0;

    public Battaglia() {
        this.pietreComuni = creaArrayPietre();
    }

    public ArrayList<Elementi> getPietreComuni() {
        return pietreComuni;
    }

    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    /**
     * Metodo per iniziare la battaglia
     * <p>Viene generato il nuovo equilibrio</p>
     * <p>Vengono generati i 2 giocatori</p>
     * <p>Ogni giocatore sceglie le prime P pietre per il loro primo Golem</p>
     *
     * @see Equilibrio#generaEquilibrio()
     */
    public void setBattaglia() {
        this.equilibrio = new Equilibrio();
        equilibrio.generaEquilibrio();
        System.out.println("GIOCATORE 1: ");
        this.giocatore1 = creaGiocatore();
        System.out.println("GIOCATORE 2: ");
        this.giocatore2 = creaGiocatore();
    }

    /**
     * Metodo per creare un ArrayList di pietre comuni
     * <p>Vengono inserite P pietre per ogni elemento</p>
     *
     * @see Elementi
     * @see Elementi#getElemento(int)
     * @return Ritorna le pietre comuni per i giocatori
     */
    public ArrayList<Elementi> creaArrayPietre() {
        ArrayList<Elementi> pietreComuni = new ArrayList<>();
        for(int i = 0; i < S / P; i++) {
            for(int j = 0; j < P; j++) {
                pietreComuni.add(Elementi.getElemento(i));
            }
        }

        return pietreComuni;
    }

    /**
     * Metodo per creare un nuovo giocatore
     * <p>Il giocatore sceglie le 4 pietre del suo primo Golem</p>
     * <p>Viene generato il primo golem del giocatore</p>
     *
     * @see Giocatore#Giocatore()
     * @see Giocatore#generaGolem(Elementi[])
     * @return Ritorna il nuovo giocatore
     */
    public Giocatore creaGiocatore() {
        Giocatore g = new Giocatore();
        Elementi[] pietreScelte = scegliPietre();
        g.generaGolem(pietreScelte);

        return g;
    }

    /**
     * Metodo per scegliere le P pietre per ogni TamaGolem
     * <p>Le pietre vengono scelte tra quelle disponibili tra quelle comuni</p>
     *
     * @see Battaglia#menuPietre()
     * @return Ritorna Array di Nodi con le P pietre scelte
     */
    public Elementi[] scegliPietre() {
        Elementi[] pietreGolem = new Elementi[P];
        for(int i = 0; i < P; i++) {
            String pietraScelta = menuPietre();
            pietreGolem[i] = Elementi.valueOf(pietraScelta);
            this.pietreComuni.remove(Elementi.valueOf(pietraScelta));
        }

        return pietreGolem;
    }

    /**
     * Metodo con menu per scegliere la pietra da dare al proprio golem
     *
     * @see Battaglia#listaPietreConteggio()
     * @return Ritorna il nome della pietra scelta
     */
    public String menuPietre() {
        String[] pietre = listaPietreConteggio();
        MyMenu menuPietre = new MyMenu("Scegli pietra per il golem: ", pietre);
        int scelta;
        do {
            scelta = menuPietre.scegliNoZero();
        }while(pietre[scelta - 1].equals("X"));

        return pietre[scelta - 1].split("\t")[0];
    }

    /**
     * Metodo per ritornare la lista di pietre con il numero rimanenti per ciascuna
     * <p>Se si utilizzano tutte le pietre di un tipo, questa non viene piu' mostrata</p>
     *
     * @see Collections
     * @see Elementi#getElemento(int) 
     * @return Ritorna array di String con pietre e loro quantita' se maggiore di 0
     */
    public String[] listaPietreConteggio() {
        ArrayList<String> pietreScelta = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(Collections.frequency(pietreComuni, Elementi.getElemento(i)) > 0)
                pietreScelta.add(Elementi.getElemento(i).toString() + "\t("+ Collections.frequency(pietreComuni, Elementi.getElemento(i)) + ")");
            else
                pietreScelta.add("X");
        }
        return pietreScelta.toArray(new String[0]);
    }

    /**
     * Metodo per visualizzare le pietre ancora disponibili
     * @see Battaglia#listaPietreConteggio()
     */
    public void stampaListaConteggio() {
        String[] lista = listaPietreConteggio();
        for(String s : lista)
            System.out.println(s);
    }

    public void turnoConPerdente() {
        int turno = 0;

        while(!giocatore1.getGolem().isMorto() && !giocatore2.getGolem().isMorto()){

            System.out.println("--------------------------------");
            System.out.println("Turno " + (turno + 1) + ":");
            Elementi e1 = giocatore1.getGolem().getPietra(posPietra1);
            Elementi e2 = giocatore2.getGolem().getPietra(posPietra2);
            System.out.println("Scontro: 1) " + e1 + " > contro > 2) " + e2);
            confrontoGolem(e1, e2);

            turno++;
            setPosPietra1();
            setPosPietra2();
        }

        nuovoGolemPerSconfitto();
    }

    public void setPosPietra1() {
        this.posPietra1 = (this.posPietra1 + 1) % P;
    }
    public void setPosPietra2() {
        this.posPietra2 = (this.posPietra2 + 1) % P;
    }

    public void confrontoGolem(Elementi e1, Elementi e2) {
        int danno = equilibrio.getValoreMatrix(Elementi.getPosElemento(e1), Elementi.getPosElemento(e2));
        if(danno > 0) {
            System.out.println(e1 + " > vince contro > " + e2);
            giocatore2.getGolem().dannoInflitto(Math.abs(danno));
            if(giocatore2.getGolem().isMorto())
                System.out.println("Il Golem 2 e' morto");
            else
                System.out.println("Il Golem 2 ha subito un danno di: " + Math.abs(danno));

        }
        else if (danno < 0) {
            System.out.println(e2 + " > vince contro > " + e1);
            giocatore1.getGolem().dannoInflitto(Math.abs(danno));
            if(giocatore1.getGolem().isMorto())
                System.out.println("Il Golem 1 e' morto");
            else
                System.out.println("Il Golem 1 ha subito un danno di: " + Math.abs(danno));
        }
    }

    public void nuovoGolemPerSconfitto(){
        if(!giocatore1.isSconfitto() && giocatore1.getGolem().isMorto()) {
            Elementi[] pietreScelte = scegliPietre();
            giocatore1.generaGolem(pietreScelte);
            this.posPietra1 = 0;
        }
        else if(!giocatore2.isSconfitto() && giocatore2.getGolem().isMorto()){
            Elementi[] pietreScelte = scegliPietre();
            giocatore2.generaGolem(pietreScelte);
            this.posPietra2 = 0;
        }
    }

    public void stampaEquilibrioBattaglia() {
        System.out.println("EQUILIBRIO DELLA PARTITA");
        equilibrio.stampaEquilibrio();
    }



}
