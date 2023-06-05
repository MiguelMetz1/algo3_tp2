package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.Exceptions.DefenseNotBought;
import edu.fiuba.algo3.TypeData.Coordinate;

public interface Placeable {
    void putIn(Defense defense, Coordinate coordinate) throws RuntimeException;
}
