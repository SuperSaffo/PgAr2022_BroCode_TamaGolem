package it.unibs.fp.tamaGolem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static it.unibs.fp.tamaGolem.Battaglia.V;

/**
 * Classe Golem
 */

public class TamaGolem {
    private int hp = V;
    private Elementi[] pietre;

    /**
     * Costruttore della classe Golem
     * @param pietre numero di pietre per ogni golem
     */
    public TamaGolem(Elementi[] pietre) {
        this.pietre = pietre;
    }

    /**
     * Getter
     * @param i posizione della pietra
     * @return ritorna la pietra alla posizione iesima
     */
    public Elementi getPietra(int i) {
        return this.pietre[i];
    }

    /**
     * Metodo che calcola la vita rimanente
     * @param danno danno inflitto durante lo scontro
     */
    public void dannoInflitto(int danno) {
        this.hp -= danno;
    }

    /**
     * Metodo che controlla che il golem abbia ancora vita
     * @return ritorna true se il golem è morto, false se vivo
     */
    public boolean isMorto() {
        return this.hp <= 0;
    }

    /**
     * Metodo per mischiare l'Array di pietre
     */
    public void mischiaPietre() {
        List<Elementi> mischiaPietre = Arrays.asList(this.pietre);

        Collections.shuffle(mischiaPietre);

        mischiaPietre.toArray(this.pietre);
    }



}
