package edu.fiuba.algo3.Model.Shop;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

public interface Buyer {
    void wasteCredits(Credits cost) throws InsufficientCredits;
    void giveDefense(Defense defense);
}
