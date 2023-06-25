package Plots.Passables;

import edu.fiuba.algo3.Enemies.Enemy;

public interface PassableByEnemy {

    public boolean areCharacters();
    Enemy returnEnemy();
    void addEnemy(Enemy enemy);
}
