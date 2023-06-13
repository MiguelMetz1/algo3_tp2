package edu.fiuba.algo3.Attacker;
import edu.fiuba.algo3.Enemies.Target;

public class InConstructionAttacker implements Attacker{
    @Override
    public void attack(Target enemy){
        System.out.println("The defense is under construction");
    }
}
