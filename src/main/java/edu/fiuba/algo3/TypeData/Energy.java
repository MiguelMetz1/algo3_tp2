package edu.fiuba.algo3.TypeData;

public class Energy extends Attribute {

    public Energy(double energyPoints){
        super(energyPoints);
    }

    protected String type(){
        return this.getClass().getName();
    }

}
