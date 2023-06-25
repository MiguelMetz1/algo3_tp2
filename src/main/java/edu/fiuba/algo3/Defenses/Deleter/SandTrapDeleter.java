package edu.fiuba.algo3.Defenses.Deleter;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Players.PlayerCharacter;

public class SandTrapDeleter implements Deleter {
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
