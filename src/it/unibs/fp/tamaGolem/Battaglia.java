package it.unibs.fp.tamaGolem;

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
    public static final int V = 10;
    /**
     * Danno massimo provocabile da un Golem a un altro Golem, equivale al massimo della vita del Golem
     */
    public static final int MAX_DANNO = V;
    public static final String CORNICE_ASTERISCHI = "*************************************************";
    public static final String CORNICE_LINEA = "-------------------------------------------------";

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
        System.out.println("Nuovo equilibrio del mondo generato");
        System.out.println("\t\t\tINIZIO DELLA PARTITA\n");
        equilibrio.generaEquilibrioControllo();
        System.out.println(CORNICE_ASTERISCHI);
        System.out.println("*\t\t\tGIOCATORE 1 (" + G + " Golem):\t\t\t\t*");
        System.out.println(CORNICE_ASTERISCHI);
        this.giocatore1 = creaGiocatore();
        System.out.println(CORNICE_ASTERISCHI);
        System.out.println("*\t\t\tGIOCATORE 2 (" + G + " Golem):\t\t\t\t*");
        System.out.println(CORNICE_ASTERISCHI);

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
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < S / N; j++) {
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
     * <p>Al termine delle scelte vengono stampate le P pietre scelte per il Golem</p>
     *
     * @see Battaglia#menuPietre()
     * @return Ritorna Array di Nodi con le P pietre scelte
     */
    public Elementi[] scegliPietre() {
        Elementi[] pietreGolem = new Elementi[P];
        System.out.println("Scegli " + P + " pietre per il Golem");
        for(int i = 0; i < P; i++) {
            String pietraScelta = menuPietre();
            pietreGolem[i] = Elementi.valueOf(pietraScelta);
            this.pietreComuni.remove(Elementi.valueOf(pietraScelta));
        }
        System.out.print("\nPietre del Golem: ");
        for(int i = 0; i < P; i++)
            System.out.print(pietreGolem[i] + "\t");
        System.out.println("\n" + CORNICE_LINEA + "\n");

        return pietreGolem;
    }

    /**
     * Metodo con menu per scegliere la pietra da dare al proprio golem
     * <p>Ripete la scelta se quella inserita non e' accettabile
     * @see Battaglia#listaPietreConteggio()
     * @see MyMenu#stampaMenuNoZero()
     * @see MyMenu#scegliNoZero()
     * @return Ritorna il nome della pietra scelta
     */
    public String menuPietre() {
        String[] pietre = listaPietreConteggio();
        MyMenu menuPietre = new MyMenu("Pietre comuni disponibili (" + this.pietreComuni.size() + "): ", pietre);
        int scelta;
        do {
            scelta = menuPietre.scegliNoZero();
        }while(pietre[scelta - 1].split("\t")[1].equals("(X)"));

        return pietre[scelta - 1].split("\t")[0];
    }

    /**
     * Metodo per ritornare la lista di pietre con il numero rimanenti per ciascuna
     * <p>Se si utilizzano tutte le pietre di un tipo, il numero di rimanenti viene sostituita con una "X"</p>
     *
     * @see Collections
     * @see Elementi#getElemento(int) 
     * @return Ritorna array di String con pietre e loro quantita' se maggiore di 0
     */
    public String[] listaPietreConteggio() {
        ArrayList<String> pietreScelta = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(Collections.frequency(this.pietreComuni, Elementi.getElemento(i)) > 0)
                pietreScelta.add(Elementi.getElemento(i).toString() + "\t("+ Collections.frequency(this.pietreComuni, Elementi.getElemento(i)) + ")");
            else
                pietreScelta.add(Elementi.getElemento(i).toString() + "\t(X)");
        }
        return pietreScelta.toArray(new String[0]);
    }

    /**
     * Metodo per giocare il turno tra giocatore 1 e giocatore 2
     * <p>Ogni giocatore possiede un Golem che attacca con una pietra di quelle possedute</p>
     * <p>Vengono stampati a video i 2 elementi che si scontrano</p>
     * <p>Finito il scontro con la prima pietra di ciascuno si incrementa il turno e la pietra da utilizzare</p>
     * <p>Se avvengono P pareggi consecutivi vengono mescolati gli ordini delle pietre dei </p>
     * <p>Al termine c'e' un messaggio che indica il Golem sconfitto e il giocatore sconfitto genera un nuovo Golem per il turno successivo</p>
     *
     * @see Battaglia#confrontoGolem(Elementi, Elementi)
     * @see Battaglia#setPosPietra1()
     * @see Battaglia#setPosPietra2()
     * @see Battaglia#nuovoGolemPerSconfitto()
     */
    public void turnoConPerdente() {
        int turno = 0;
        int npareggi = 0;

        System.out.println("\n" + CORNICE_ASTERISCHI);
        System.out.println("*\t\t\tINIZIO DELLO SCONTRO\t\t\t\t*");
        System.out.println(CORNICE_ASTERISCHI + "\n");

        while(!this.giocatore1.getGolem().isMorto() && !this.giocatore2.getGolem().isMorto()){

            System.out.println(CORNICE_LINEA);
            System.out.println("Turno " + (turno + 1) + ":\t");
            Elementi e1 = this.giocatore1.getGolem().getPietra(this.posPietra1);
            Elementi e2 = this.giocatore2.getGolem().getPietra(this.posPietra2);
            System.out.println("\t- (1) " + e1 + " > contro < " + e2 + " (2)");

            //CONTEGGIO DEI PAREGGI, SE IN UN TURNO UN GOLEM SUBISCE DANNO SI RESETTA
            if(confrontoGolem(e1, e2))
                npareggi = 0;
            else
                npareggi++;

            //SE AVVENGONO P PAREGGI SI MESCOLA L'ORDINE DELLE PIETRE
            if(npareggi >= P) {
                this.giocatore1.getGolem().mischiaPietre();
                this.giocatore2.getGolem().mischiaPietre();
            }

            //AUMENTA TURNO, INCREMENTA LA PIETRA DA USARE NEL COMBATTIMENTO SUCCESSIVO
            turno++;
            setPosPietra1();
            setPosPietra2();
        }

        System.out.println(CORNICE_LINEA);
        System.out.println("SCONTRO TERMINATO");
        if(this.giocatore1.getGolem().isMorto())
            System.out.println("Il Golem di giocatore 1 ha perso lo scontro");
        else
            System.out.println("Il Golem di giocatore 2 ha perso lo scontro");

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
     * Metodo per confrontare i 2 Golem, in base a chi vince il confronto viene scelto chi riceve danno
     * <p>Se il danno e' positivo allora vince 1 e perde 2</p>
     * <p>Se il danno e' negativo allora vince 2 e perde 1</p>
     * <p>Se gli elementi coincidono e' un pareggio</p>
     * <p>Se avviene un pareggio si ritorna falso, altrimenti si ritorna vero</p>
     * <p>Se il golem perdente muore non viene stampato il danno ricevuto dal colpo fatale</p>
     *
     * @see Equilibrio
     * @param e1 Elemento della pietra del Golem 1
     * @param e2 Elemento della pietra del Golem 2
     * @return Ritorna falso se pareggiano, altrimenti vero
     */
    public boolean confrontoGolem(Elementi e1, Elementi e2) {
        int danno = this.equilibrio.getValoreMatrix(Elementi.getPosElemento(e1), Elementi.getPosElemento(e2));
        if(danno > 0) {
            System.out.println("\t- " + e1 + " > vince contro > " + e2);
            this.giocatore2.getGolem().dannoInflitto(Math.abs(danno));

            if(this.giocatore2.getGolem().isMorto())
                System.out.println("\t- Il Golem 2 e' morto");
            else
                System.out.println("\t- Il Golem 2 ha subito un danno di: " + Math.abs(danno));
        }
        else if (danno < 0) {
            System.out.println("\t- " + e2 + " > vince contro > " + e1);
            this.giocatore1.getGolem().dannoInflitto(Math.abs(danno));

            if(this.giocatore1.getGolem().isMorto())
                System.out.println("\t- Il Golem 1 e' morto");
            else
                System.out.println("\t- Il Golem 1 ha subito un danno di: " + Math.abs(danno));
        }
        else {
            System.out.println("\t- Nessun danno subito");
            return false;
        }
        return true;
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
        System.out.println(CORNICE_LINEA + "\n");

        if(!this.giocatore1.isSconfitto() && this.giocatore1.getGolem().isMorto()) {
            System.out.println(CORNICE_ASTERISCHI);
            System.out.println("*\t\t\tGIOCATORE 1 (" + this.giocatore1.getNumeroGolem() + " Golem):\t\t\t\t*");
            System.out.println(CORNICE_ASTERISCHI);

            Elementi[] pietreScelte = scegliPietre();
            giocatore1.generaGolem(pietreScelte);
            this.posPietra1 = 0;
        }
        else if(!this.giocatore2.isSconfitto() && this.giocatore2.getGolem().isMorto()){
            System.out.println(CORNICE_ASTERISCHI);
            System.out.println("*\t\t\tGIOCATORE 2 (" + this.giocatore2.getNumeroGolem()  + " Golem):\t\t\t\t*");
            System.out.println(CORNICE_ASTERISCHI);

            Elementi[] pietreScelte = scegliPietre();
            this.giocatore2.generaGolem(pietreScelte);
            this.posPietra2 = 0;
        }
        System.out.println("\n");
    }


    /**
     * Metodo per stampare le regole del gioco all'inizio di ogni partita
     * <p>Il gioco dipende completamente da numero di elementi del mondo</p>
     *
     * @see Elementi
     * @see Battaglia#N
     * @see Battaglia#G
     * @see Battaglia#P
     * @see Battaglia#S
     */
    public void stampaInfoPartita() {
        System.out.println(CORNICE_LINEA);
        System.out.println("\t\t\tREGOLE DEL GIOCO");
        System.out.print("+ Totale elementi: " + N);

        for(int i = 0; i < Elementi.values().length; i++) {
            if(i % 3 == 0)
                System.out.println();
            System.out.print("\t" + Elementi.getElemento(i));
        }

        System.out.println("\n+ Totale pietre comuni: " + S);
        System.out.println("+ Ogni giocatore ha " + G + " Golem");
        System.out.println("+ Ogni Golem ha " + V + " HP");
        System.out.println("+ Per ogni Golem il giocatore sceglie " + P + " pietre");
        System.out.println("\n\tGLHF");
        System.out.println(CORNICE_LINEA);

    }

    /**
     * Metodo per stampare l'equilibrio al termine della partita
     * @see Equilibrio#stampaEquilibrio()
     */
    public void stampaEquilibrioBattaglia() {
        System.out.println("EQUILIBRIO DELLA PARTITA: ");
        this.equilibrio.stampaEquilibrio();
    }



}
