package edu.fiuba.algo3.Defenses.Towers;

import edu.fiuba.algo3.Defenses.Defense;

public class WhiteTower extends Defense {

    protected int range(){
        return 3;
    }

    protected int damage(){
        return 1;
    }

    protected int timeOfConstruction(){
        return 1;
    }

    public String toString(){
        return "White Tower";
    }

}

