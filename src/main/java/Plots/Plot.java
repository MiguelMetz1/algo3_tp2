package Plots;

import Defenses.Defense;
import Exceptions.CannotBuild;
import Plots.Passables.Passable;
import TypeData.Coordinate;

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

}
