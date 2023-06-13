package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class NullPlot extends Plot{
    public NullPlot() {
        super(new Coordinate(0,0));
    }

    @Override
    protected ArrayList<String> rightPlaceables() {
        return new ArrayList<>();
    }
}
