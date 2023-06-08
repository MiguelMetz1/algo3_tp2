package edu.fiuba.algo3.Manager;

import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class ConstructionManager implements Manager {


    public void attackWithin(RangeAttack rangeAttack) throws CannotAttack {

        throw new CannotAttack("An Attacker in construction cant attack.");
    }
}
