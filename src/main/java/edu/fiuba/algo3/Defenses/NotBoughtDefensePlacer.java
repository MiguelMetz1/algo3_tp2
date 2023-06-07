package edu.fiuba.algo3.Defenses;

import edu.fiuba.algo3.Exceptions.DefenseNotBought;
import edu.fiuba.algo3.TypeData.Coordinate;

public class NotBoughtDefensePlacer implements Placeable {
    @Override
    public void putIn(Defense defense, Coordinate coordinate) throws RuntimeException {

        throw new DefenseNotBought("The defense must be bought to place.");
    }
}
