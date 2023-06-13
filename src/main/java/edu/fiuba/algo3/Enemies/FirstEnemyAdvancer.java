package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.Queue;

public class FirstEnemyAdvancer implements Advancer {

    Queue<Coordinate> path;

    Placeable entityToAdvance;

    GameMap map;

    public FirstEnemyAdvancer(GameMap map, Queue<Coordinate> path, Placeable entityToAdvance){
        this.map = map;
        this.path = path;
        this.entityToAdvance = entityToAdvance;
    }

    @Override
    public void advance() {
        if( !this.path.isEmpty() ) {
            Coordinate nextPosition = this.path.poll();
            try {
                this.map.locateEntityIn(entityToAdvance, nextPosition);
            } catch (IncorrectPlaceable e) {
                System.out.println("The entity can't advance to this place.");
            }
        }
    }
}
