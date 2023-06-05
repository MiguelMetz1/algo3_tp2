package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.TypeData.Coordinate;

public abstract class Plot {
    Coordinate coordinate;
    Buildable buildable;


    public Plot(Coordinate coordinate, Buildable buildable){
        this.coordinate = coordinate;
        this.buildable = buildable;
    }

    public void build(Defense defense) throws CannotBuild {
        this.buildable.build(defense);
    }

    public boolean canBuild(){
        return this.buildable.canBuild();
    }

    public abstract String showPlotName();


}
