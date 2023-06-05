package edu.fiuba.algo3.TypeData;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Plot;

import java.util.ArrayList;
import java.util.Iterator;

public class RangeAttack {
    ArrayList<Plot> around;

    public RangeAttack(Coordinate coordinate, int range){
        this.around = GameMap.getMap().getAround(coordinate, range);
    }
    public Enemy findEnemy() throws EnemyNotFound {
        Enemy enemy = null;
        Iterator<Plot> aroundIterator = around.iterator();
        while( aroundIterator.hasNext() ){
            Plot actualPlot = aroundIterator.next();
            if( actualPlot.hasEnemies() ){
                enemy = actualPlot.returnEnemy();
                break;
            }
        }
        if( enemy == null){
            throw ( new EnemyNotFound("There no enemies around.") );
        }

        return enemy;
    }

}
