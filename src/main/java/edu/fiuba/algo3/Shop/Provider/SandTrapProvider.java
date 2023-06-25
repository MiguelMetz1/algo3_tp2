package edu.fiuba.algo3.Shop.Provider;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Shop.Traps.SandTrap;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public class SandTrapProvider implements Provider {

    PlayerCharacter playerCharacter;

    public SandTrapProvider(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    @Override
    public Defense newArticle() {
        return new SandTrap(this.playerCharacter);
    }

    @Override
    public Credits articleCost() {
        return new Credits(25);
    }
}