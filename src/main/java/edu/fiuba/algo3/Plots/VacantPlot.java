package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Placeable;

public class VacantPlot implements OccupationState{
    @Override
    public void recieve(Placeable placeable) {
        //Replace with logg
        System.out.println("The plot was vacant. The object will be located here.");
    }
}
