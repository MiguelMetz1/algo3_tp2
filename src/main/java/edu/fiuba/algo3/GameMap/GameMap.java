package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.*;

public class GameMap {
    private HashMap< Coordinate, Plot > map;


    public GameMap(MapJsonParser mapParser) {
        this.map = mapParser.get();
    }

    public void locateEntityIn(Placeable entity, Coordinate destinationPlace ) throws IncorrectPlaceable {
        Plot destinationPlot = map.get(destinationPlace);
        entity.locateIn(destinationPlot);
    }


}
