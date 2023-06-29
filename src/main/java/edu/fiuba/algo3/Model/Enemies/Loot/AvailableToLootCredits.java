package edu.fiuba.algo3.Model.Enemies.Loot;

import edu.fiuba.algo3.Model.Players.Looter;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

public class AvailableToLootCredits implements Looteable {

    Credits creditsToGive;

    public AvailableToLootCredits(Credits credits ){
        this.creditsToGive = credits;
    }
    @Override
    public void transferLootTo( Looter player ) {
        player.transferCredits(creditsToGive);
    }
}
