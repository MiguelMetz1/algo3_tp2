package edu.fiuba.algo3.Enemies;
import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Attacker.ReadyAttacker;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Looter;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Enemy implements Target, Placeable {

    Plot positionedPlace;

    Energy energy;

    Advancer advancer;

    Inventory inventorier;

    Attacker attacker;

    AttackReceiver attackReceiver;

    ArrayList<String> passablePlots;

    GameMap map;

    public Enemy( GameMap map ) {
        this.map = map;
        this.energy = new Energy( new Life(energy()) );
        this.attackReceiver = new LiveAttackReceiver(this.energy);
        this.advancer = new LiveAdvancer(this.map, new Speed(speed()), new LinkedList<>(), this );
        this.positionedPlace = new NullPlot();
        this.inventorier = new UnlooteableMOB();
        this.attacker = new ReadyAttacker(new Damage(damage()), this.positionedPlace, new Distance(range()));
        this.passablePlots = passablePlots();
    }

    public Enemy( GameMap map, Queue<Coordinate> path ) {
        this.map = map;
        this.energy = new Energy( new Life(energy()) );
        this.attackReceiver = new LiveAttackReceiver(this.energy);
        this.advancer = new LiveAdvancer(this.map, new Speed(speed()), path, this );
        this.positionedPlace = new NullPlot();
        this.inventorier = new UnlooteableMOB();
        this.attacker = new ReadyAttacker(new Damage(damage()), this.positionedPlace, new Distance(range()));
        this.passablePlots = passablePlots();
    }

    public void locateIn( Plot plot ) throws WrongPlace {
        if( !this.isARightPlot(plot) ){
            throw new WrongPlace("The enemy cant walk on this plot.");
        }
        this.positionedPlace = plot;
        this.attacker = new ReadyAttacker(new Damage(damage()), this.positionedPlace, new Distance(range()));
    }

    protected boolean isARightPlot( Plot plot ){
        for ( String rightPlotType : this.passablePlots){
             if( plot.hasType( rightPlotType ) ){
                 return true;
             }
        }
        return false;
    }

    public void takeDamage(Damage damage) {

        this.attackReceiver.takeDamage(damage);
        if( this.isDead() ){
            this.inventorier = new LooteableMOB(new Credits(amountOfCredits()));
            this.advancer = new DeadAdvancer();
            this.attacker = new NullAttacker();
            this.positionedPlace = new HellsPlot();
            this.passablePlots.clear();
            this.attackReceiver = new DeadAttackReceiver();
        }
    }

    public void transferLootTo( Looter player ){
        this.inventorier.transferLootTo(player);
    }

    public boolean distanceToBiggerThan( Plot place, Distance distance ){
        return  this.positionedPlace.distanceToBiggerThan(place, distance);
    }

    //TODO PONER isDead En Private
    protected boolean isDead(){
        Energy deadEntityEnergy = new Energy( new Life(0) );
        return ( !this.energy.higher(deadEntityEnergy) || this.energy.equals(deadEntityEnergy) );
    }


    public void selfDestroy( ArrayList<Enemy> liveEnemies){
        if( this.isDead()) {
            liveEnemies.remove(this);
        }
    }

    protected abstract int amountOfCredits();

    public void advance() {
        this.advancer.advance();
    }

    public void attack( Target player ) {
        this.attacker.attack(player);
    }

    protected abstract int damage();

    protected abstract int energy();

    protected abstract int speed();

    protected ArrayList<String> passablePlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Gangway.class.getName() );
        passablePlots.add(FinalGangway.class.getName());
        passablePlots.add(InitialGangway.class.getName());
        return passablePlots;
    }

    protected int range(){
        return 0;
    }

    public abstract String toString();

    public boolean hasType( String type ){
            return type.equals( this.type() );
    }

    private String type(){
        return this.getClass().getName();
    }

}
