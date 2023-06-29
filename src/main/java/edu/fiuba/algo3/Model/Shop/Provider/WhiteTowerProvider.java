package edu.fiuba.algo3.Model.Shop.Provider;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

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
