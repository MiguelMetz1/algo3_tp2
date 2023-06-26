package edu.fiuba.algo3.Shop.Provider;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.Traps.SandTrap;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public class SandTrapProvider implements Provider {

    Player player;

    public SandTrapProvider(Player player) {
        this.player = player;
    }

    @Override
    public Defense newArticle() {
        return new SandTrap(this.player);
    }

    @Override
    public Credits articleCost() {
        return new Credits(25);
    }
}