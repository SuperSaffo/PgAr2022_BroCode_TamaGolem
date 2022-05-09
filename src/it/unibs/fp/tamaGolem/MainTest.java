package it.unibs.fp.tamaGolem;

public class MainTest {
    public static void main(String[] args) {
        Equilibrio e = new Equilibrio();
        e.generaEquilibrioControllo();
        e.stampaEquilibrio();

        Battaglia b = new Battaglia();
        b.creaArrayPietre();

        b.stampaInfoPartita();
    }
}
