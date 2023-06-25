package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Enemies.AttackReceiver.LiveAttackReceiver;
import edu.fiuba.algo3.Enemies.AttackReceiver.AttackReceiver;
import edu.fiuba.algo3.Enemies.Advancer.NullAdvancer;
import edu.fiuba.algo3.Enemies.AttackReceiver.NullAttackReceiver;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.Buff.Attribute;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Energy.Energy;

import java.util.ArrayList;
import java.util.Queue;

public abstract class TargetableEnemy extends Enemy {
    private Energy life;

    public TargetableEnemy(GameMap map, Queue<Coordinate> path ) {
        super(map, path);
        this.life = new Energy( this.getLife() );
    }

    @Override
    public void takeBuff(Buff buff) {

        AttackReceiver attackReceiver = this.attackReceiver;
        if( this.isDead() || this.isBeforeTheStartPosition() ){
            attackReceiver = new NullAttackReceiver();
        }

        attackReceiver.takeBuff(buff);

    }

    public void attack( ArrayList<PlayerCharacter> targets ){

        if( this.isDead() ){
            this.setAttacker( new NullAttacker() );
        }

        super.attack( targets );

    }

    protected ArrayList<Attribute> getBuffeablesAttributes(){
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(this.speed);
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

    public void die( ArrayList<TargetableEnemy> finalWaysEnemies ){
        if( this.isDead() ){
            finalWaysEnemies.add(this);
        }
    }

    protected boolean actualLifeHigher( Energy otherLife ){
        return this.life.higher(otherLife);
    }

    public boolean distanceToBiggerThan(Coordinate position, Distance attackDistance ){
        if( this.isBeforeTheStartPosition() )
            return true;
        return this.actualPosition.distanceTo(position).higher(attackDistance);
    }

    protected abstract double getLife();

    protected abstract double getSpeed();

}
