package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;

public class Gangway extends Plot{
    Gangway nextGangway;
    Gangway previousGangway;
    ArrayList <Enemy> enemies;

    public Gangway(Coordinate coordinate){
        this(coordinate, null);
    }
    public Gangway(Coordinate coordinate, Gangway previousGangway){
        super(coordinate, new UnbuildablePlot());
        //this.nextGangway = nextGangway;
        this.previousGangway = previousGangway;
        this.previousGangway.setNext(this);
        this.enemies = new ArrayList<Enemy>();

    }

    private void setNext(Gangway gangway){
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
