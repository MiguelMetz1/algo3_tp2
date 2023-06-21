package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.TypeData.*;

import java.util.ArrayList;
import java.util.Queue;

public abstract class TargetableEnemy extends Enemy implements Target{
    private Energy life;

    AttackReceiver attackReceiver;

    ArrayList<Attribute> attributes;

    public TargetableEnemy(GameMap map, Queue<Coordinate> path ) {
        super(map, path);
        this.life = new Energy( this.getLife() );
        this.changeAttackReceiver();
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

    private void changeAttackReceiver( ){
        this.attributes = new ArrayList<>();
        attributes.add(this.speed);
        attributes.add(this.life);
        this.setAttackReceiver( new LiveAttackReceiver(attributes) );
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

    protected void setAttackReceiver( AttackReceiver attackReceiver){
        this.attackReceiver = attackReceiver;
    }

    public void die( ArrayList< TargetableEnemy > finalWaysEnemies ){
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
