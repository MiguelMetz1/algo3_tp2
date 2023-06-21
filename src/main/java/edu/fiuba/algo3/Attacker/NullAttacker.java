package edu.fiuba.algo3.Attacker;

import edu.fiuba.algo3.Enemies.Target;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NullAttacker implements Attacker<Target> {
    @Override
    public void attack(ArrayList<Target> enemy){
        Logger.getLogger("Attacker").log(Level.INFO, "This entity can't attack in this moments.");
    }

}
