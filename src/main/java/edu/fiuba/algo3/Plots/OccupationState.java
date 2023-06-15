package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;

public interface OccupationState {
    void recieve(Placeable placeable) throws WrongPlace;
}
