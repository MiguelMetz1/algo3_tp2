package edu.fiuba.algo3.TypeData;

public class Life extends Attribute {

    public Life(double lifePoints) {
        super(lifePoints);
    }

    protected String type(){
        return this.getClass().getName();
    }
}
