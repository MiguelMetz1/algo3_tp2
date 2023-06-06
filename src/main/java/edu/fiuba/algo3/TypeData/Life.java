package edu.fiuba.algo3.TypeData;

public class Life implements Reducible{

    int life;
    public Life(int life) {
        this.life = life;
    }

    public void reduce(int life) {
        this.life -= life;
    }

    public boolean isEmpty() {
        return (this.life <= 0);
    }

    public int getLife(){return this.life;}
}
