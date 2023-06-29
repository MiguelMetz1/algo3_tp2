package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

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
