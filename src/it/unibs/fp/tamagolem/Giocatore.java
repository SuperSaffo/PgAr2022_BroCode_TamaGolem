package it.unibs.fp.tamagolem;

import static it.unibs.fp.tamagolem.Battaglia.P;

public class Giocatore {

    private TamaGolem golem;
    private int numeroGolem = P;

    public Giocatore(){
    }

    public void setGolem(TamaGolem golem) {
        this.golem = golem;
    }

    public TamaGolem getGolem() {
        return golem;
    }

    public void perdiGolem() {
        this.numeroGolem--;
    }

    public TamaGolem generaGolem(Elementi[] pietreScelte){
        return new TamaGolem(pietreScelte);
    }

    public TamaGolem generaNuovoGolem(Elementi[] pietreScelte) {
        perdiGolem();
        return generaGolem(pietreScelte);
    }

    public boolean isMorto() {
        if(!golem.isMorto()) {
            System.out.println(golem.getHp());
            return false;
        }
        else {
            numeroGolem--;
            if (!isSconfitto())
                //generaGolem();
            //else
                System.out.println("Nessun golem a disposizione");
        }
        return true;
    }

    public boolean isSconfitto(){
        if (numeroGolem == 0){
            return true;
        }else
            return false;

    }



}
