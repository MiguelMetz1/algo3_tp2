package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;

public class OccupiedPlot implements OccupationState{
    @Override
    public void recieve(Placeable placeable) throws WrongPlace {
        throw new WrongPlace("The plot was occupied, you can't put anything here");
    }
}
