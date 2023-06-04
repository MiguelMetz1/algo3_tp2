package Enemies;

import TypeData.Damage;
import TypeData.Energy;
import TypeData.Life;
import TypeData.Speed;

public abstract class Enemy {
    Damage damage;
    Speed speed;
    Energy energy;

    public Enemy(Damage damage, Speed speed, Energy energy) {
        this.damage = damage;
        this.speed = speed;
        this.energy = energy;
    }


}
