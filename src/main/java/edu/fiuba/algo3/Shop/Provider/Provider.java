package edu.fiuba.algo3.Shop.Provider;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public interface Provider {
    Defense newArticle();
    Credits articleCost();
}
