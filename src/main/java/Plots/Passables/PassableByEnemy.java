package Plots.Passables;

import Enemies.Enemy;

public interface PassableByEnemy {

    public boolean areCharacters();
    Enemy returnEnemy();
    void addEnemy(Enemy enemy);
}
