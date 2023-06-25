package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.image.Image;

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

    public abstract Image getImage();

    public abstract String getName();
}