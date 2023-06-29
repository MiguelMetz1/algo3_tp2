package edu.fiuba.algo3.Model.Shop.Provider;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

public class SilverTowerProvider implements Provider {
    @Override
    public Defense newArticle() {
        return new SilverTower();
    }
    @Override
    public Credits articleCost() {
        return new Credits(20);
    }
}
