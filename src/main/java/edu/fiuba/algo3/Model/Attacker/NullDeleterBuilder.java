package edu.fiuba.algo3.Model.Attacker;

import edu.fiuba.algo3.Model.Defenses.Builder.Builder;
import edu.fiuba.algo3.Model.Defenses.Deleter.Deleter;
import edu.fiuba.algo3.Model.Defenses.Deleter.NullDeleter;

public class NullDeleterBuilder implements Builder<Deleter> {
    @Override
    public Deleter actualState() {
        return new NullDeleter();
    }
}
