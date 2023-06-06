package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Ground extends Plot{
    public Ground(){
        super(new BuildablePlot());
    }

    public String showPlotName(){
        return "Tierra";
    }



}
