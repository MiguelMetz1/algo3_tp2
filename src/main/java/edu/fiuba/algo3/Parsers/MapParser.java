package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.HashMap;

public interface MapParser {
    HashMap<Coordinate, Plot > getMap();
}
