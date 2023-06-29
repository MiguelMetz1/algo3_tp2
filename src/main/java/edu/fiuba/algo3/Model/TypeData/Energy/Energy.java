package edu.fiuba.algo3.Model.TypeData.Energy;

import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;

public class Energy extends Attribute {

    public Energy(double energyPoints){
        super(energyPoints);
    }

    protected String type(){
        return this.getClass().getName();
    }

}
