package edu.fiuba.algo3.Advancer;

public abstract class Advancer {

    protected int mustAdvanceAmount;
    protected int actualPosition;

    protected int initialPosition;
    protected int finalPosition;

    public Advancer(int speed){
        mustAdvanceAmount = speed;
        actualPosition = 0;
        initialPosition = 0;
        finalPosition = mustAdvanceAmount;
    }
    public void advancePosition(){
        this.actualPosition += 1;
    }

    public boolean shouldAdvance(){
        boolean canAdvance = (actualPosition != finalPosition);
        if (canAdvance == false) {
             finalPosition += mustAdvanceAmount;
        }
        return  canAdvance;

    }


}
