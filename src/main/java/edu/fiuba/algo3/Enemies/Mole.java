package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.EnemiesAttacker.LifeAttacker;
import edu.fiuba.algo3.Enemies.Interface.Target;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class Mole extends Enemy implements Target {

    private int advances;

    public Mole(GameMap map, Queue<Coordinate> path){
        super(map, path);
        this.advances = 0;
        this.setAttacker( new LifeAttacker( this.actualPosition, getDamage() ) );
       /* this.addMolePassablePlots();*/
    }

/*    private void addMolePassablePlots(){
        this.addPassablePlot(Rocky.class.getName());
        this.addPassablePlot(Ground.class.getName());
    }*/

    public void advance(){
        super.advance();
        advances++;
        this.incrementSpeed();
        this.changeAttacker();
    }

    protected void changeAttacker(){
        Attacker attacker = new LifeAttacker( this.actualPosition, getDamage() );
        this.setAttacker(attacker);
    }

    private void incrementSpeed(){
        if( this.movesThatIncrementSpeed().contains( advances ) ){
            this.speed.incrementIn(1);
        }
    }

    private ArrayList<Integer> movesThatIncrementSpeed(){
        ArrayList<Integer> movesThatIncrementSpeed = new ArrayList<>();
        movesThatIncrementSpeed.add(6);
        movesThatIncrementSpeed.add(11);
        return movesThatIncrementSpeed;
    }


    protected double getDamage() {
        if( advances % 2 != 0 ){
            return 5;
        }
        return 2;
    }

    @Override
    protected double getSpeed() {
        return 1;
    }

    @Override
    public void takeBuff(Buff buff) {

    }

    @Override
    public boolean distanceToBiggerThan(Coordinate position, Distance attackDistance) {
        return false;
    }

    protected String enemyImage(){return "file:src/main/java/edu/fiuba/algo3/View/Images/moleOnly.png";}

}
