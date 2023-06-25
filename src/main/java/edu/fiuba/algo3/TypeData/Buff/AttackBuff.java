package edu.fiuba.algo3.TypeData.Buff;

import edu.fiuba.algo3.TypeData.Damage.Damage;

public abstract class AttackBuff extends Buff {

    public AttackBuff( Damage additiveDamage ){
        super(1, additiveDamage);
    }

    @Override
    protected void quitBuff(Attribute attribute) {
        System.out.println("This buff can't be removed.");
    }

    @Override
    protected abstract String typeOfBuff();
}
