package edu.fiuba.algo3.TypeData;

public class Damage {
    int damagePoints;

    public Damage(){
        this.damagePoints = this.getDamage();
    }

    public Damage( int damagePoints ){
        this.damagePoints = damagePoints;
    }

    public void applyDamage(Energy energy) {
        energy.reduceIn( new Energy(new Life(this.damagePoints)) );
    }

    public void applyDamage(Life life) {
        life.reduceIn( new Life(this.damagePoints) );
    }

    protected int getDamage(){
        return 0;
    }

}
