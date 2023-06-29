package edu.fiuba.algo3.Model.Enemies;

import edu.fiuba.algo3.Model.Attacker.NullAttacker;
import edu.fiuba.algo3.Model.Enemies.Advancer.NullAdvancer;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Energy.Energy;
import edu.fiuba.algo3.Model.TypeData.Buff.Attribute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class KillableEnemy extends Enemy {
    private Energy life;

    public KillableEnemy(GameMap map, LinkedList<Coordinate> path ) {
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
            Logger.getLogger("Enemies").log(Level.INFO, "A " + this.getType() + " in " + actualPosition + " has dead.");
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
