package edu.fiuba.algo3.Model.TypeData.Speed;

import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

public class Speed extends Attribute {

    public Speed(double speed){
        super(speed);
    }

    public Distance inDistancePerTurn(){
        return new Distance(this.pointsOfAttribute);
    }

    protected String type(){
        return this.getClass().getName();
    }
}
