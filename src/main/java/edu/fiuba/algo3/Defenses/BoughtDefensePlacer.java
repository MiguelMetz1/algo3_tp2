package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Exceptions.DefenseNotBought;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;

public class BoughtDefensePlacer implements Placeable{

    @Override
    public void putIn(Defense defense, Coordinate coordinate) throws RuntimeException {
        GameMap.getMap().build(defense, coordinate);
    }

}
