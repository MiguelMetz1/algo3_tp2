package edu.fiuba.algo3.classesTests;


import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameMapTest {


    @Test
    public void gameMapCantLocateEntitiesOutsideTheBounds() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        assertThrows(WrongPlace.class, () ->map.locateEntityIn(new WhiteTower(), new Coordinate(-3, -3)) );


    }
}
