package edu.fiuba.algo3.Attackers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotAttack;

public interface Attack {
    void attack(Enemy enemy) throws CannotAttack;
}
