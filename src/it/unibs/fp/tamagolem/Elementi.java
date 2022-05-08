package it.unibs.fp.tamagolem;

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
     * Metodo
     * @param e elemento
     * @return ritorna la posizione di un elemento
     */
    public static int getPosElemento(Elementi e) {
        return Elementi.valueOf(e.toString()).ordinal();   // ORDIANAL DA LA POS NELL'ENUM
    }
}
