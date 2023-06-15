package edu.fiuba.algo3.TypeData;

import java.util.ArrayList;

public class Speed implements Attribute {
    int speed;
    private ArrayList<Buff> buffs;

    public Speed(int speed){

        this.speed = speed;
        this.buffs = new ArrayList<>();
    }

    public boolean lower( Speed otherSpeed ){
        return this.speed < otherSpeed.speed;
    }

    public void reduceIn( int reducePoints ){
        this.speed -= reducePoints;
    }

    @Override
    public void changeInScale(int scale) {
        if( this.speed == 0){
            this.speed = 1;
        }
        this.speed *= scale;
    }

    @Override
    public void applyBuff( Buff buff ){
        buff.applyBuffIn(this, buffs);
    }

    @Override
    public boolean typeOfBuffsEquals(String type) {
        return type.equals(this.type());
    }

    private String type(){
        return this.getClass().getName();
    }

    public void quitBuffs() {
        ArrayList<Buff> buffsToQuit = new ArrayList<Buff>();
        for( Buff buff: buffs){
            buff.quitBuffFrom(this, buffsToQuit);
        }
        quitDebbuffs(buffsToQuit);
    }

    private void quitDebbuffs(ArrayList<Buff> buffsToQuit){
        for( Buff buffToQuit: buffsToQuit ){
            this.buffs.remove(buffToQuit);
        }
    }
}
