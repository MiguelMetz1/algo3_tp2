package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate;

public class Rocky extends Plot{

    public Rocky(){
        super(new UnbuildablePlot());
    }

    public String showPlotName(){
        return "Piedra";
    }

}
