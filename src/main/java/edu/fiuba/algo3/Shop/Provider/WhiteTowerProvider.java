package edu.fiuba.algo3.Shop.Provider;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public class WhiteTowerProvider implements Provider {
    @Override
    public Defense newArticle() {
        return new WhiteTower();
    }

    @Override
    public Credits articleCost() {
        return new Credits(10);
    }
}
