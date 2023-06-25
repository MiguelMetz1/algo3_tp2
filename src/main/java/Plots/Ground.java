package Plots;

import Defenses.Defense;
import Exceptions.CannotBuild;
import TypeData.Coordinate;

public class Ground extends Plot{


    public Ground(Coordinate coordinate){
        super(coordinate, new BuildablePlot());
    }

    public String showPlotName(){
        return "Tierra";
    }

}
