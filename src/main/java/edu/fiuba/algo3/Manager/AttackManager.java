package edu.fiuba.algo3.Manager;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class AttackManager implements Manager {

    Damage damage;

    public AttackManager(Damage damage) {

        this.damage = damage;
    }

    private void attack(Enemy enemy) {

        enemy.takeDamage(this.damage);
    }

    public void attackWithin(RangeAttack rangeAttack) throws EnemyNotFound {

        Enemy enemy = null;
        try {
            enemy = rangeAttack.findEnemy();
        } catch (EnemyNotFound e) {
            throw new EnemyNotFound(e.getMessage());
        }

        attack(enemy);
    }

}
