package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

public abstract class Plot {
    Coordinate coordinate;

    public Plot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean distanceToBiggerThan(Plot place, Distance distance ){
        return (this.coordinate.distanceTo(place.coordinate).higher(distance));
    }

    public boolean hasType( String type ){
        return type.equals(this.type());
    }

    public void receive(Placeable placeable) throws WrongPlace {
       //Replace with log
        System.out.println("The entity was located in this plot.");
    }

    protected  String type(){
        return this.getClass().getName();
    }
}