package edu.fiuba.algo3.TypeData;

public class Time extends Attribute{

    public Time(double pointsOfAttribute) {
        super(pointsOfAttribute);
    }

    @Override
    protected String type() {
        return "Time";
    }
}
