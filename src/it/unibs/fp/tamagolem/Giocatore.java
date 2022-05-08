package it.unibs.fp.tamagolem;

import static it.unibs.fp.tamagolem.Battaglia.G;

public class Giocatore {

    private TamaGolem golem;
    private int numeroGolem = G;

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

    public void generaGolem(Elementi[] pietreScelte){
        perdiGolem();
        this.golem = new TamaGolem(pietreScelte);
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

    //public StringBuffer infoGiocatore(){}



}
