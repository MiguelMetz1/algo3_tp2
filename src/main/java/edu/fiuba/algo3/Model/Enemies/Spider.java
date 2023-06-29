package edu.fiuba.algo3.Model.Enemies;

import edu.fiuba.algo3.Model.Attacker.EnemiesAttacker.LifeAttacker;
import edu.fiuba.algo3.Model.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.LinkedList;

public class Spider extends LooteableEnemy {

    public Spider(GameMap map, LinkedList<Coordinate> path) {
        super(map, path);
        this.setAttacker( new LifeAttacker( this.actualPosition, getDamage() ) );
    }


    protected double getDamage() {
        return 2;
    }

    @Override
    protected double getLife() {
        return 2;
    }

    @Override
    protected double getSpeed() {
        return 2;
    }

    @Override
    protected double amountOfCredits() {
        int random = (int) Math.round(Math.random());
        return 10*(random);
    }

    protected String getType(){
        return "Spider";
    }

}
