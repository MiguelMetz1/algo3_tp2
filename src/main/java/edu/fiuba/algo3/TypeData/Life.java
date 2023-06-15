package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public class Life implements Attribute {

    ArrayList<Buff> buffs;
    int lifePoints;
    public Life(int lifePoints) {
        this.lifePoints = lifePoints;
        this.buffs = new ArrayList<>();
    }

    public void reduceIn(Life otherLife) {
        this.lifePoints -= otherLife.lifePoints;
    }

    public boolean higher( Life otherLife ) {
        return (this.lifePoints > otherLife.lifePoints);
    }

    protected int getLifePoints(){
        return lifePoints;
    }

    public boolean equals( Life otherLife ){
        return this.lifePoints == otherLife.lifePoints;
    }

    @Override
    public void reduceIn(int reducePoints) {
        this.lifePoints -= reducePoints;
    }

    @Override
    public void changeInScale(int scale) {

    }

    @Override
    public void applyBuff(Buff buff) {
        buff.applyBuffIn(this, buffs);
    }

    @Override
    public void quitBuffs() {
        ArrayList<Buff> buffsToQuit = new ArrayList<Buff>();
        for( Buff buff: buffs){
            buff.quitBuffFrom(this, buffsToQuit);
        }
        quitDebbufs(buffsToQuit);
    }

    private void quitDebbufs(ArrayList<Buff> buffsToQuit){
        for( Buff buffToQuit: buffsToQuit ){
            this.buffs.remove(buffToQuit);
        }
    }

    @Override
    public boolean typeOfBuffsEquals(String type) {
        return type.equals(this.type());
    }

    private String type(){
        return this.getClass().getName();
    }
}
