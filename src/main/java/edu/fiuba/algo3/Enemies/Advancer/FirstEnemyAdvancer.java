package edu.fiuba.algo3.Enemies.Advancer;

import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstEnemyAdvancer implements Advancer {

    Coordinate firstAdvanceCoordinate;

    Placeable entityToAdvance;

    GameMap map;

    Coordinate actualPosition;

    public FirstEnemyAdvancer(GameMap map, Coordinate firstStep, Placeable entityToAdvance){
        this.map = map;
        this.firstAdvanceCoordinate = firstStep;
        this.entityToAdvance = entityToAdvance;
    }

    public FirstEnemyAdvancer(GameMap map, Coordinate actualPosition, Coordinate firstAdvanceCoordinate, Placeable entityToAdvance){
        this.map = map;
        this.firstAdvanceCoordinate = firstAdvanceCoordinate;
        this.entityToAdvance = entityToAdvance;
        this.actualPosition = actualPosition;
    }

    @Override
    public void advance() {
        try {
            this.map.locateEntityIn(entityToAdvance, firstAdvanceCoordinate);
            actualPosition.updateTo(firstAdvanceCoordinate);
        } catch (WrongPlace e) {
            Logger.getLogger("Advancer").log(Level.INFO, "The entity can't advance to this place.");
        }
    }
}
