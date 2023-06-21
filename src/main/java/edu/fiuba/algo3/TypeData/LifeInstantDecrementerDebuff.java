package edu.fiuba.algo3.TypeData;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LifeInstantDecrementerDebuff extends AttackBuff{

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
