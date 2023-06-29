package edu.fiuba.algo3.Model.TypeData.Buff;

import edu.fiuba.algo3.Model.TypeData.Speed.Speed;
import edu.fiuba.algo3.Model.TypeData.Damage.Damage;

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
