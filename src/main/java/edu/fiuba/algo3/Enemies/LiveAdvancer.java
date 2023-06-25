package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Speed;

import java.util.Queue;

public class LiveAdvancer implements Advancer {

    Speed speedToReach;

    Queue<Coordinate> path;

    Placeable entityToAdvance;

    Coordinate actualPosition;

    Advancer advancer;

    GameMap map;

    public LiveAdvancer(GameMap map, Speed speedToReach, Coordinate actualPosition, Queue<Coordinate> path, Placeable entityToAdvance){
        this.map = map;
        this.speedToReach = speedToReach;
        this.path = path;
        this.entityToAdvance = entityToAdvance;
        this.actualPosition = actualPosition;
        Coordinate firstAdvancePoint = path.poll();
        this.advancer = new FirstEnemyAdvancer(this.map, this.actualPosition, firstAdvancePoint, this.entityToAdvance);
    }
    @Override
    public void advance() {
        this.advancer.advance();
        this.advancer = new ContinousAdvancer(map, speedToReach, this.actualPosition, path, entityToAdvance);
    }
}