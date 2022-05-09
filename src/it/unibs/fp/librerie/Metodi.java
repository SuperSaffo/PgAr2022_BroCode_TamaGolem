package it.unibs.fp.librerie;



import java.util.Random;

/**
 * Interfaccia con metodi utilizzati
 */
public interface Metodi {

    /**
     * Metodo per generare un numero casuale compreso tra 2 estremi, escluso lo 0
     * <p>Il numero viene rigenerato se e' uguale a 0</p>
     *
     * @param min Estremo inferiore dell'intervallo
     * @param max Estremo superiore dell'intervallo
     * @return Ritorna il numero randomico generato
     */
    static int generateRandom(int min, int max) {
        Random rand = new Random();
        int range = max + 1 - min;
        int ii;
        do {
            ii = rand.nextInt(range) + min;
        }while(ii == 0);

        return ii;
    }

}
