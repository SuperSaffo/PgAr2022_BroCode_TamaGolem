package it.unibs.fp.tamaGolem;

import it.unibs.fp.librerie.Metodi;

/**
 * Classe Equilibrio per la generazione dell'equilibrio del mondo
 */
public class Equilibrio {
    /**
     * Matrice associativa per il grafo pesato e direzionale dell'equilibrio
     */
    int[][] matrix = new int[Battaglia.N][Battaglia.N];

    /**
     * Costruttore vuoto dell'equilibrio
     */
    public Equilibrio() {
    }

    /**
     * Getter di un valore della matrice date le coordinate
     * @param r Indice della riga
     * @param c Indice della colonna
     * @return Ritorna l'elemento iesimo alla colonna jesima
     */
    public int getValoreMatrix(int r, int c) {
        return matrix[r][c];
    }

    /**
     * Setter della matrice
     * @param matrix Matrice da inserire
     */
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Metodo per generare la matrice associativa in modo che sia accettabile
     * <p>La matrice viene rigenerata fin quando viene restituito falso</p>
     *
     * @see Equilibrio#generaEquilibrio()
     */
    public void generaEquilibrioControllo() {
        boolean isCorrect;
        do{
            isCorrect = generaEquilibrio();
        }while(!isCorrect);
    }

    /**
     * Metodo per generare la matrice associativa
     * <p>Vengono riempite N-1 righe e N-1 colonne con valori casuali</p>
     * <p>L'ultima colonna e' data dal numero necessario a fare si che la somma della riga equivalga a 0</p>
     * <p>se l'ultimo numero risulta Maggiore o Minore della danno massimo o -massimo oppure 0 viene generata una nuova riga</p>
     * <p>Se avvengono troppe iterazioni del ciclo viene ritornato falso</p>
     * <p>La matrice possiede sia numeri positivi che negativi,
     * i numeri negativi vengono utilizzati per calcolare piu' facilmente la matrice
     * e per calcolare piu' facilmente il vincitore dello scontro tra 2 Golem</p>
     *
     * @see Metodi#generateRandom(int, int)
     * @see Equilibrio#calcolaSomma(int[], int)
     * @see Equilibrio#setMatrix(int[][])
     * @return Ritorna falso se avvengono troppe iterazioni, altrimenti vero;
     */
    public boolean generaEquilibrio() {
        int nIter = 0;
        int[][] matrix = new int[Battaglia.N][Battaglia.N];

        /*
         * L'ULTIMA RIGA E COLONNA DELLA MATRICE SI GENERA CON IL CALCOLO DEI VALORI DELL'ULTIMO ELEMENTO DI OGNI RIGA
         */
        for(int i = 0; i < Battaglia.N - 1; i++) {
            int j;
            for(j = i; j < Battaglia.N - 1; j++) {
                /*
                 * I VALORI SULLA DIAGONALE VENGONO INIZIALIZZATI A 0
                 * GLI ALTRI VALORI VENGONO GENERATI RANDOMICAMENTE COMPRESI TRA -MAX_DANNO E MAX_DANNO
                 * NON VENGONO GENERATI ZERI
                 * NELLA MATRICE OGNI CELLA HA UN VALORE OPPOSTO RISPETTO A QUELLO DELLA CELLA SIMMETRICA SULLA DIAGONALE DI ZERI
                 */
                if(i == j)
                    matrix[i][j] = 0;
                else {
                    matrix[i][j] = Metodi.generateRandom(-Battaglia.MAX_DANNO, Battaglia.MAX_DANNO);
                    matrix[j][i] = - matrix[i][j];
                }
                nIter++;
            }
            /*
             * L'ULTIMO VALORE DELLA RIGA E' UGUALE ALLA DIFFERENZA TRA 0 E LA SOMMA DI TUTTI I VALORI PRECEDENTI SULLA RIGA
             * IL VALORE VIENE SALVATO ANCHE SUL SIMMETRICO
             */
            if(j == Battaglia.N - 1) {
                matrix[i][j] = calcolaSomma(matrix[i], Battaglia.N);
                matrix[j][i] = - matrix[i][j];
                /*
                 * CONTROLLO SULLA VALIDITA' DELL'ULTIMO VALORE
                 * IL VALORE DEVE ESSERE COOMPRESO TRA IL MAX_DANNO E MIN_DANNO E DEVE ESSERE DIVERSO DA 0
                 * SE NON RISPETTA QUESTI ALLORA SI RIGENERA LA RIGA
                 */
                if(matrix[i][j] > Battaglia.MAX_DANNO || matrix[i][j] < -Battaglia.MAX_DANNO || matrix[i][j] == 0)
                    i--;
            }

            //SE AVVENGONO TROPPE ITERAZIONI DEL CICLO PER LA GENERAZIONE DELLA MATRICE SI RITORNA FALSO
            if(nIter > 200)
                return false;
        }

        //SE IL CICLO SI CONCLUDE VIENE RICHIAMATO IL SETTER DELLA MATRICE
        setMatrix(matrix);
        //RITORNA TRUE DATO CHE LA MATRICE E' CORRETTA
        return true;
    }

    /**
     * Metodo per calcolare la somma dei valori di una riga e ritornare l'opposto
     * <p>L'opposto della somma equivale al valore necessario per rendere la somma dell'intera riga della matrice uguale a 0</p>
     *
     * @param riga Array dio valori da sommare
     * @param n Dimensione dell'Array
     * @return Ritorna l'opposto della somma
     */
    public static int calcolaSomma(int[] riga, int n) {
        int somma = 0;
        for(int i = 0; i < n - 1; i++)
            somma += riga[i];

        return (- somma);
    }

    /**
     * Metodo per stampare l'equilibrio a fine programma
     * <p>Vengono stampati inizialmente su una riga gli elementi</p>
     * <p>Prima di ogni riga viene stampato l'elemento a cui corrisponde</p>
     * <p>I valori negativi vengono stampati come zeri per semplificare la lettura dell'equilibrio</p>
     *
     * @see Equilibrio#generaEquilibrio()
     */
    public void stampaEquilibrio () {
        //STAMPA DELLA PRIMA RIGA CON GLI ELEMENTI
        System.out.print("\t\t-->\t");
        for(int i = 0; i < Battaglia.N; i++)
            System.out.print(Elementi.getElemento(i) + "\t");
        //RIGA DI A CAPO PER FORMATTAZIONE
        System.out.println();
        /*
         * PRIMA DI OGNI RIGA VIENE STAMPATO L'ELEMENTO
         * STAMPA DELLA MATRICE
         * I VALORI NEGATIVI VENGONO STAMPATI COME 0
         */
        for(int i = 0; i < Battaglia.N; i++) {
            System.out.print(Elementi.getElemento(i) + "\t\t");
            for(int j = 0; j < Battaglia.N; j++) {
                if(matrix[i][j] <= 0)
                   System.out.print("0" + "\t\t");
                else
                    System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
    }

}
