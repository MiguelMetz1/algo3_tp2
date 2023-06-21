package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

public interface Target extends AttackReceiver {
    boolean distanceToBiggerThan(Coordinate position, Distance attackDistance );
}
