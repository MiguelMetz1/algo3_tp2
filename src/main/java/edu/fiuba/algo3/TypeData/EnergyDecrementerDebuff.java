package edu.fiuba.algo3.TypeData;

import edu.fiuba.algo3.Enemies.Enemy;

public class EnergyDecrementerDebuff extends Buff{
    public EnergyDecrementerDebuff(int timeOfAction, DecrementerDamage damage ){
        super(timeOfAction, damage);
    }


    @Override
    protected void quitBuff(Attribute attribute) {
        this.damage.removeDamage(attribute);
    }

    @Override
    protected String typeOfBuff() {
        return Energy.class.getName();
    }
}
