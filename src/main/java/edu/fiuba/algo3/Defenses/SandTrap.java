package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Defenses.States.NullBuilder;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.*;

public class SandTrap extends Defense{

    private Builder<Deleter> deleterBuilder;

    private Deleter deleter;

    private PlayerCharacter playerCharacter;


    public SandTrap( PlayerCharacter playerCharacter ){
        this.playerCharacter = playerCharacter;
        this.deleter = new NullDeleter();
        this.deleterBuilder = new NullBuilder();
    }

    @Override
    protected int range() {
        return 0;
    }

    @Override
    protected int timeOfConstruction() {
        return 1;
    }

    @Override
    protected int damage() {
        return 2;
    }

    public void locateIn( Coordinate position, Plot plot) throws WrongPlace {
        super.locateIn(position, plot);
        this.deleterBuilder = new UnderDestructionSandTrap( 3, this.playerCharacter );
    }

    public void continueWithTheConstruction() {
        this.deleter = this.deleterBuilder.actualState();
        this.deleter.delete(this);
    }

    protected Buff getBuff(){
        return new SpeedScalableBuff(1, new SpeedScalableDamage(damage()));
    }

    @Override
    public String toString() {
        return "SandTrap";
    }
}
