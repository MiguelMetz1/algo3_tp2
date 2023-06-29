package edu.fiuba.algo3.Model.Plots;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullPlot extends Plot{
    public NullPlot() {
        super(new Coordinate(0,0));
    }

    public void receive(Defense defense){
        Logger.getLogger("Placeable").log(Level.WARNING, "You can't place anything on this plot.");
    }

    @Override
    public void remove(Defense defense) {
        Logger.getLogger("Placeable").log(Level.INFO, "In this plot nothing can be received, then nothing can be remove");
    }

    public String getName(){
        return "null";
    }

}
