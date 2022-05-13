package it.unibs.fp.tamaGolem;

/**
 * Classe giocatore
 */

public class Giocatore {

    private TamaGolem golem;
    private int numeroGolem = Battaglia.G;

    /**
     * Costruttore vuoto del giocatore
     */
    public Giocatore(){
    }

    /**
     * Getter golem giocatore
     * @return ritorna un golem posseduto da giocatore
     */
    public TamaGolem getGolem() {
        return golem;
    }

    public int getNumeroGolem() {
        return numeroGolem;
    }

    /**
     * Medoto che toglie un golem al giocatore
     */
    public void perdiGolem() {
        this.numeroGolem--;
    }

    /**
     * Metodo che genera un nuovo golem
     * @see Giocatore#perdiGolem()
     * @param pietreScelte numero di pietre per ogni golem
     */
    public void generaGolem(Elementi[] pietreScelte){
        perdiGolem();
        this.golem = new TamaGolem(pietreScelte);
    }


    /**
     * Metodo che controlla che un giocatore è sconfitto
     * @return ritorna true se il giocatore non ha più golem a disposizione
     */
    public boolean isSconfitto(){
        if (numeroGolem == 0 && golem.isMorto()){
            return true;
        }else
            return false;
    }

}
