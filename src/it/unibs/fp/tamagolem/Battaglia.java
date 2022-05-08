package it.unibs.fp.tamagolem;

public class Battaglia {
    public static final int N = Nodi.values().length;
    public static final int P = (int) Math.ceil((double)(N + 1) / 3) + 1;
    public static final int G = (int) Math.ceil(((double)(N - 1) * (N - 2))/(2 * P));
    public static final int S = (int) Math.ceil(((double)(2 * G * P) / N)) * N;
    public static final int V = 100;
    public static final int MAX_DANNO = 100;

    private Equilibrio equilibrio;
    private Nodi[] pietreComuni;
    private Giocatore giocatore1;
    private Giocatore giocatore2;

    public Battaglia() {
    }

    public void setBattaglia() {
        this.equilibrio = new Equilibrio();
        this.pietreComuni = creaArrayPietre();

    }

    public Nodi[] creaArrayPietre() {
        Nodi[] pietreComuni = new Nodi[S];
        for(int i = 0; i < S / P; i++) {
            for(int j = 0; j < P; j++) {
                pietreComuni[i] = Nodi.getNodo(i);
                System.out.println(pietreComuni[i]);
            }
        }

        return pietreComuni;
    }
}
