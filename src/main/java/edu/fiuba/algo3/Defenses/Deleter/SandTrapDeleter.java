package edu.fiuba.algo3.Defenses.Deleter;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Players.Player;

public class SandTrapDeleter implements Deleter {
    Player player;

    public SandTrapDeleter( Player player){
        this.player = player;
    }

    @Override
    public void delete(Defense defense) {
        this.player.destroyDefense(defense);
        defense.removeFromYourPlot();
    }
}
