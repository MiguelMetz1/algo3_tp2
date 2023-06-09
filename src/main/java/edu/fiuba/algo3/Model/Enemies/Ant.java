package edu.fiuba.algo3.Model.Enemies;


import edu.fiuba.algo3.Model.Attacker.EnemiesAttacker.LifeAttacker;
import edu.fiuba.algo3.Model.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Players.Looter;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ant extends LooteableEnemy {

    private ArrayList<Ant> deadAnts;

    public Ant(GameMap map, LinkedList<Coordinate> path, ArrayList<Ant> deadAnts) {
        super(map, path);
        this.deadAnts = deadAnts;
        this.setAttacker( new LifeAttacker( this.actualPosition, getDamage() ) );

    }

    public void takeBuff( Buff buff ){
        super.takeBuff(buff);
        if( this.isDead() ) {
            deadAnts.remove(this);
            deadAnts.add(this);
        }
    }

    @Override
    public void transferLootTo(Looter player) {
        if( deadAnts.size() >= 10 ) {
            this.incrementCreditsIn(1);
        }
        super.transferLootTo(player);
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

    protected double amountOfCredits(){
        return 1;
    }

    protected String getType(){
        return "Ant";
    }
}
