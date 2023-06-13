package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

import java.util.ArrayList;

public abstract class Plot {
    Coordinate coordinate;
    ArrayList<String> rightPlaceables;


    public Plot(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.rightPlaceables = rightPlaceables();
    }

    public boolean distanceToBiggerThan(Plot place, Distance distance ){
        return (this.coordinate.distanceTo(place.coordinate).higher(distance));
    }

    public void receive(Placeable placeable) throws IncorrectPlaceable {
        if(!this.rightPlaceables.contains(placeable.getClass().getName())){
            throw new IncorrectPlaceable("this plot cant receive the placeable object.");
        }
    }

    protected abstract ArrayList<String> rightPlaceables();
}