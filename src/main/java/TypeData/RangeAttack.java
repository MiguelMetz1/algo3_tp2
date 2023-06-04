package TypeData;

import Enemies.Enemy;
import Map.Map;

public class RangeAttack {
    int range;
    Coordinate coordinate;
    Map map;

    public RangeAttack(Coordinate coordinate, Map map, int range){
        this.coordinate = coordinate;
        this.map = map;
        this.range = range;
    }
    public Enemy findEnemy(){
        return null;
    };
}
