package edu.fiuba.algo3.Enemies;


import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.*;

import java.util.ArrayList;
import java.util.Queue;

public class Ant extends LooteableEnemy {

    private static ArrayList<Ant> deadAnts = new ArrayList<>();

    public Ant(GameMap map, Queue<Coordinate> path) {
        super(map, path);
        this.setAttacker( new LifeAttacker( this.actualPosition, getDamage() ) );

    }

    public void takeBuff( Buff buff ){
        super.takeBuff(buff);
        if( this.isDead() ) {
            deadAnts.remove(this);
            deadAnts.add(this);
        }
    }

    protected double getSpeed() {
        return 1;
    }

    protected double getDamage() {
        return 1;
    }

    protected double getLife(){
        return 1;
    }

    protected int amountOfCredits(){
        if ( deadAnts.size() > 10 ){
            return 2;
        }

        return 1;
    }
}
