package edu.fiuba.algo3.Model.Shop.Provider;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

public interface Provider {
    Defense newArticle();
    Credits articleCost();
}
