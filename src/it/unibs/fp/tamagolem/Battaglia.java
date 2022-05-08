package it.unibs.fp.tamagolem;

import it.unibs.fp.librerie.MyMenu;

import java.util.ArrayList;
import java.util.Collections;


public class Battaglia {
    /**
     * Numero di elementi
     */
    public static final int N = Elementi.values().length;
    /**
     * Numero di pietre per ogni Golem
     */
    public static final int P = (int) Math.ceil((double)(N + 1) / 3) + 1;
    /**
     * Numero di Golem per ogni giocatore
     */
    public static final int G = (int) Math.ceil(((double)(N - 1) * (N - 2))/(2 * P));
    /**
     * Numero di pietre comuni
     */
    public static final int S = (int) Math.ceil(((double)(2 * G * P) / N)) * N;
    /**
     * Vita di ogni Golem
     */
    public static final int V = 100;
    /**
     * Danno massimo provocabile da un Golem a un altro Golem
     */
    public static final int MAX_DANNO = 100;

    /**
     * Equilibrio del sistema
     * @see Equilibrio
     */
    private Equilibrio equilibrio;
    private ArrayList<Elementi> pietreComuni;
    /**
     * Giocatore 1
     * @see Giocatore
     */
    private Giocatore giocatore1;
    /**
     * Giocatore 2
     * @see Giocatore
     */
    private Giocatore giocatore2;
    /**
     * Numero di pietra del golem che viene usata nella sfida
     * <p>Viene resettata quando si cambia il Golem</p>
     */
    private int posPietra1 = 0;
    /**
     * Numero di pietra del golem che viene usata nella sfida
     * <p>Viene resettata quando si cambia il Golem</p>
     */
    private int posPietra2 = 0;

    /**
     * Costrutture della classe battaglia
     * <p>Viene creato l'ArrayList contenente le S pietre, ci sono P pietre per ognuno degli N elementi</p>
     *
     * @see Elementi
     */
    public Battaglia() {
        this.pietreComuni = creaArrayPietre();
    }

    /**
     * Getter del giocatore 1
     *
     * @return Ritorna il giocatore 1
     */
    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    /**
     * Getter del giocatore 2
     *
     * @return Ritorna il giocatore 2
     */
    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    /**
     * Metodo per iniziare la battaglia
     * <p>Viene generato il nuovo equilibrio</p>
     * <p>Vengono generati i 2 giocatori</p>
     *
     * @see Equilibrio#generaEquilibrioControllo()
     * @see Battaglia#creaGiocatore()
     */
    public void setBattaglia() {
        this.equilibrio = new Equilibrio();
        equilibrio.generaEquilibrioControllo();
        System.out.println("--------------------------------------------------");
        System.out.println("|\tGIOCATORE 1:\t|");
        this.giocatore1 = creaGiocatore();
        System.out.println("--------------------------------------------------");
        System.out.println("|\tGIOCATORE 2:\t|");
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
     * <p>Ripete la scelta se quella inserita non e' accettabile<p/>
     * @see Battaglia#listaPietreConteggio()
     * @see MyMenu#stampaMenuNoZero()
     * @see MyMenu#scegliNoZero()
     * @return Ritorna il nome della pietra scelta
     */
    public String menuPietre() {
        String[] pietre = listaPietreConteggio();
        MyMenu menuPietre = new MyMenu("Scegli pietra per il golem: ", pietre);
        int scelta;
        do {
            scelta = menuPietre.scegliNoZero();
        }while(pietre[scelta - 1].split("\t")[1].equals("(X)"));

        return pietre[scelta - 1].split("\t")[0];
    }

    /**
     * Metodo per ritornare la lista di pietre con il numero rimanenti per ciascuna
     * <p>Se si utilizzano tutte le pietre di un tipo, questa viene sostituita con una "X"</p>
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
                pietreScelta.add(Elementi.getElemento(i).toString() + "\t(X)");
        }
        return pietreScelta.toArray(new String[0]);
    }

    /*
        /**
         * Metodo per visualizzare le pietre ancora disponibili
         * @see Battaglia#listaPietreConteggio()
         */
        /*
    public void stampaListaConteggio() {
        String[] lista = listaPietreConteggio();
        for(String s : lista)
            System.out.println(s);
    }
    */

    /**
     * Metodo per giocare il turno tra giocatore 1 e giocatore 2
     * <p>Ogni giocatore possiede un Golem che attacca con una pietra di quelle possedute</p>
     * <p>Vengono stampati a video i 2 elementi che si scontrano</p>
     * <p>Finito il scontro con la prima pietra di ciascuno si incrementa il turno e la pietra da utilizzare</p>
     * <p>Al termine lo sconfitto genera un nuovo Golem per il turno successivo</p>
     *
     * @see Battaglia#confrontoGolem(Elementi, Elementi)
     * @see Battaglia#setPosPietra1()
     * @see Battaglia#setPosPietra2()
     * @see Battaglia#nuovoGolemPerSconfitto()
     */
    public void turnoConPerdente() {
        int turno = 0;

        while(!giocatore1.getGolem().isMorto() && !giocatore2.getGolem().isMorto()){

            System.out.println("--------------------------------------------------");
            System.out.println("Turno " + (turno + 1) + ":\t");
            Elementi e1 = giocatore1.getGolem().getPietra(posPietra1);
            Elementi e2 = giocatore2.getGolem().getPietra(posPietra2);
            System.out.println("\t- (1) " + e1 + " > contro < " + e2 + " (2)");
            confrontoGolem(e1, e2);

            turno++;
            setPosPietra1();
            setPosPietra2();
        }

        nuovoGolemPerSconfitto();
    }

    /**
     * Metodo per incrementare la posizione della pietra nell'Array di pietre del Golem 1
     * <p>La posizione viene incrementata di 1 e si calcola il resto con il numero di pietre P, in modo che sia compresa tra 0 e P-1</p>
     */
    public void setPosPietra1() {
        this.posPietra1 = (this.posPietra1 + 1) % P;
    }
    /**
     * Metodo per incrementare la posizione della pietra nell'Array di pietre del Golem 2
     * <p>La posizione viene incrementata di 1 e si calcola il resto con il numero di pietre P, in modo che sia compresa tra 0 e P-1</p>
     */
    public void setPosPietra2() {
        this.posPietra2 = (this.posPietra2 + 1) % P;
    }

    /**
     * Metodo per confrontare i 2 Golem, in base al danno viene scelto chi riceve danno
     * <p>Se il danno e' positivo allora vince 1 e perde 2</p>
     * <p>Se il danno e' negativo allora vince 2 e perde 1</p>
     * <p>Se gli elementi coincidono e' un pareggio</p>
     * <p>Se il golem perdente muore non viene stampato il danno ricevuto dal colpo fatale</p>
     *
     * @param e1 Elemento della pietra del Golem 1
     * @param e2 Elemento della pietra del Golem 2
     */
    public void confrontoGolem(Elementi e1, Elementi e2) {
        int danno = equilibrio.getValoreMatrix(Elementi.getPosElemento(e1), Elementi.getPosElemento(e2));
        if(danno > 0) {
            System.out.println("\t- " + e1 + " > vince contro > " + e2);
            giocatore2.getGolem().dannoInflitto(Math.abs(danno));
            if(giocatore2.getGolem().isMorto())
                System.out.println("\t- Il Golem 2 e' morto");
            else
                System.out.println("\t- Il Golem 2 ha subito un danno di: " + Math.abs(danno));

        }
        else if (danno < 0) {
            System.out.println("\t- " + e2 + " > vince contro > " + e1);
            giocatore1.getGolem().dannoInflitto(Math.abs(danno));
            if(giocatore1.getGolem().isMorto())
                System.out.println("\t- Il Golem 1 e' morto");
            else
                System.out.println("\t- Il Golem 1 ha subito un danno di: " + Math.abs(danno));
        }
        else
            System.out.println("\t- Nessun danno subito");
    }

    /**
     * Metodo per generare il nuovo Golem del giocatore perdente
     * <p>Il golem viene generato se il giocatore non e' sconfitto e se il suo golem e' morto</p>
     * <p>La posizione della pietra iniziale del giocatore perdente viene resettata</p>
     *
     * @see TamaGolem#isMorto()
     * @see Giocatore#isSconfitto()
     * @see Giocatore#generaGolem(Elementi[])
     * @see Battaglia#posPietra1
     * @see Battaglia#posPietra2
     */
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

    /**
     * Metodo per stampare l'equilibrio al termine della partita
     * @see Equilibrio#stampaEquilibrio()
     */
    public void stampaEquilibrioBattaglia() {
        System.out.println("EQUILIBRIO DELLA PARTITA: ");
        equilibrio.stampaEquilibrio();
    }



}
