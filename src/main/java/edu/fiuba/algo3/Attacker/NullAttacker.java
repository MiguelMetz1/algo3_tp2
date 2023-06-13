package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Enemies.Target;

public class NullAttacker implements Attacker {
    @Override
    public void attack(Target enemy){
        System.out.println("This entity can't attack in this moments.");
    }

}
