package edu.fiuba.algo3.Model.Enemies;

import edu.fiuba.algo3.Model.Enemies.Advancer.Advancer;
import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Attacker.NullAttacker;
import edu.fiuba.algo3.Model.Enemies.Advancer.LiveAdvancer;
import edu.fiuba.algo3.Model.Enemies.AttackReceiver.AttackReceiver;
import edu.fiuba.algo3.Model.Enemies.AttackReceiver.LiveAttackReceiver;
import edu.fiuba.algo3.Model.Enemies.AttackReceiver.NullAttackReceiver;
import edu.fiuba.algo3.Model.Enemies.Interface.Advanceable;
import edu.fiuba.algo3.Model.Enemies.Advancer.NullAdvancer;
import edu.fiuba.algo3.Model.Enemies.Interface.Target;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.Plots.*;
import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Coordinate.HellsCoordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;
import edu.fiuba.algo3.Model.TypeData.Speed.Speed;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Enemy implements Advanceable, Attacker<Player>, Target {
    Advancer advancer;

    Attacker attacker;

    protected Speed speed;

    protected Coordinate actualPosition;

    Plot positionedPlace;

    ArrayList<String> passablePlots;

    LinkedList<Coordinate> path;

    AttackReceiver attackReceiver;

    ArrayList<Attribute> attributes;

    GameMap map;

    public Enemy(GameMap map, LinkedList<Coordinate> path){
        this.speed = new Speed( this.getSpeed() );
        this.actualPosition = new HellsCoordinate();
        this.path = path;
        this.map = map;
        this.advancer = new LiveAdvancer( map, this.speed, this.actualPosition, path, this);
        this.attacker = new NullAttacker();
        this.positionedPlace = new NullPlot();
        this.passablePlots = this.passablePlots();
        this.changeAttackReceiver();
    }


    protected ArrayList<Attribute> getBuffeablesAttributes(){
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(this.speed);
        return  attributes;
    }

    protected void changeAttackReceiver( ){
        this.attributes = this.getBuffeablesAttributes();
        this.setAttackReceiver( new LiveAttackReceiver(this.attributes) );
    }

    protected void setAttackReceiver( AttackReceiver attackReceiver){
        this.attackReceiver = attackReceiver;
    }

    public  void attack( ArrayList<Player> targets ){

        Attacker attacker = this.attacker;
        if( !this.reachedTheFinal() ) {
            attacker = new NullAttacker();
        }

        attacker.attack(targets);
    }

    protected boolean isBeforeTheStartPosition(){
        return this.actualPositionIs( new HellsCoordinate() );
    }

    public  void advance(){

        Advancer advancer = this.advancer;

        if( this.reachedTheFinal() ){

            advancer = new NullAdvancer();

            this.positionedPlace = new HellsPlot();

        }
        advancer.advance();
        this.speed.quitBuffs();
    }

    protected boolean reachedTheFinal(){
        return this.path.isEmpty() || this.actualPosition.equals(path.getLast());
    }

    protected boolean actualPositionIs( Coordinate coordinate ){
        return this.actualPosition.equals( coordinate );
    }

    protected void setAttacker(Attacker attacker){
        this.attacker = attacker;
    }

    protected void setAdvancer( Advancer advancer ){
        this.advancer = advancer;
    }

    @Override
    public void locateIn( Coordinate actualPosition, Plot plot ) throws WrongPlace {
        if( !this.isARightPlot(plot) ){
            throw new WrongPlace("The enemy can't walk on this plot.");
        }
        this.actualPosition.updateTo(actualPosition);
        Logger.getLogger("Advancer").log(Level.INFO, "A "  + this.getType() + " has advanced to " + actualPosition);
        this.positionedPlace = plot;
    }

    protected boolean isARightPlot( Plot plot ){
        for ( String rightPlotType : this.passablePlots){
            if( plot.hasType( rightPlotType ) ){
                return true;
            }
        }
        return false;
    }

    protected ArrayList<String> passablePlots(){
        ArrayList<String> passablePlots = new ArrayList<>();
        passablePlots.add( Gangway.class.getName() );
        passablePlots.add( FinalGangway.class.getName() );
        passablePlots.add( InitialGangway.class.getName() );
        return passablePlots;
    }

    public void finalizeYourWay(ArrayList<Enemy> finalWaysEnemies ){
        if( this.reachedTheFinal() ){
            Logger.getLogger("Enemies").log(Level.INFO, "A "+ this.getType() +" in " + actualPosition + " has reached the goal.");
            finalWaysEnemies.add(this);
        }
    }

    @Override
    public void takeBuff(Buff buff) {
        AttackReceiver attackReceiver = this.attackReceiver;
        if( !this.isAvailableToReceiveAttack() ) {
            attackReceiver = new NullAttackReceiver();
        }else{
            Logger.getLogger("AttackReceiver").log(Level.INFO, "A " + this.getType() + " in " + this.actualPosition.toString() + " has received an attack.");
        }

        attackReceiver.takeBuff(buff);
    }

    public static Enemy returnOneLifeDamageableBetween( ArrayList<Enemy> enemies ){

        for( Enemy enemy: enemies){
            if(enemy.isLifeDamageable())
                return enemy;
        }

        return new NullEnemy(enemies.get(0).map);
    }

    protected  boolean isLifeDamageable(){
        return true;
    }

    protected boolean isAvailableToReceiveAttack(){
        return !this.isBeforeTheStartPosition();
    }

    public boolean distanceToBiggerThan(Coordinate position, Distance attackDistance ){
        if( this.isBeforeTheStartPosition() )
            return true;
        return this.actualPosition.distanceTo(position).higher(attackDistance);
    }

    protected void addPassablePlot( String passablePlot ){
        this.passablePlots.add(passablePlot);
    }

    protected abstract double getSpeed();

    public void inPosition(Coordinate coordinate, ArrayList<String> enemiesList) {
        if(this.actualPositionIs(coordinate))
            enemiesList.add(this.getType());
    }

    protected abstract String getType();


    public void findPosition(HashMap<Coordinate, ArrayList<String>> coordinateType) {
        ArrayList<String> types = coordinateType.getOrDefault(this.actualPosition, new ArrayList<>());
        types.add(this.getType());
        coordinateType.put(this.actualPosition, types);
    }

}
