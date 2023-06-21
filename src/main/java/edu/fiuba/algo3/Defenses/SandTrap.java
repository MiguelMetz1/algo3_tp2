package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.TypeData.*;

public class SandTrap extends Defense{
    @Override
    protected int range() {
        return 0;
    }

    @Override
    protected int timeOfConstruction() {
        return 0;
    }

    @Override
    protected int damage() {
        return 2;
    }

    protected Buff getBuff(){
        return new SpeedScalableBuff(1, new SpeedScalableDamage(damage()));
    }

    @Override
    public String toString() {
        return "SandTrap";
    }
}
