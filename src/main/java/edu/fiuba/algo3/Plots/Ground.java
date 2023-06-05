package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Ground extends Plot{

    Defense defense;
    public Ground(Coordinate coordinate){
        super(coordinate, new BuildablePlot());
    }

    public String showPlotName(){
        return "Tierra";
    }

}
