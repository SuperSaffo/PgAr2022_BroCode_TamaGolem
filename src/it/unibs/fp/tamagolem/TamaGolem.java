package it.unibs.fp.tamagolem;

public class TamaGolem {
    private int hp;
    private Nodi[] pietre;

    public TamaGolem(int hp) {
        this.hp = hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp() {
        return hp;
    }

    public Nodi[] getPietre() {
        return pietre;
    }

    public void setPietre(Nodi[] pietre) {


        //this.pietre = g;
    }

    public boolean isMorto() {
        if(this.hp <= 0)
            return true;
        return false;
    }



}
