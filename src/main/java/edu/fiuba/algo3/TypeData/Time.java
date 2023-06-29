package edu.fiuba.algo3.TypeData;

import edu.fiuba.algo3.TypeData.Buff.Attribute;

public class Time extends Attribute {
    public Time(double pointsOfAttribute) {
        super(pointsOfAttribute);
    }

    @Override
    protected String type() {
        return "Time";
    }

    public void copy(Time otherTime) {
        this.pointsOfAttribute = otherTime.pointsOfAttribute;
    }
}
