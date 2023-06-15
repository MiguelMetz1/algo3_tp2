package edu.fiuba.algo3.TypeData;

public class DecrementerDamage extends Damage{

    public DecrementerDamage(int damagePoints){
        super(damagePoints);
    }

    @Override
    public void applyDamage(Attribute life) {
        life.reduceIn(this.damagePoints);
    }

    @Override
    public void removeDamage(Attribute life) {
        life.reduceIn((-1)*this.damagePoints);
    }
}
