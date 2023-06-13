package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.*;

import java.util.Queue;


public class Ant extends Enemy {

    private static int antsThatDied;

    public Ant(GameMap map){
        super(map);
        this.antsThatDied = 0;
    }

    public Ant(GameMap map, Queue<Coordinate> path){
        super(map, path);
        this.antsThatDied = 0;
    }

    public void takeDamage(Damage damage){
        super.takeDamage(damage);
        if ( this.isDead() ){
            antsThatDied += 1;
        }
    }

    protected int amountOfCredits(){
        if( antsThatDied > 10 ){
            return 2;
        }
        return 1;
    }

    @Override
    protected int damage() {
        return 1;
    }

    @Override
    protected int energy() {
        return 1;
    }

    @Override
    protected int speed() {
        return 1;
    }

    public String toString(){
        return "Ant";
    }
}
