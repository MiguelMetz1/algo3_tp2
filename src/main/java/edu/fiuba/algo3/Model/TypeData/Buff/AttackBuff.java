package edu.fiuba.algo3.Model.TypeData.Buff;

import edu.fiuba.algo3.Model.TypeData.Damage.Damage;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AttackBuff extends Buff {

    public AttackBuff( Damage additiveDamage ){
        super(1, additiveDamage);
    }

    @Override
    protected void quitBuff(Attribute attribute) {
        Logger.getLogger("Buff").log(Level.INFO, "This buff cant be removed.");
    }

    @Override
    protected abstract String typeOfBuff();
}
