package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Players.Looter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullLooteable implements Looteable {
    @Override
    public void transferLootTo(Looter player) {
        Logger.getLogger("Looteable").log(Level.WARNING, "In this moments this entity can't be looted.");
    }
}
