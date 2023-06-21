package edu.fiuba.algo3.Defenses;

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
