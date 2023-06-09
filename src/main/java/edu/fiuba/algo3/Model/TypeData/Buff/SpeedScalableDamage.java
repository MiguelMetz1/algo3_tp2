package edu.fiuba.algo3.Model.TypeData.Buff;

import edu.fiuba.algo3.Model.TypeData.Damage.Damage;

public class SpeedScalableDamage extends Damage {

    public SpeedScalableDamage( int pointsOfDamage ){
        super(pointsOfDamage);
    }

    @Override
    public void applyDamage(Attribute reducible) {
        reducible.changeInScale(1/(this.pointsOfAttribute));
    }

    @Override
    public void removeDamage(Attribute buffeable) {
        buffeable.changeInScale(this.pointsOfAttribute);
    }

    @Override
    protected String type() {
        return this.getClass().getName();
    }
}
