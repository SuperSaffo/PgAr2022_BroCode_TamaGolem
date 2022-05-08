package it.unibs.fp.tamagolem;

public enum Elementi {
    ARIA,
    FUOCO,
    TERRA,
    ACQUA,
    LUCE,
    BUIO;

    private static Elementi[] elementi = Elementi.values();

    /**
     * Metodo per restituire un Elemento di Elementi data la posizione
     *
     * @param i Indice dell'elemento nell'enum
     * @return Ritorna un elemento
     */
    public static Elementi getElemento(int i) {
        return elementi[i];
    }

    public static int getPosElemento(Elementi e) {
        return Elementi.valueOf(e.toString()).ordinal();   // ordinal da la pos nell' enum
    }
}
