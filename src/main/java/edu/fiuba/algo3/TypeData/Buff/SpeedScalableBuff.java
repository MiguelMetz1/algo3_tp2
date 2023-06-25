package edu.fiuba.algo3.TypeData.Buff;

import edu.fiuba.algo3.TypeData.Damage.Damage;
import edu.fiuba.algo3.TypeData.Speed.Speed;

public class SpeedScalableBuff extends Buff{
    public SpeedScalableBuff(int timeOfAction, Damage damage) {
        super(timeOfAction, damage);
    }

    @Override
    protected void quitBuff(Attribute attribute) {
        this.damage.removeDamage(attribute);
    }

    @Override
    protected String typeOfBuff() {
        return Speed.class.getName();
    }
}
