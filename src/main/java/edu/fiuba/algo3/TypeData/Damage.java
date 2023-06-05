package edu.fiuba.algo3.TypeData;

public class Damage {
    int damage;

    public Damage(int damage){
        this.damage = damage;
    }

    public void applyDamage(Reducible reducible) {
        reducible.reduce(this.damage);
    }
}
