package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Distance;

public class ReadyAttacker implements Attacker {

    Damage damage;
    Distance attackDistance;
    Plot position;

    Buff buff;

    public ReadyAttacker(Buff buff, Plot position, Distance attackDistance) {
        this.position = position;
        this.buff = buff;
        this.attackDistance = attackDistance;
    }

    public void attack( Target attackable ){
        if( !attackable.distanceToBiggerThan( position, attackDistance ) ){
            //attackable.takeDamage(damage);//Descomentar si el refactor no funciona
            attackable.takeBuff(this.buff);
        }
    }

}
