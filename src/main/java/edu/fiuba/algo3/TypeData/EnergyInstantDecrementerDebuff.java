package edu.fiuba.algo3.TypeData;

public class EnergyInstantDecrementerDebuff extends AttackBuff{
    public EnergyInstantDecrementerDebuff(AdditiveDamage damage ){
        super(damage);
    }

    @Override
    protected String typeOfBuff() {
        return Energy.class.getName();
    }
}
