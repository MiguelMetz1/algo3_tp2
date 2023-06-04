package Plots;

import Defenses.Defense;
import Exceptions.CannotBuild;

public class UnbuildablePlot implements Buildable{

    public boolean canBuild(){
        return false;
    }

    public void build(Defense defense) throws CannotBuild {
        throw (new CannotBuild("Can't build in this plot."));
    }
}
