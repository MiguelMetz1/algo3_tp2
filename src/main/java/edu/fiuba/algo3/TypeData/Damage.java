package edu.fiuba.algo3.TypeData;

public abstract class Damage {
    int damagePoints;

    public Damage(){
        this.damagePoints = this.getDamage();
    }

    public Damage(int damagePoints ){
        this.damagePoints = damagePoints;
    }

    public void incrementIn( int incrementPoints ){
        this.damagePoints += incrementPoints;
    }

    public  abstract void applyDamage(Attribute reducible);

    public abstract void removeDamage( Attribute buffeable);

    protected int getDamage(){
        return 0;
    }

}
