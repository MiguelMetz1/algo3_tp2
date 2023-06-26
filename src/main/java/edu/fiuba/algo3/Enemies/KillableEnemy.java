package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Enemies.Advancer.NullAdvancer;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Buff.Attribute;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Energy.Energy;

import java.util.ArrayList;
import java.util.Queue;

public abstract class KillableEnemy extends Enemy {
    private Energy life;

    public KillableEnemy(GameMap map, Queue<Coordinate> path ) {
        super(map, path);
        this.life = new Energy( this.getLife() );
        this.changeAttackReceiver();
    }

    @Override
    protected boolean isAvailableToReceiveAttack() {
        return super.isAvailableToReceiveAttack() && !this.isDead();
    }

    public void attack(ArrayList<Player> targets ){

        if( this.isDead() ){
            this.setAttacker( new NullAttacker() );
        }

        super.attack( targets );

    }

    public void finalizeYourWay(ArrayList<Enemy> finalWaysEnemies ){
        super.finalizeYourWay(finalWaysEnemies);
        if( this.isDead() ){
            finalWaysEnemies.add(this);
        }
    }

    protected ArrayList<Attribute> getBuffeablesAttributes(){
        ArrayList<Attribute> attributes = super.getBuffeablesAttributes();
        attributes.add(this.life);
        return  attributes;
    }

    public void advance(){
        if( this.isDead() ){
            this.setAdvancer( new NullAdvancer() );
        }
        super.advance();
    }

    protected boolean isDead(){
        return ( this.life.lower(new Energy(0)) || this.life.equals(new Energy(0)));
    }

    protected boolean actualLifeHigher( Energy otherLife ){
        return this.life.higher(otherLife);
    }

    protected abstract double getLife();

    protected abstract double getSpeed();

}
