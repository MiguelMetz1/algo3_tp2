package Attackers;

import Enemies.Enemy;

public interface Attack {
    void attack(Enemy enemy);
    boolean withinRange();
}
