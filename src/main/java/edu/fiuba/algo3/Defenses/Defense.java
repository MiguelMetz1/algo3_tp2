package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Defenses.States.NullAttackerBuilder;
import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Defenses.States.UnderConstructionAttacker;
import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Plots.NullPlot;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.*;


public abstract class Defense implements Placeable {

    protected Attacker attacker;
    protected Builder<Attacker> builder;
    protected Plot position;

    public Defense() {
        this.builder = new NullAttackerBuilder();
        this.position = new NullPlot();
        this.attacker = new NullAttacker();
    }

    public void attack(Target enemy){
        this.attacker.attack(enemy);
    }

    public void continueWithTheConstruction() {
        this.attacker = this.builder.actualState();
    }

    @Override
    public void locateIn(Plot plot) throws IncorrectPlaceable {

        plot.receive(this);
        this.position = plot;
        this.builder = new UnderConstructionAttacker(
                timeOfConstruction(),
                new Damage(damage()),
                plot,
                new Distance(range())
        );

    }

    protected abstract int range();

    protected abstract int timeOfConstruction();

    protected abstract int damage();

    public abstract String toString();

}