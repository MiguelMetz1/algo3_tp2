package edu.fiuba.algo3.TypeData;

public class SpeedScalableDamage extends Damage {

    public SpeedScalableDamage( int pointsOfDamage ){
        super(pointsOfDamage);
    }

    @Override
    public void applyDamage(Attribute reducible) {
        reducible.changeInScale(1/(this.damagePoints));
    }

    @Override
    public void removeDamage(Attribute buffeable) {
        buffeable.changeInScale(this.damagePoints);
    }
}
