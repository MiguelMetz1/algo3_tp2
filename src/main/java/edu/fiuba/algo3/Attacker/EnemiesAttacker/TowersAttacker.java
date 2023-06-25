package edu.fiuba.algo3.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;

public class TowersAttacker implements Attacker<PlayerCharacter> {

    private final Coordinate position;

    public TowersAttacker(Coordinate actualPosition) {
        this.position = actualPosition;
    }

    @Override
    public void attack(ArrayList<PlayerCharacter> players) {

        for ( PlayerCharacter player : players) {

            if( !player.distanceToBiggerThan( position, new Distance(0) ) ) {

                player.attackFirstDefense();

            }

        }

    }
}
