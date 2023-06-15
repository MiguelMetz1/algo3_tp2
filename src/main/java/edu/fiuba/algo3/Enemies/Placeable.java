package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;

public interface Placeable {
    void locateIn(Plot plot) throws WrongPlace;

}