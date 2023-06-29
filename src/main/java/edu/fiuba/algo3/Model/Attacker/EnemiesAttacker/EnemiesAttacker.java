package edu.fiuba.algo3.Model.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                Logger.getLogger("Attacker").log(Level.INFO, "A enemy has attacked the player.");
                player.takeBuff(buff);
            }

        }
    }
}
