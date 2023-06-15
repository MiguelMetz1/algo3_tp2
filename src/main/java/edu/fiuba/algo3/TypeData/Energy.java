package edu.fiuba.algo3.TypeData;

public class Energy implements Attribute {
    Life life;

    public Energy(Life life){
        this.life = life;
    }

    public void reduceIn( int reducePoints ) {
        this.life.reduceIn(reducePoints);
    }

    @Override
    public void changeInScale(int scale) {
        this.life.changeInScale(scale);
    }

    @Override
    public void applyBuff(Buff buff) {
        this.life.applyBuff(buff);
    }

    @Override
    public void quitBuffs() {
        this.life.quitBuffs();
    }

    public boolean higher( Energy otherEnergy ){
        return this.life.higher(otherEnergy.life);
    }

    public boolean equals( Energy otherEnergy ){
        return this.life.equals(otherEnergy.life);
    }

    @Override
    public boolean typeOfBuffsEquals(String type) {
        return type.equals(this.type());
    }

    private String type(){
        return this.getClass().getName();
    }

}
