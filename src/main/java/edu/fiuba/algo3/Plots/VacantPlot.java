package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VacantPlot implements OccupationState{
    @Override
    public void recieve(Placeable placeable) {
        Logger.getLogger("Placeable").log(Level.INFO, "The plot was vacant. The object will be located here.");
    }
}
