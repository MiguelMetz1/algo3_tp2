package edu.fiuba.algo3.Attacker;


import edu.fiuba.algo3.Enemies.Target;

import java.util.ArrayList;

public interface Attacker<T extends Target> {
    void attack(ArrayList<T> attackables);
}