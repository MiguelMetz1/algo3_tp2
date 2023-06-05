package edu.fiuba.algo3.TypeData;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.GameMap.GameMap;

public class RangeAttack {
    int range;
    Coordinate coordinate;
    GameMap map;

    public RangeAttack(Coordinate coordinate, GameMap map, int range){
        this.coordinate = coordinate;
        this.map = map;
        this.range = range;
    }
    public Enemy findEnemy(){
        return null;
    };
}
