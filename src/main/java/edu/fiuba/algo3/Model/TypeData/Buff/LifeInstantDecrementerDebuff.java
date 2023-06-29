package edu.fiuba.algo3.Model.TypeData.Buff;

import edu.fiuba.algo3.Model.TypeData.Life.Life;
import edu.fiuba.algo3.Model.TypeData.Damage.AdditiveDamage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LifeInstantDecrementerDebuff extends AttackBuff {

    public LifeInstantDecrementerDebuff(AdditiveDamage damage ){
        super(damage);
    }

    @Override
    protected void quitBuff(Attribute attribute) {

        Logger.getLogger("Buff").log(Level.INFO, "This buff is a instant damage buff.");
    }


    @Override
    protected String typeOfBuff() {
        return Life.class.getName();
    }
}
