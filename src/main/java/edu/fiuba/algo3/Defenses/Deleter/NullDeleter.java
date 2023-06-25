package edu.fiuba.algo3.Defenses.Deleter;

import edu.fiuba.algo3.Defenses.Defense;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullDeleter implements Deleter {
    @Override
    public void delete(Defense defense) {
        Logger.getLogger("Deleter").log(Level.INFO, "This trap can't be deleted in this moments.");
    }
}
