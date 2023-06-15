package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.ReadyAttacker;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.DecrementerDamage;
import edu.fiuba.algo3.TypeData.Distance;
import edu.fiuba.algo3.TypeData.LifeInstantDecrementerDebuff;
import java.util.ArrayList;
import java.util.Queue;

public class Mole extends Enemy{


    private int advances;

    public Mole(GameMap map, Queue<Coordinate> path){
        super(map, path);
        this.attributes.remove(this.energy);
        this.advances = 0;
    }

    public void advance(){
        super.advance();
        advances++;
        if( this.movesThatIncrementSpeed().contains(advances) ){
            this.speed.reduceIn(-1);
        }

    }

    public void attack( Target target){
        if( advances % 2 != 0){
            this.damage = new DecrementerDamage(5);
        }else{
            this.damage = new DecrementerDamage(2);
        }
        if( !this.isDead()) {
            this.attacker = new ReadyAttacker(new LifeInstantDecrementerDebuff(1, damage), this.positionedPlace, new Distance(range()));
        }

        super.attack(target);

    }

    private ArrayList<Integer> movesThatIncrementSpeed(){
        ArrayList<Integer> movesThatIncrementSpeed = new ArrayList<>();
        movesThatIncrementSpeed.add(6);
        movesThatIncrementSpeed.add(11);
        return movesThatIncrementSpeed;
    }

    @Override
    protected int amountOfCredits() {
        return 0;
    }

    @Override
    protected int damage() {
        return 2;
    }

    @Override
    protected int energy() {
        return 1;
    }

    @Override
    protected int speed() {
        return 1;
    }

    @Override
    public String toString() {
        return "Mole";
    }

    protected ArrayList<String> passablePlots(){
        ArrayList<String> passablePlots = super.passablePlots();
        passablePlots.add(Ground.class.getName());
        passablePlots.add(Rocky.class.getName());
        return passablePlots;
    }
}
