package it.unibs.fp.tamagolem;

import it.unibs.fp.mylib.EstrazioniCasuali;

import java.util.Random;

public class Main {
    private static final int MAX_DANNO = 5;

    public static void main(String[] args) {
        int n = 6;
        double p = Math.ceil((n + 1) / 3) + 1;
        double g = Math.ceil(((n - 1) * (n - 2))/(2 * p));
        double s = Math.ceil(((2 * g * p) / n)) * n;

        int[][] matrix = new int[n][n];


        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j)
                    matrix[i][j] = 0;
                else if(matrix[j][i] == 0){
                    matrix[i][j] = generateRandom(0, 5);
                }
                else
                    matrix[i][j] = 0;
            }
        }

        /*
        for(int i = 0; i < n; i++) {
            int j;
            for(j = i; j < n - 1; j++) {
                if(i == j)
                    matrix[i][j] = 0;
                else {
                    matrix[i][j] = generateRandom(-MAX_DANNO, MAX_DANNO);
                    matrix[j][i] = - matrix[i][j];
                }
            }
            if(j == n - 1 && i != n - 1) {
                matrix[i][j] = calcolaSomma(matrix[i], n);
                matrix[j][i] = - matrix[i][j];
                if(matrix[i][j] > MAX_DANNO || matrix[i][j] < -MAX_DANNO || matrix[i][j] == 0)
                    i--;
            }

        }
         */


        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }


    }

    private static int calcolaSomma(int[] matrix, int n) {
        int somma = 0;
        for(int i = 0; i < n; i++)
            somma += matrix[i];

        return (somma * -1);
    }

    /*
    public static int generateRandom(int min, int max) {
        Random rand = new Random();
        int ii;

        do {
            //ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
            ii = (int) (Math.random() * (max - min + 1)) + min;
        }while(ii == 0);

        return ii;
    }
     */

    public static int generateRandom(int min, int max) {
        Random rand = new Random();
        int range = max + 1 - min;
        int ii;
        //do {
            //ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
            ii = rand.nextInt(range);
            ii += min;
        //}while(ii == 0);

        return ii;
    }


}
