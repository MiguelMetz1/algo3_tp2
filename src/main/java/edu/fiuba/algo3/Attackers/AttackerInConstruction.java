package edu.fiuba.algo3.Attackers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class AttackerInConstruction implements Attack{

    @Override
    public void attack(Enemy enemy) throws CannotAttack {
        throw new CannotAttack("An Attacker in construction cant attack.");
    }
}
