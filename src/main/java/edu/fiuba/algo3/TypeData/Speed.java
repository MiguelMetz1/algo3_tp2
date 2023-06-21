package edu.fiuba.algo3.TypeData;

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
