package edu.fiuba.algo3.Attackers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class AttackTowerWhite implements Attack{

    private RangeAttack rangeAttack;
    public AttackTowerWhite (RangeAttack rangeAttack) {
        this.rangeAttack = rangeAttack;
    }
    @Override
    public void attack(Enemy enemy) {

    }

    @Override
    public boolean withinRange() {
        return false;
    }
}
