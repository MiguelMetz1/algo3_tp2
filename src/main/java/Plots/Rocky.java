package Plots;

import Defenses.Defense;
import Exceptions.CannotBuild;
import TypeData.Coordinate;

public class Rocky extends Plot{

    public Rocky(Coordinate coordinate){
        super(coordinate, new UnbuildablePlot());
    }

}
