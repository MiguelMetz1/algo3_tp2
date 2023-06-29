package edu.fiuba.algo3.Model.Enemies.Interface;

import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Plots.Plot;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

public interface Placeable {
    void locateIn(Coordinate coordinate, Plot plot) throws WrongPlace;



}