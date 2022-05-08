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

    public void isMorto() {
        if(!golem.isMorto())
            System.out.println(golem.getHp());
        else {
            numeroGolem--;
            if (!isSconfitto()) generaGolem();
            else
                System.out.println("Nessun golem a disposizione");
        }
    }

    public boolean isSconfitto(){
        if (numeroGolem == 0){
            return true;
        }else
            return false;

    }

    public TamaGolem generaGolem(){
        return new TamaGolem(golem.getHp());
    }

}
