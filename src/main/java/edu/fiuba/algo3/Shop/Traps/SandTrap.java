package edu.fiuba.algo3.Shop.Traps;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Defenses.Deleter.NullDeleter;
import edu.fiuba.algo3.Defenses.Builder.Builder;
import edu.fiuba.algo3.Defenses.Builder.NullBuilder;
import edu.fiuba.algo3.Defenses.Builder.UnderDestructionSandTrap;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Buff.SpeedScalableBuff;
import edu.fiuba.algo3.TypeData.Buff.SpeedScalableDamage;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

import java.util.ArrayList;

public class SandTrap extends Defense {

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

    public void locateIn(Coordinate position, Plot plot) throws WrongPlace {
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

    @Override
    protected ArrayList<String> rightPlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Gangway.class.getName() );
        return passablePlots;
    }
}
