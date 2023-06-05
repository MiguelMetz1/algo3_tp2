package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.CannotBuild;

public interface Buildable {

    boolean canBuild();

    void build(Defense defense) throws CannotBuild;

}
