package edu.fiuba.algo3.Model.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

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
