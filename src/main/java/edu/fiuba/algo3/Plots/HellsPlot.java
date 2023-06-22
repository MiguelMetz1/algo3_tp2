package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HellsPlot extends Plot{

    private NullPlot nullPlot;

    public HellsPlot(){
        super(new Coordinate(-666,-666));
        nullPlot = new NullPlot();
    }

    @Override
    public void receive(Defense defense) throws WrongPlace {
        nullPlot.receive(defense);
    }

    @Override
    public void remove(Defense defense) {
        nullPlot.remove(defense);
    }
}
