package Attackers;

import edu.fiuba.algo3.Enemies.Enemy;

public interface Attack {
    void attack(Enemy enemy);
    boolean withinRange();
}
