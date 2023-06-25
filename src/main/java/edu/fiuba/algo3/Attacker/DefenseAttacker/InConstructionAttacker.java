package edu.fiuba.algo3.Attacker.DefenseAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Enemies.Interface.Target;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InConstructionAttacker implements Attacker<Target> {
    @Override
    public void attack(ArrayList<Target> enemy){
        Logger.getLogger("Attacker").log(Level.INFO, "The defense is under construction");
    }
}
