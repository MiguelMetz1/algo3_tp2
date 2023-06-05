package edu.fiuba.algo3.Attackers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class AttackerReadyToAttack implements Attack{

    Damage damage;

    public AttackerReadyToAttack(Damage damage) {
        this.damage = damage;
    }
    @Override
    public void attack(Enemy enemy) {
        enemy.takeDamage(this.damage);
    }

}
