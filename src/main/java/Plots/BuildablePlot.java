package Plots;

import Defenses.Defense;

public class BuildablePlot implements Buildable{
    Defense defense;

    public boolean canBuild(){
        return (this.defense == null);
    }

    public void build(Defense defense){
        this.defense = defense;
    }
}
