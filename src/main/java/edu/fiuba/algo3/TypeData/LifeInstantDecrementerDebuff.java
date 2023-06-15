package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public class LifeInstantDecrementerDebuff extends Buff{

    public LifeInstantDecrementerDebuff(int timeOfAction, Damage damage ){
        super(timeOfAction, damage);
    }


    public void quitBuffFrom(Attribute buffeable, ArrayList<Buff> buffsToQuit){
        timeOfAction--;
        if( timeOfAction <= 0 ){
            buffsToQuit.add(this);
        }
    }

    @Override
    protected void quitBuff(Attribute attribute) {
        System.out.println("This debuff is instant damage debuff");
    }


    @Override
    protected String typeOfBuff() {
        return Life.class.getName();
    }
}
