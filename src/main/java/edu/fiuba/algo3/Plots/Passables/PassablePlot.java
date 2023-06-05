package edu.fiuba.algo3.Plots.Passables;

import edu.fiuba.algo3.Enemies.Enemy;

import java.util.List;

public class PassablePlot implements PassableByEnemy {
    List <Enemy> enemies;

    public boolean areCharacters(){
        return !(enemies.isEmpty());
    }
    public Enemy returnEnemy(){
        return enemies.get(0);
    }
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

}
