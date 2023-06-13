package edu.fiuba.algo3.TypeData;

public class Life implements Reducible<Life>{

    int lifePoints;
    public Life(int lifePoints) {
        this.lifePoints = lifePoints;
    }
    public Life() {
        this.lifePoints = this.getLifePoints();
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

}
