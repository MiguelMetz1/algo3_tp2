package edu.fiuba.algo3.Model.TypeData.Buff;

import edu.fiuba.algo3.Model.TypeData.Energy.Energy;
import edu.fiuba.algo3.Model.TypeData.Damage.AdditiveDamage;

public class EnergyInstantDecrementerDebuff extends AttackBuff {
    public EnergyInstantDecrementerDebuff(AdditiveDamage damage ){
        super(damage);
    }

    @Override
    protected String typeOfBuff() {
        return Energy.class.getName();
    }
}
