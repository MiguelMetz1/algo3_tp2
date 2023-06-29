package edu.fiuba.algo3.Model.Attacker;


import edu.fiuba.algo3.Model.Enemies.Interface.Target;

import java.util.ArrayList;

public interface Attacker<T extends Target> {
    void attack(ArrayList<T> attackables);
}
