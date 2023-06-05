package edu.fiuba.algo3.TypeData;

public class Energy implements Reducible{
    Life life;

    public Energy(Life life){
        this.life = life;
    }

    public void reduce(int life) {

        this.life.reduce(life);
    }

    public boolean isEmpty() {
        return this.life.isEmpty();
    }
}
