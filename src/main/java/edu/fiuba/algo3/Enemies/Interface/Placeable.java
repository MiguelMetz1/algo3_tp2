package edu.fiuba.algo3.Enemies.Interface;

import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;

public interface Placeable {
    void locateIn(Coordinate coordinate, Plot plot) throws WrongPlace;



}