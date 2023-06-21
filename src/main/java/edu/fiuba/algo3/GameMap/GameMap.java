package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.*;

public class GameMap {
    private HashMap< Coordinate, Plot > map;

    public GameMap( HashMap<Coordinate, Plot> map ){
        this.map = map;
    }

    public void locateEntityIn(Placeable entity, Coordinate destinationPlace ) throws WrongPlace {
        if( !this.map.containsKey(destinationPlace) ){
            throw new WrongPlace("The place that you are trying to access is not part of this map");
        }
        Plot destinationPlot = map.get(destinationPlace);
        destinationPlot.receive(entity);
        entity.locateIn(destinationPlace, destinationPlot);

    }


}
