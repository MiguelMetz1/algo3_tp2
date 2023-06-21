package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Players.Looter;
import edu.fiuba.algo3.TypeData.Credits;

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
