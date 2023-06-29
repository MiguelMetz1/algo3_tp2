package edu.fiuba.algo3.Model.Plots;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.TypeData.Time;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

public abstract class Plot {
    Coordinate coordinate;

    public Plot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean hasType( String type ){
        return type.equals(this.type());
    }

    public abstract void receive(Defense defense) throws WrongPlace;

    protected String type(){
        return this.getClass().getName();
    }

    public abstract void remove(Defense defense);

    public abstract String getName();

    public void defenseTime(Time timeOfConstruction) {
        timeOfConstruction.copy(new Time(0));
    }
}