package edu.fiuba.algo3.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;

public class EnemiesAttacker implements Attacker<Player> {
    Distance attackDistance;

    Coordinate position;

    Buff buff;

    public EnemiesAttacker(Buff buff, Coordinate position, Distance attackDistance) {
        this.position = position;
        this.buff = buff;
        this.attackDistance = attackDistance;
    }

    public void attack( ArrayList<Player> players ){
        for ( Player player : players) {
            if( !player.distanceToBiggerThan( position, new Distance(0) ) ) {
                player.takeBuff(buff);
            }

        }
    }
}
