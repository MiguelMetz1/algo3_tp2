package edu.fiuba.algo3.Model.Attacker.DefenseAttacker;

import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Enemies.Enemy;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                Logger.getLogger("Attacker").log(Level.INFO, "A trap in " + position.toString() + " attacked an enemy." );
                enemy.takeBuff(this.buff);
                hasAttacked = true;
            }
        }
    }

}
