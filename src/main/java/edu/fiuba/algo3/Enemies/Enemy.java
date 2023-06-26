package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Enemies.Advancer.Advancer;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Enemies.Advancer.LiveAdvancer;
import edu.fiuba.algo3.Enemies.AttackReceiver.AttackReceiver;
import edu.fiuba.algo3.Enemies.AttackReceiver.LiveAttackReceiver;
import edu.fiuba.algo3.Enemies.AttackReceiver.NullAttackReceiver;
import edu.fiuba.algo3.Enemies.Interface.Advanceable;
import edu.fiuba.algo3.Enemies.Advancer.NullAdvancer;
import edu.fiuba.algo3.Enemies.Interface.Target;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Buff.Attribute;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Coordinate.HellsCoordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Speed.Speed;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public abstract class Enemy implements Advanceable, Attacker<Player>, Target {
    Advancer advancer;

    Attacker attacker;

    protected Speed speed;

    protected Coordinate actualPosition;

    Plot positionedPlace;

    ArrayList<String> passablePlots;

    Queue<Coordinate> path;

    AttackReceiver attackReceiver;

    ArrayList<Attribute> attributes;

    GameMap map;

    public Enemy(GameMap map, Queue<Coordinate> path){
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

    public Enemy( ) {
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
        return this.path.isEmpty();
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
            finalWaysEnemies.add(this);
        }
    }

    @Override
    public void takeBuff(Buff buff) {
        AttackReceiver attackReceiver = this.attackReceiver;

        if( !this.isAvailableToReceiveAttack() ) {
            attackReceiver = new NullAttackReceiver();
        }

        attackReceiver.takeBuff(buff);
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

    public void addImage( Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){


            Button button = buttonMap.get(this.actualPosition);
            StackPane stackPane = stackPaneMap.get(this.actualPosition);
            Image overlayImage1 = new Image(enemyImage());
            ImageView overlayImageView1 = new ImageView(overlayImage1);
            overlayImageView1.setFitHeight(20);
            overlayImageView1.setFitWidth(20);

            //System.out.println(stackPane.getChildren().size());

            stackPane.getChildren().addAll(overlayImageView1);
            overlayImageView1.toFront();

            button.setGraphic(stackPane);

            //stackPane.getChildren().remove(0);

    }

    protected abstract String enemyImage();

}
