package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Plot {
    Coordinate coordinate;

    public Plot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean hasType( String type ){
        return type.equals(this.type());
    }

    public abstract void receive(Defense defense) throws WrongPlace;

    protected  String type(){
        return this.getClass().getName();
    }

    public abstract void remove(Defense defense);
}