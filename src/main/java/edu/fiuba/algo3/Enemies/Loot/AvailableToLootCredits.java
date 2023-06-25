package edu.fiuba.algo3.Enemies.Loot;

import edu.fiuba.algo3.Players.Looter;
import edu.fiuba.algo3.TypeData.Credits.Credits;

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
