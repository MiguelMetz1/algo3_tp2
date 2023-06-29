package edu.fiuba.algo3.Model.Defenses.Deleter;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.Plots.Plot;

public class SandTrapDeleter implements Deleter {
    Player player;

    public SandTrapDeleter( Player player){
        this.player = player;
    }

    @Override
    public void delete(Defense defense, Plot plot) {
        this.player.destroyDefense(defense);
        plot.remove(defense);
    }

}
