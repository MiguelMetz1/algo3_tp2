package edu.fiuba.algo3.Shop;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.TypeData.Credits;

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
