package edu.fiuba.algo3.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OwlAttacker implements Attacker<Player> {

    private final Coordinate position;

    public OwlAttacker(Coordinate actualPosition) {
        this.position = actualPosition;
    }

    @Override
    public void attack(ArrayList<Player> players) {

        for ( Player player : players) {

            if( !player.distanceToBiggerThan( position, new Distance(0) ) ) {
                Logger.getLogger("Attacker").log(Level.INFO, "A owl has attacked a tower.");
                player.attackFirstDefense();

            }

        }

    }
}
