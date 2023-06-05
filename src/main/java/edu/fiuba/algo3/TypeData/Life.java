package edu.fiuba.algo3.TypeData;

public class Life {

    int life;
    public Life(int life) {
        this.life = life;
    }

    public void reduceLife(int life) {
        this.life -= life;
    }

    public boolean isEmpty() {
        return (this.life <= 0);
    }
}
