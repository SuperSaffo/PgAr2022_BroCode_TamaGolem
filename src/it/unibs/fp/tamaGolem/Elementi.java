package it.unibs.fp.tamaGolem;

/**
 * Classe enum degli elementi
 */
public enum Elementi {
    ARIA,
    FUOCO,
    TERRA,
    ACQUA,
    LUCE,
    BUIO;

    /**
     * Array contenente gli elementi
     */
    private static final Elementi[] elementi = Elementi.values();

    /**
     * Metodo per restituire un Elemento di Elementi data la posizione
     *
     * @param i Indice dell'elemento nell'enum
     * @return Ritorna un elemento
     */
    public static Elementi getElemento(int i) {
        return elementi[i];
    }

    /**
     * Metodo per restituire la posizione di un elemento nell'enum dato l'elemento
     * @param e Elemento di cui restituire la posizione
     * @return Ritorna la posizione di un elemento
     */
    public static int getPosElemento(Elementi e) {
        return Elementi.valueOf(e.toString()).ordinal();   // ORDIANAL DA LA POS NELL'ENUM
    }
}
