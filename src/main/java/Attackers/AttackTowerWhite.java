package Attackers;

import Enemies.Enemy;
import TypeData.RangeAttack;

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
