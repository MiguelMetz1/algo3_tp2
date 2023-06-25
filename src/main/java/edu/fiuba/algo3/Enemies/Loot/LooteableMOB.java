package edu.fiuba.algo3.Enemies.Loot;

import edu.fiuba.algo3.Players.Looter;
import edu.fiuba.algo3.TypeData.Credits.Credits;

public class LooteableMOB implements Looteable {

    Looteable loot;

    public LooteableMOB(Credits credits ){
        this.loot = new AvailableToLootCredits(credits);
    }

    @Override
    public void transferLootTo(Looter player){
        this.loot.transferLootTo( player );
        this.loot = new LootedMOB();
    }
}
