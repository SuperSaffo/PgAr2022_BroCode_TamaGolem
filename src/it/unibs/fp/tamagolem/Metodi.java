package it.unibs.fp.tamagolem;

import java.util.Random;

public interface Metodi {

    static int generateRandom(int min, int max) {
        Random rand = new Random();
        int range = max + 1 - min;
        int ii;
        do {
            ii = rand.nextInt(range) + min;
        }while(ii == 0);

        return ii;
    }

}
