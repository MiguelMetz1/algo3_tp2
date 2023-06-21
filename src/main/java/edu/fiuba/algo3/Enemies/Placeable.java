package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

public interface Placeable {
    void locateIn(Coordinate coordinate, Plot plot) throws WrongPlace;

}