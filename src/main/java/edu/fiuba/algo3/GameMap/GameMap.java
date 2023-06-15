package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.*;

public class GameMap {
    private HashMap< Coordinate, Plot > map;


    public GameMap() {
        ExternalResources resources = new ExternalResources(this);
        this.map = resources.getMap();
    }

    public void locateEntityIn(Placeable entity, Coordinate destinationPlace ) throws WrongPlace {
        Plot destinationPlot = map.get(destinationPlace);
        entity.locateIn(destinationPlot);
        destinationPlot.receive(entity);
    }


}
