package edu.fiuba.algo3.Model.Enemies.Advancer;

import edu.fiuba.algo3.Model.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstEnemyAdvancer implements Advancer {

    Coordinate firstAdvanceCoordinate;

    Placeable entityToAdvance;

    GameMap map;

    Coordinate actualPosition;


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
        } catch (WrongPlace e) {
            Logger.getLogger("Advancer").log(Level.INFO, "The entity can't advance to this place.");
        }
    }
}
