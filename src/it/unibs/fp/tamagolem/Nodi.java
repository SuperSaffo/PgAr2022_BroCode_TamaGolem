package it.unibs.fp.tamagolem;

public enum Nodi {
    ARIA,
    FUOCO,
    TERRA,
    ACQUA,
    LUCE,
    BUIO;

    private static Nodi[] nodi = Nodi.values();

    public static Nodi getNodo(int i) {
        return nodi[i];
    }
}
