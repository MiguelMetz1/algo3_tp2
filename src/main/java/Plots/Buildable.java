package Plots;

import Defenses.Defense;
import Exceptions.CannotBuild;

public interface Buildable {

    boolean canBuild();

    void build(Defense defense) throws CannotBuild;

}
