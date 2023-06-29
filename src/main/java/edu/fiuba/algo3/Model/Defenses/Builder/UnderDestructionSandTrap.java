package edu.fiuba.algo3.Model.Defenses.Builder;

import edu.fiuba.algo3.Model.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Model.Defenses.Deleter.NullDeleter;
import edu.fiuba.algo3.Model.Defenses.Deleter.SandTrapDeleter;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.TypeData.Time;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnderDestructionSandTrap implements Builder<Deleter> {

    Time timeOfConstruction;

    Player player;


    public UnderDestructionSandTrap(Time timeOfConstruction, Player player) {
        this.timeOfConstruction = timeOfConstruction;
        this.player = player;
    }

    @Override
    public Deleter actualState() {
        this.timeOfConstruction.reduceIn(1);
        Logger.getLogger("Builder").log(Level.INFO, "A trap has increasing the time of destruction in 1.");
        if( this.timeOfConstruction.equals(new Time(0)) || this.timeOfConstruction.lower(new Time(0) ) ){
            Logger.getLogger("Builder").log(Level.INFO, "A trap has been deleted.");
            return new SandTrapDeleter(player);
        }
        return new NullDeleter();
    }

}
