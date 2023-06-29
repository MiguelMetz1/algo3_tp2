package edu.fiuba.algo3.Model.Defenses;
import edu.fiuba.algo3.Model.Defenses.Builder.NullBuilder;
import edu.fiuba.algo3.Model.Enemies.Enemy;
import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Defenses.Builder.UnderConstructionAttacker;
import edu.fiuba.algo3.Model.Defenses.Builder.Builder;
import edu.fiuba.algo3.Model.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Model.Attacker.NullAttacker;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Plots.Ground;
import edu.fiuba.algo3.Model.Plots.NullPlot;
import edu.fiuba.algo3.Model.Plots.Plot;
import edu.fiuba.algo3.Model.TypeData.Time;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Buff.EnergyInstantDecrementerDebuff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Coordinate.HellsCoordinate;
import edu.fiuba.algo3.Model.TypeData.Damage.AdditiveDamage;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Defense implements Placeable {

    protected Attacker attacker;

    protected Builder<Attacker<Enemy>> builder;

    protected Plot positionedPlot;

    protected Coordinate position;

    protected Time timeOfConstruction;

    protected ArrayList< String > rightPlots;

    public Defense() {
        this.builder = new NullBuilder();
        this.positionedPlot = new NullPlot();
        this.attacker = new NullAttacker();
        this.rightPlots = this.rightPlots();
        this.position = new HellsCoordinate();

    }

    public void attack(ArrayList<Enemy> enemies){
        this.attacker.attack(enemies);
    }

    public void continueWithTheConstruction() {
        this.attacker = this.builder.actualState();
    }

    @Override
    public void locateIn( Coordinate position, Plot plot) throws WrongPlace {
        if( !this.isARightPlot(plot) ){
            throw new WrongPlace("The " + this + " cant be located in this plot.");
        }

        plot.receive(this);
        this.positionedPlot = plot;
        this.position.updateTo(position);
        Logger.getLogger("Placeable").log(Level.INFO, "A "+ this +" has been located in " + position.toString());
        this.timeOfConstruction = new Time(timeOfConstruction());
        this.builder = new UnderConstructionAttacker(
                timeOfConstruction,
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

    public void destroyOn( LinkedList<Defense> activeDefenses ) {
        activeDefenses.remove(this);
        this.positionedPlot.remove(this);
    }

    public void findPosition(HashMap<Coordinate, ArrayList<String>> coordinateType) {
        ArrayList<String> types = coordinateType.getOrDefault(this.position, new ArrayList<>());
        types.add(this.toString());
        coordinateType.put(this.position, types);
    }

    public void remainingTime(Time timeOfConstruction) {
        timeOfConstruction.copy(this.timeOfConstruction);
    }
}