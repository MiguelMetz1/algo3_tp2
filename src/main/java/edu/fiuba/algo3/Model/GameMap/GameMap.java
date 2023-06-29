package edu.fiuba.algo3.Model.GameMap;

import edu.fiuba.algo3.Model.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Plots.Plot;
import edu.fiuba.algo3.Model.TypeData.Time;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.*;

public class GameMap {
    private HashMap< Coordinate, Plot> map;

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
