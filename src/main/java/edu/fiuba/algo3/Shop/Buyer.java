package edu.fiuba.algo3.Shop;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public interface Buyer {
    void wasteCredits(Credits cost) throws InsuficientCredits;
    void addDefense(Defense defense);
}
