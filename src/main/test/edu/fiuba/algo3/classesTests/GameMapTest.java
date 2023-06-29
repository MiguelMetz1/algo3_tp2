package edu.fiuba.algo3.classesTests;


import edu.fiuba.algo3.Model.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Parsers.ExternalResources;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
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
