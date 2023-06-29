package edu.fiuba.algo3.Model.Enemies.Advancer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullAdvancer implements Advancer {
    @Override
    public void advance() {
        Logger.getLogger("Advancer").log(Level.INFO,"This enemy can't advance.");
    }
}
