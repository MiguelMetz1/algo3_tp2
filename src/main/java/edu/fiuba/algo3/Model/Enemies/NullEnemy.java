package edu.fiuba.algo3.Model.Enemies;

import edu.fiuba.algo3.Model.GameMap.GameMap;

import java.util.LinkedList;

public class NullEnemy extends Enemy{
    public NullEnemy(GameMap map) {
        super(map, new LinkedList<>());
    }

    @Override
    protected double getSpeed() {
        return 0;
    }

    @Override
    protected String getType() {
        return "Null Enemy";
    }

}
