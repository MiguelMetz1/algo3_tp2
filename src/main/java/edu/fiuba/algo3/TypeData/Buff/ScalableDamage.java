package edu.fiuba.algo3.TypeData.Buff;

import edu.fiuba.algo3.TypeData.Damage.Damage;

public class ScalableDamage extends Damage {

    public ScalableDamage( double scalableDamagePoints ){
        super(scalableDamagePoints);
    }

    @Override
    public void applyDamage(Attribute attribute) {
        attribute.changeInScale(this.pointsOfAttribute);
    }

    @Override
    public void removeDamage(Attribute attribute) {
        attribute.changeInScale(1/(this.pointsOfAttribute));
    }

    @Override
    protected String type() {
        return this.getClass().getName();
    }
}
