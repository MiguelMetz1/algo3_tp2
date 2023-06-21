package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NullAdvancer implements Advancer {
    @Override
    public void advance() {
        Logger.getLogger("Advancer").log(Level.INFO,"This enemy can't advance.");
    }
}
