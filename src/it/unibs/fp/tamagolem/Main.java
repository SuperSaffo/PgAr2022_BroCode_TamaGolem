package it.unibs.fp.tamagolem;

import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.Random;

public class Main {


    public static void main(String[] args) {
        Equilibrio eq = new Equilibrio();
        eq.generaEquilibrio();

        Battaglia b = new Battaglia();

        b.creaArrayPietre();

    }
    



}
