package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;

public class Gangway extends Plot{
    protected Gangway nextGangway;
    protected Gangway previousGangway;
    protected ArrayList <Enemy> enemies;

    public Gangway(Coordinate coordinate){
        super(coordinate, new UnbuildablePlot());
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
            advanceEnemy(enemy);
        }
    }
    private void advanceEnemy(Enemy enemy){
        Gangway gangway = nextGangway;
       while(enemy.shouldAdvance()){
           gangway.addEnemy(enemy);
           gangway = gangway.nextGangway;
       }
    }

    public Enemy returnEnemy() throws EnemyNotFound {
        Iterator<Enemy> enemiesIterator = enemies.iterator();
        while( enemiesIterator.hasNext() ){
            return enemiesIterator.next();
        }
        throw new EnemyNotFound("No enemies in this plot.");
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public String showPlotName(){
        return "Pasarela";
    }

}
