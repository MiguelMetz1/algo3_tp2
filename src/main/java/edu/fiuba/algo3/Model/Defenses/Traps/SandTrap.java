package edu.fiuba.algo3.Model.Defenses.Traps;

import edu.fiuba.algo3.Model.Attacker.DefenseAttacker.TrapAttacker;
import edu.fiuba.algo3.Model.Attacker.NullDeleterBuilder;
import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Model.Defenses.Deleter.NullDeleter;
import edu.fiuba.algo3.Model.Defenses.Builder.Builder;
import edu.fiuba.algo3.Model.Defenses.Builder.UnderDestructionSandTrap;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.Plots.Gangway;
import edu.fiuba.algo3.Model.Plots.Plot;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Buff.SpeedScalableBuff;
import edu.fiuba.algo3.Model.TypeData.Buff.SpeedScalableDamage;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;
import edu.fiuba.algo3.Model.TypeData.Time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SandTrap extends Defense {

    private Builder<Deleter> deleterBuilder;

    private Deleter deleter;

    private Player player;

    public SandTrap( Player player){
        this.player = player;
        this.deleter = new NullDeleter();
        this.deleterBuilder = new NullDeleterBuilder();
        this.attacker = new TrapAttacker(this.getBuff(), this.position, new Distance(this.range()));
    }

    @Override
    protected int range() {
        return 0;
    }

    @Override
    protected int timeOfConstruction() {
        return 0;
    }

    @Override
    protected int damage() {
        return 2;
    }

    public void locateIn(Coordinate position, Plot plot) throws WrongPlace {
        super.locateIn(position, plot);
        this.timeOfConstruction = new Time(timeOfConstruction());
        this.deleterBuilder = new UnderDestructionSandTrap( new Time(3), this.player );
    }

    public void remainingTime(Time timeOfConstruction) {
        timeOfConstruction.copy(new Time(3));
    }

    public void continueWithTheConstruction() {
        this.deleter = this.deleterBuilder.actualState();
        this.deleter.delete(this, this.positionedPlot);
    }

    protected Buff getBuff(){
        return new SpeedScalableBuff(1, new SpeedScalableDamage(damage()));
    }

    @Override
    protected ArrayList<String> rightPlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Gangway.class.getName() );
        return passablePlots;
    }

    public void destroyOn(LinkedList<Defense> activeDefenses ) {
        Logger.getLogger("AttackReceiver").log(Level.INFO, "A owl try to destroy a  sand trap, it fails..");
    }

    @Override
    public String toString() {
        return "SandTrap";
    }

}
