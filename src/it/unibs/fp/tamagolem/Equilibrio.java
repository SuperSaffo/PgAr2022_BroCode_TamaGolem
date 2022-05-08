package it.unibs.fp.tamagolem;

import it.unibs.fp.librerie.Metodi;

import static it.unibs.fp.tamagolem.Battaglia.MAX_DANNO;
import static it.unibs.fp.tamagolem.Battaglia.N;

public class Equilibrio {
    int[][] matrix = new int[N][N];

    public Equilibrio() {

    }

    public void generaEquilibrio() {
        //for(int i = Nodi.valueOf("ARIA").ordinal(); i < N - 1; i++) {
        for(int i = 0; i < N - 1; i++) {
            int j;
            for(j = i; j < N - 1; j++) {
                if(i == j)
                    matrix[i][j] = 0;
                else {
                    matrix[i][j] = Metodi.generateRandom(-MAX_DANNO, MAX_DANNO);
                    matrix[j][i] = - matrix[i][j];
                }
            }
            if(j == N - 1) {
                matrix[i][j] = calcolaSomma(matrix[i], N);
                matrix[j][i] = - matrix[i][j];
                if(matrix[i][j] > MAX_DANNO || matrix[i][j] < -MAX_DANNO || matrix[i][j] == 0)
                    i--;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                //if(matrix[i][j] <= 0)
                //    System.out.print("0" + "\t\t");
                //else
                    System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
    }

    private static int calcolaSomma(int[] matrix, int n) {
        int somma = 0;
        for(int i = 0; i < n - 1; i++)
            somma += matrix[i];

        return ( - somma);
    }

}
