package edu.fiuba.algo3.Model.Enemies.Loot;

import edu.fiuba.algo3.Model.Enemies.KillableEnemy;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Players.Looter;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Credits.Credits;

import java.util.LinkedList;

public abstract class LooteableEnemy extends KillableEnemy implements Looteable {

    Looteable looteable;

    Credits credits;

    public LooteableEnemy(GameMap map, LinkedList<Coordinate> path ) {
        super(map, path);
        this.credits = new Credits(amountOfCredits());
        this.looteable = new LooteableMOB(this.credits);
    }

    @Override
    public void transferLootTo(Looter player) {
        if( this.isDead() ) {
            this.looteable.transferLootTo(player);
        }
    }

    protected void incrementCreditsIn(double amountOfIncrement ){
        new Credits(amountOfIncrement).transferCreditsTo(this.credits);
    }

    protected abstract double amountOfCredits();


}
