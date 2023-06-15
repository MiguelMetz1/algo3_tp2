package edu.fiuba.algo3.TypeData;

public class ScalableDamage extends Damage{

    public ScalableDamage( int scalableDamagePoints ){
        super(scalableDamagePoints);
    }

    @Override
    public void applyDamage(Attribute reducible) {
        reducible.changeInScale(this.damagePoints);
    }

    @Override
    public void removeDamage(Attribute buffeable) {
        buffeable.changeInScale(1/(this.damagePoints));
    }
}
