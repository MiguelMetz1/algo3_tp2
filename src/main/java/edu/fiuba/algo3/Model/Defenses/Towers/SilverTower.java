package edu.fiuba.algo3.Model.Defenses.Towers;

import edu.fiuba.algo3.Model.Defenses.Defense;

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


}
