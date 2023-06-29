package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Defenses.Deleter.NullDeleter;
import edu.fiuba.algo3.Defenses.Deleter.SandTrapDeleter;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Time;

public class UnderDestructionSandTrap implements Builder<Deleter> {

    Buff debuff;

    Time timeOfConstruction;

    Coordinate position;

    Distance range;

    Player player;


    public UnderDestructionSandTrap(Time timeOfConstruction, Player player) {
        this.timeOfConstruction = timeOfConstruction;
        this.player = player;
    }

    @Override
    public Deleter actualState() {
        this.timeOfConstruction.reduceIn(1);
        if( this.timeOfConstruction.equals(new Time(0)) || this.timeOfConstruction.lower(new Time(0) ) ){
            return new SandTrapDeleter(player);
        }
        return new NullDeleter();
    }

}
