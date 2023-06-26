package edu.fiuba.algo3.Defenses.Towers;

import edu.fiuba.algo3.Defenses.Defense;

public class SilverTower extends Defense {

    @Override
    protected int range() {
        return 5;
    }

    @Override
    protected int timeOfConstruction() {
        return 2;
    }

    @Override
    protected int damage() {
        return 2;
    }

    public String toString(){
        return "Silver Tower";
    }

    public String image(){
        return "file:src/main/java/edu/fiuba/algo3/View/Images/silverTower.png";
    }

}
