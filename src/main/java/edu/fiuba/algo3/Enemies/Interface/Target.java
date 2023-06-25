package edu.fiuba.algo3.Enemies.Interface;

import edu.fiuba.algo3.Enemies.AttackReceiver.AttackReceiver;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;

public interface Target extends AttackReceiver {
    boolean distanceToBiggerThan(Coordinate position, Distance attackDistance );
}
