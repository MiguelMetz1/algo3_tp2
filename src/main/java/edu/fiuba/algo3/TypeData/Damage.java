package edu.fiuba.algo3.TypeData;

public abstract class Damage extends Attribute{

    public Damage(double damagePoints ){
        super(damagePoints);
    }

    public  abstract void applyDamage(Attribute reducible);

    public abstract void removeDamage( Attribute buffeable);

    protected abstract String type();

}
