package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Buff;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Energy;

import java.util.Queue;

public class Owl extends TargetableEnemy {

    Coordinate finalGangway;

    public Owl(GameMap map, Queue<Coordinate> path, Coordinate finalGangway) {
        super(map, path);
        this.addOwlPassablePlots();
        this.finalGangway = finalGangway;
        this.setAttacker( new TowersAttacker( actualPosition ) );
    }

    private void addOwlPassablePlots(){
        this.addPassablePlot(Rocky.class.getName());
        this.addPassablePlot(Ground.class.getName());
    }
    public void takeBuff(Buff buff) {
        super.takeBuff(buff);
        Energy halfLife = new Energy(this.getLife()*0.5);
        if( !this.actualLifeHigher(halfLife) ){
            this.setAdvancer(new DamagedOwlAdvancer(this, this.map, this.actualPosition, this.speed, this.finalGangway));
        }

    }

    @Override
    protected double getLife() {
        return 5;
    }

    @Override
    protected double getSpeed() {
        return 5;
    }

}
