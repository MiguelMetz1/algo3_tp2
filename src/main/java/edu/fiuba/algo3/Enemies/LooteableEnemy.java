package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Looter;
import edu.fiuba.algo3.TypeData.*;
import java.util.Queue;

public abstract class LooteableEnemy extends TargetableEnemy implements Looteable {

    Looteable looteable;

    Credits credits;

    public LooteableEnemy(GameMap map, Queue<Coordinate> path ) {
        super(map, path);

        this.credits = new Credits(this.amountOfCredits());

        Looteable looteable = new NullLooteable();
        this.looteable = looteable;
    }

    @Override
    public void transferLootTo(Looter player) {
        if( this.isDead() ) {
            this.looteable = new LooteableMOB( this.credits );


        }
        this.looteable.transferLootTo(player);
    }

    protected abstract int amountOfCredits();


}