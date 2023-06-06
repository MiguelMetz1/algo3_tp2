package edu.fiuba.algo3.Plots;

import edu.fiuba.algo3.Enemies.Enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InitialGangway extends Gangway {

    private Queue< ArrayList<Enemy> > enemiesToSpawn;

    public  InitialGangway(){
        super();
        this.enemiesToSpawn = new LinkedList<>();
    }

    public InitialGangway(Gangway gangway, Queue<ArrayList<Enemy>> enemiesToSpawn){
        super();
        this.nextGangway = gangway.nextGangway;
        this.enemiesToSpawn = enemiesToSpawn;
    }

    public Boolean canSpawnEnemies(){
        return true;
    }

    public void spawnEnemies() {
        this.enemies = enemiesToSpawn.poll();
        if (this.enemies == null){
            this.enemies = new ArrayList<Enemy>();
        }
    }
}
