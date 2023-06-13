package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Distance;

public class ReadyAttacker implements Attacker {

    Damage damage;
    Distance attackDistance;
    Plot position;

    public ReadyAttacker(Damage damage, Plot position, Distance attackDistance) {
        this.position = position;
        this.damage = damage;
        this.attackDistance = attackDistance;
    }

    public void attack( Target attackable ){
        if( !attackable.distanceToBiggerThan( position, attackDistance ) ){
            attackable.takeDamage(damage);
        }
    }

}
