package Plots;

import edu.fiuba.algo3.Enemies.Enemy;
import TypeData.Coordinate;

import java.util.ArrayList;

public class Gangway extends Plot{
    Gangway nextGangway;
    ArrayList <Enemy> enemies;

    public Gangway(Coordinate coordinate){
        this(coordinate, null);
    }
    public Gangway(Coordinate coordinate, Gangway nextGangway){
        super(coordinate, new UnbuildablePlot());
        this.nextGangway = nextGangway;
        this.enemies = new ArrayList<Enemy>();

    }

    public void setNext(Gangway gangway){
        this.nextGangway = gangway;
    }


    public void advanceEnemies(){
        if (nextGangway == null){
            return;
        };
        nextGangway.advanceEnemies();
        for (Enemy enemy: enemies) {
            avanceEnemy(enemy);
        }
    }
    public void avanceEnemy(Enemy enemy){
        Gangway gangway = nextGangway;
       while(enemy.shouldAdvance()){
           gangway.addEnemy(enemy);
           gangway = gangway.nextGangway;
       }
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public String showPlotName(){
        return "Pasarela";
    }
}
