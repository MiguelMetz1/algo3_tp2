package edu.fiuba.algo3.Enemies.Loot;

import edu.fiuba.algo3.Players.Looter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NullLooteable implements Looteable {
    @Override
    public void transferLootTo(Looter player) {
        Logger.getLogger("Looteable").log(Level.WARNING, "This entity can't be looted.");
    }
}
