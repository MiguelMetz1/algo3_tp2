package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Defenses.Builder.Builder;
import edu.fiuba.algo3.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Defenses.Deleter.NullDeleter;

public class NullDeleterBuilder implements Builder<Deleter> {
    @Override
    public Deleter actualState() {
        return new NullDeleter();
    }
}
