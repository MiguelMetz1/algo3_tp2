package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Time;

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
        entity.locateIn(destinationPlace, destinationPlot);
    }

    public String getType(Coordinate coordinate) {
        return this.map.get(coordinate).getName();
    }

    public double getDimension() {
        return Math.pow(this.map.size(), 0.5);
    }

    public void remainingTime(Coordinate coordinate, Time timeOfConstruction) {
        this.map.get(coordinate).defenseTime(timeOfConstruction);
    }
}
