package edu.fiuba.algo3.Model.Defenses.Deleter;

import edu.fiuba.algo3.Model.Defenses.Defense;
import edu.fiuba.algo3.Model.Plots.Plot;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullDeleter implements Deleter {
    @Override
    public void delete(Defense defense, Plot plot) {
        Logger.getLogger("Deleter").log(Level.INFO, "This trap can't be deleted in this moments.");
    }
}
