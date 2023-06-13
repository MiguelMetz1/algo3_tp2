package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.TypeData.Damage;

public class DeadAttackReceiver implements AttackReceiver{
    @Override
    public void takeDamage(Damage damage){
        System.out.println("The enemy can't receive the attack because is dead");
    }
}
