package edu.fiuba.algo3.Model.Enemies;

import edu.fiuba.algo3.Model.Attacker.EnemiesAttacker.OwlAttacker;
import edu.fiuba.algo3.Model.Enemies.Advancer.DamagedOwlAdvancer;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Plots.Ground;
import edu.fiuba.algo3.Model.Plots.Rocky;
import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Energy.Energy;

import java.util.ArrayList;
import java.util.LinkedList;

public class Owl extends KillableEnemy {

    Coordinate finalGangway;

    public Owl(GameMap map, LinkedList<Coordinate> path, Coordinate finalGangway) {
        super(map, path);
        this.addOwlPassablePlots();
        this.finalGangway = finalGangway;
        this.setAttacker( new OwlAttacker( actualPosition ) );
    }

    protected ArrayList<Attribute> getBuffeablesAttributes(){
        ArrayList<Attribute> attributes = super.getBuffeablesAttributes();
        attributes.remove(this.speed);
        return  attributes;
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

    protected String getType(){
        return "Owl";
    }

}
