package edu.fiuba.algo3.Model.Enemies.Loot;

import edu.fiuba.algo3.Model.Players.Looter;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

public class LooteableMOB implements Looteable {

    Looteable loot;

    public LooteableMOB(Credits credits ){
        this.loot = new AvailableToLootCredits(credits);
    }

    @Override
    public void transferLootTo(Looter player){
        this.loot.transferLootTo( player );
        this.loot = new NullLooteable();
    }
}
