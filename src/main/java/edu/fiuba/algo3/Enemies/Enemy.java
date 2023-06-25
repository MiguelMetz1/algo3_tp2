package edu.fiuba.algo3.Enemies;

import Advancer.Advancer;
import TypeData.Damage;
import TypeData.Energy;

public abstract class Enemy {
    Damage damage;

    Energy energy;

    Advancer advancer;

    public Enemy(Damage damage, Energy energy, Advancer advancer) {
        this.damage = damage;
        this.energy = energy;
        this.advancer = advancer;
    }

    public boolean shouldAdvance(){
        return advancer.shouldAdvance();

    }


}
