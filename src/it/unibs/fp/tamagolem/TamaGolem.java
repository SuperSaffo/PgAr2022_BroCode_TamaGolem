package it.unibs.fp.tamagolem;

import static it.unibs.fp.tamagolem.Battaglia.V;

public class TamaGolem {
    private int hp = V;
    private Elementi[] pietre;

    public TamaGolem(Elementi[] pietre) {
        this.pietre = pietre;
    }

    public TamaGolem() {
    }

    public void dannoInflitto(int danno) {
        this.hp += danno;
    }

    public int getHp() {
        return hp;
    }

    public Elementi[] getPietre() {
        return pietre;
    }

    public void setPietre(Elementi[] pietre) {


        //this.pietre = g;
    }

    public boolean isMorto() {
        if(this.hp <= 0)
            return true;
        return false;
    }



}
