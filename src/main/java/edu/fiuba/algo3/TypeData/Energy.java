package edu.fiuba.algo3.TypeData;

public class Energy implements Reducible<Energy>{
    Life life;

    public Energy(Life life){
        this.life = life;
    }

    public void reduceIn(Energy energy) {
        this.life.reduceIn(energy.life);
    }

    public boolean higher( Energy otherEnergy ){
        return this.life.higher(otherEnergy.life);
    }

    public boolean equals( Energy otherEnergy ){
        return this.life.equals(otherEnergy.life);
    }

}
