package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.PlayerCharacter;

public class SandTrapDeleter implements Deleter{
    PlayerCharacter playerCharacter;

    public SandTrapDeleter( PlayerCharacter playerCharacter ){
        this.playerCharacter = playerCharacter;
    }

    @Override
    public void delete(Defense defense) {
        this.playerCharacter.destroyDefense(defense);
        defense.removeFromYourPlot();
    }
}
