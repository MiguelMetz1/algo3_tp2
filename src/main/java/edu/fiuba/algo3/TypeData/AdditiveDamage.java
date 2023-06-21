package edu.fiuba.algo3.TypeData;

public class AdditiveDamage extends Damage{

    public AdditiveDamage(double damagePoints){
        super(damagePoints);
    }

    @Override
    public void applyDamage(Attribute life) {
        life.reduceIn(this.pointsOfAttribute);
    }

    @Override
    public void removeDamage(Attribute life) {
        life.incrementIn(this.pointsOfAttribute);
    }

    @Override
    protected String type() {
        return this.getClass().getName();
    }
}
