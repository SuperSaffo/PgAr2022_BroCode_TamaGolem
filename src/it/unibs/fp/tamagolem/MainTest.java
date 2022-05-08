package it.unibs.fp.tamagolem;

public class MainTest {
    public static void main(String[] args) {
        Battaglia b = new Battaglia();
        //b.creaArrayPietre();
        Elementi[] n = b.scegliPietre();
        //b.stampaListaConteggio();

        Elementi[] n1 = b.scegliPietre();

        b.stampaListaConteggio();
    }
}
