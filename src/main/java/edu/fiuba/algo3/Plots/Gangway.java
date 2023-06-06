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


    public Gangway(){
        super( new UnbuildablePlot() );
        this.enemies = new ArrayList<Enemy>();
    }
    public Gangway(Gangway previousGangway){
        super(new UnbuildablePlot());
        previousGangway.setNext(this);
        this.previousGangway = previousGangway;
        this.enemies = new ArrayList<Enemy>();
        nextGangway = null;
    }

    protected void setNext(Gangway gangway){
        this.nextGangway = gangway;
    }


    public void advanceEnemies(){
        if (nextGangway == null){
            return;
        }
        nextGangway.advanceEnemies();
        if( enemies != null && !enemies.isEmpty() )
            advanceEnemy(enemies.get(0));
    }

    private void advanceEnemy(Enemy enemy){
        Gangway actualGangway = this;
        Gangway actualNextGangway = this.nextGangway;

        while( enemy.shouldAdvance() ){
            if( actualNextGangway != null ) {
                actualNextGangway.addEnemy(enemy);
                actualGangway.enemies.remove(enemy);
                //enemies.remove(enemy);
                actualGangway = actualNextGangway;
                actualNextGangway = actualNextGangway.nextGangway;
            }
            enemy.advance();
        }
      if( !enemies.isEmpty() ){
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
