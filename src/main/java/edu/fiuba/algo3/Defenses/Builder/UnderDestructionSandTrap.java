package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Defenses.Deleter.NullDeleter;
import edu.fiuba.algo3.Defenses.Deleter.SandTrapDeleter;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

public class UnderDestructionSandTrap implements Builder<Deleter> {

    Buff debuff;

    int timeOfConstruction;

    Coordinate position;

    Distance range;

    Player player;

    public UnderDestructionSandTrap(int timeOfConstruction, Player player) {
        this.timeOfConstruction = timeOfConstruction;
        this.player = player;
    }

    @Override
    public Deleter actualState() {
        this.timeOfConstruction--;
        if( this.timeOfConstruction <= 0 ){
            return new SandTrapDeleter(player);
        }
        return new NullDeleter();
    }

}
