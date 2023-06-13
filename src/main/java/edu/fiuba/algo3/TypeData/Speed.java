package edu.fiuba.algo3.TypeData;

public class Speed {
    int speed;

    public Speed(int speed){
        this.speed = speed;
    }

    public void incrementSpeed(){
        this.speed++;
    }

    public boolean lower( Speed otherSpeed ){
        return this.speed < otherSpeed.speed;
    }
}
