package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Defenses.States.NullAttackerBuilder;
import edu.fiuba.algo3.Enemies.TargetableEnemy;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Defenses.States.UnderConstructionAttacker;
import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.*;

import java.util.ArrayList;


public abstract class Defense implements Placeable {

    protected Attacker attacker;

    protected Builder<Attacker> builder;

    protected Plot positionedPlot;

    protected Coordinate position;

    protected ArrayList< String > rightPlots;

    public Defense() {
        this.builder = new NullAttackerBuilder();
        this.positionedPlot = new NullPlot();
        this.attacker = new NullAttacker();
        this.rightPlots = this.rightPlots();
        this.position = new HellsCoordinate();
    }


    public void attack(ArrayList<TargetableEnemy> enemies){
        this.attacker.attack(enemies);
    }

    public void continueWithTheConstruction() {
        this.attacker = this.builder.actualState();
    }

    @Override
    public void locateIn( Coordinate position, Plot plot) throws WrongPlace {
        if( !this.isARightPlot(plot) ){
            throw new WrongPlace("The defense cant be located in this plot.");
        }
        this.position.updateTo(position);
        this.positionedPlot = plot;

        this.builder = new UnderConstructionAttacker(
                timeOfConstruction(),
                this.getBuff(),
                this.position,
                new Distance(range())
        );

        this.rightPlots.clear();

    }

    protected Buff getBuff(){
        return new EnergyInstantDecrementerDebuff(new AdditiveDamage(damage()));
    }

    protected boolean isARightPlot( Plot plot ){
        for ( String rightPlotType : this.rightPlots ){
            if( plot.hasType( rightPlotType ) ){
                return true;
            }
        }
        return false;
    }

    protected abstract int range();

    protected abstract int timeOfConstruction();

    protected abstract int damage();

    public abstract String toString();

    protected ArrayList<String> rightPlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Ground.class.getName() );
        return passablePlots;
    }

}