package edu.fiuba.algo3.Model.TypeData.Life;

import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;

public class Life extends Attribute {

    public Life(double lifePoints) {
        super(lifePoints);
    }

    protected String type(){
        return this.getClass().getName();
    }



}
