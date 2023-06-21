package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public abstract class Attribute {

    protected double pointsOfAttribute;
    protected ArrayList<Buff> activeBuffs;

    public Attribute( double pointsOfAttribute ){
        this.pointsOfAttribute = pointsOfAttribute;
        this.activeBuffs = new ArrayList<>();
    }

    public void incrementIn( double incrementPoints ){
        this.pointsOfAttribute += incrementPoints;
    }

    public void reduceIn( double reducePoints ){
        this.pointsOfAttribute -= reducePoints;
    }
    public void changeInScale(double scale){
        this.pointsOfAttribute *= scale;
    }
    public void applyBuff( Buff buff ){
        buff.applyBuffIn(this, activeBuffs);
    }
    boolean typeOfBuffsEquals(String type){
        return type.equals(this.type());
    }
    public void quitBuffs(){
        ArrayList<Buff> buffsToQuit = new ArrayList<>();
        for( Buff buff: activeBuffs){
            buff.quitBuffFrom(this, buffsToQuit);
        }
        quitDebbuffs(buffsToQuit);
    }

    private void quitDebbuffs(ArrayList<Buff> buffsToQuit){
        for( Buff buffToQuit: buffsToQuit ){
            this.activeBuffs.remove(buffToQuit);
        }
    }

    public boolean lower( Attribute otherAttribute ){
        return this.pointsOfAttribute < otherAttribute.pointsOfAttribute;
    }

    public boolean higher( Attribute otherAttribute ){
        return this.pointsOfAttribute > otherAttribute.pointsOfAttribute;
    }

    public boolean equals( Attribute otherAttribute ){
        return this.pointsOfAttribute == otherAttribute.pointsOfAttribute;
    }


    protected abstract String type();

}
