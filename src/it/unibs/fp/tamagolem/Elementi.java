package it.unibs.fp.tamagolem;

public enum Elementi {
    ARIA,
    FUOCO,
    TERRA,
    ACQUA,
    LUCE,
    BUIO;

    private static Elementi[] elementi = Elementi.values();

    public static Elementi getElemento(int i) {
        return elementi[i];
    }
}
