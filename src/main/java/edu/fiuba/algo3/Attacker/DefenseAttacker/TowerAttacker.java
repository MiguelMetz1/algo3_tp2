package edu.fiuba.algo3.Attacker.DefenseAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;

public class TowerAttacker implements Attacker<Enemy> {

    Distance attackDistance;

    Coordinate position;

    Buff buff;

    public TowerAttacker(Buff buff, Coordinate position, Distance attackDistance) {
        this.position = position;
        this.buff = buff;
        this.attackDistance = attackDistance;
    }

    public void attack( ArrayList<Enemy> attackables ){
        boolean hasAttacked = false;
        ArrayList<Enemy> enemies = new ArrayList<>(attackables);
        for( int i = 0; i < attackables.size() && !hasAttacked; i++) {
            Enemy enemy = Enemy.returnOneLifeDamageableBetween(enemies);
            if (!enemy.distanceToBiggerThan(position, attackDistance)) {
                enemy.takeBuff(this.buff);
                hasAttacked = true;
            }
            enemies.remove(enemy);
        }
    }

}
