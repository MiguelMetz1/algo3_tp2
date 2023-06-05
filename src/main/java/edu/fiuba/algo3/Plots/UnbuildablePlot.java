package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Exceptions.CannotBuild;

public class UnbuildablePlot implements Buildable{

    public boolean canBuild(){
        return false;
    }

    public void build(Defense defense) throws CannotBuild {
        throw (new CannotBuild("Can't build in this plot."));
    }
}
