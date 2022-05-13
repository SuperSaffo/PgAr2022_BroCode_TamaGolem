package it.unibs.fp.tamaGolem;

public class MainTest {

    public static void main(String[] args) {
        Equilibrio e = new Equilibrio();
        e.generaEquilibrioControllo();
        e.stampaEquilibrio();


        System.out.println(Battaglia.N);
        System.out.println(Battaglia.S);

        System.out.println(Battaglia.P);

        System.out.println(Battaglia.G);



        Battaglia b = new Battaglia();
        b.creaArrayPietre();

        b.stampaInfoPartita();
    }
}
