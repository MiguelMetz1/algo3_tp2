package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public abstract class Buff {
    int timeOfAction;
    Damage damage;

    public Buff( int timeOfAction, Damage damage ){
        this.damage = damage;
        this.timeOfAction = timeOfAction;
    }
    public void quitBuffFrom(Attribute attribute, ArrayList<Buff> buffsToQuit){
        timeOfAction--;
        if( timeOfAction <= 0 ){
            buffsToQuit.add(this);
            damage.removeDamage(attribute);
            this.quitBuff(attribute);
        }
    }

    protected abstract void quitBuff( Attribute attribute);

    public void applyBuffIn( Attribute buffeable, ArrayList<Buff> activeBuffs ){
        if( buffeable.typeOfBuffsEquals(this.typeOfBuff()) ){
            damage.applyDamage(buffeable);
            activeBuffs.add(this);
        }
    }

    protected  abstract String typeOfBuff();

}
