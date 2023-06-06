package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.Iterator;

public class Gangway extends Plot{
    protected Gangway nextGangway;
    protected ArrayList <Enemy> enemies;


    public Gangway(Coordinate coordinate){
        super(coordinate, new UnbuildablePlot());
        this.enemies = new ArrayList<Enemy>();
    }
    public Gangway(Coordinate coordinate, Gangway previousGangway){
        super(coordinate, new UnbuildablePlot());
        previousGangway.setNext(this);
        this.enemies = new ArrayList<Enemy>();
        nextGangway = null;
    }

    private void setNext(Gangway gangway){
        this.nextGangway = gangway;
    }


    public void advanceEnemies(){
        if (nextGangway == null){
            return;
        };
        nextGangway.advanceEnemies();
        if( enemies != null && enemies.size() > 0 )
            advanceEnemy(enemies.get(0));
    }
    private void advanceEnemy(Enemy enemy){
        Gangway gangway = this.nextGangway;
        //System.out.println(enemy.returnName()+": "+gangway.coordinate.returnCoordinate());
       while(enemy.shouldAdvance()){
           if( gangway != null ) {
               gangway.addEnemy(enemy);
               enemies.remove(enemy);
               gangway = gangway.nextGangway;
               enemy.advance();
           }
       }
      if( enemies.size() > 0){
          advanceEnemy(enemies.get(0));
      }

    }

    public boolean hasEnemies(){
        return ( enemies.size() > 0);
    }
    public Enemy returnEnemy() throws EnemyNotFound {
        if (enemies.get(0).isDead())
            enemies.remove(0);
        if(enemies.size() > 0) {
            return enemies.get(0);
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
