package Plots;

import Enemies.Enemy;
import TypeData.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Gangway extends Plot{
    Gangway nextGangway;
    ArrayList <Enemy> enemies;
    public Gangway(Coordinate coordinate, Gangway nextGangway){
        super(coordinate, new UnbuildablePlot());
        this.nextGangway = nextGangway;
        this.enemies = new ArrayList<Enemy>();

    }

    public void avanceEnemies(){
        if (nextGangway == null){
            return;
        };
        nextGangway.avanceEnemies();
        for (Enemy enemy: enemies) {
            avanceEnemy(enemy);
        }
    }
    public void avanceEnemy(Enemy enemy){
        Gangway gangway = nextGangway;
       while(enemy.mustAdvance()){
           gangway.addEnemy(enemy);
           gangway = gangway.nextGangway;
       }
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }
}
