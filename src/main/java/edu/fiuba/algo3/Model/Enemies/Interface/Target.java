package edu.fiuba.algo3.Model.Enemies.Interface;

import edu.fiuba.algo3.Model.Enemies.AttackReceiver.AttackReceiver;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

public interface Target extends AttackReceiver {
    boolean distanceToBiggerThan(Coordinate position, Distance attackDistance );
}
