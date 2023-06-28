package edu.fiuba.algo3.Attacker.DefenseAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;
import java.util.Iterator;

public class TrapAttacker implements Attacker<Enemy> {
    Distance attackDistance;

    Coordinate position;

    Buff buff;

    public TrapAttacker(Buff buff, Coordinate position, Distance attackDistance) {
        this.position = position;
        this.buff = buff;
        this.attackDistance = attackDistance;
    }

    public void attack( ArrayList<Enemy> attackables ){
        boolean hasAttacked = false;
        ArrayList<Enemy> enemies = new ArrayList<>(attackables);
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext() && !hasAttacked){
            Enemy enemy = enemyIterator.next();
            if (!enemy.distanceToBiggerThan(position, attackDistance)) {
                enemy.takeBuff(this.buff);
                hasAttacked = true;
            }
        }
    }

}