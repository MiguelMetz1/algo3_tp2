package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

public class HellsPlot extends Plot{

    private NullPlot nullPlot;

    public HellsPlot(){
        super(new Coordinate(-666,-666));
        nullPlot = new NullPlot();
    }

    @Override
    public void receive(Defense defense){
        nullPlot.receive(defense);
    }

    @Override
    public void remove(Defense defense) {
        nullPlot.remove(defense);
    }

    public String getName(){
        return "Hell";
    }

}
