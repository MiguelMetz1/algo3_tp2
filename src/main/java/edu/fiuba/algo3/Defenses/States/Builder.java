package edu.fiuba.algo3.Defenses.States;
import edu.fiuba.algo3.Exceptions.CannotConstruction;

public interface Builder {

    Builder nextBuild() throws CannotConstruction;
    boolean buildFinished();
}

