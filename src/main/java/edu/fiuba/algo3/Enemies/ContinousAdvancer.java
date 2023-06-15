package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Speed;

import java.util.Queue;

public class ContinousAdvancer implements Advancer {

    Speed speedToReach;

    Queue<Coordinate> path;

    Placeable entityToAdvance;

    GameMap map;

    public ContinousAdvancer(GameMap map, Speed speedToReach, Queue<Coordinate> path, Placeable entityToAdvance){
        this.map = map;
        this.speedToReach = speedToReach;
        this.path = path;
        this.entityToAdvance = entityToAdvance;
    }
    @Override
    public void advance() {
        if( !this.path.isEmpty() ) {
            Coordinate nextPosition;
            Speed actualSpeed = new Speed(0);
            while (actualSpeed.lower(this.speedToReach)) {
                nextPosition = this.path.poll();
                try {
                    map.locateEntityIn(entityToAdvance, nextPosition);
                } catch (WrongPlace e) {
                    System.out.println("The entity can't advance to this place.");
                }
                actualSpeed.reduceIn(-1);
            }
        }
    }
}
