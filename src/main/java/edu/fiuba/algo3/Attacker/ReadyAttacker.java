package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

import java.util.ArrayList;
import java.util.Iterator;

public class ReadyAttacker implements Attacker<Target> {

    Distance attackDistance;

    Plot positionedPlace;

    Coordinate position;

    Buff buff;

    private boolean hasAttacked;

    public ReadyAttacker(Buff buff, Coordinate position, Distance attackDistance) {
        this.position = position;
        this.buff = buff;
        this.attackDistance = attackDistance;
        this.hasAttacked = false;
    }

    public void attack(ArrayList<Target> attackables ){
        Iterator<Target> targets = attackables.iterator();
        while ( targets.hasNext() && !hasAttacked ) {
            Target attackable = targets.next();
            if (!attackable.distanceToBiggerThan(position, attackDistance)) {
                attackable.takeBuff(this.buff);
                hasAttacked = true;
            }
        }
        hasAttacked = false;
    }

}
