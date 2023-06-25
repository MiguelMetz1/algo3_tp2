package edu.fiuba.algo3.TypeData.Buff;

import edu.fiuba.algo3.TypeData.Damage.AdditiveDamage;
import edu.fiuba.algo3.TypeData.Energy.Energy;

public class EnergyInstantDecrementerDebuff extends AttackBuff {
    public EnergyInstantDecrementerDebuff(AdditiveDamage damage ){
        super(damage);
    }

    @Override
    protected String typeOfBuff() {
        return Energy.class.getName();
    }
}
