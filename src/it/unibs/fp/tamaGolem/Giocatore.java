package it.unibs.fp.tamaGolem;

/**
 * Classe giocatore
 */
public class Giocatore {

    /**
     * Golem posseduto dal giocatore
     */
    private TamaGolem golem;
    /**
     * Numero di golem posseduti dal giocatore
     */
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
        return this.numeroGolem;
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
     * <p>Il giocatore si considera sconfitto se non possiede piu' golem e l'ultimo e' morto</p>
     * @return ritorna true se il giocatore non ha più golem a disposizione e l'ultimo e' morto
     */
    public boolean isSconfitto(){
        return this.numeroGolem == 0 && this.golem.isMorto();
    }

}
