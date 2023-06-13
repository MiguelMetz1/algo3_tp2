package edu.fiuba.algo3.Shop;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.TypeData.Credits;

public interface Provider {
    Defense newArticle();
    Credits articleCost();
}
