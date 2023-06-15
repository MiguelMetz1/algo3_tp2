package edu.fiuba.algo3.Players;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.HellsPlot;
import edu.fiuba.algo3.Plots.NullPlot;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.*;

import java.util.ArrayList;
import java.util.Queue;

public class PlayerCharacter implements Target, Placeable {

    private Plot position;
    private Life life;

    private ArrayList<Attribute> attributes;

    public PlayerCharacter() {
        this.life = new Life(20);
        this.position = new NullPlot();
        this.attributes = new ArrayList<>();
        this.attributes.add(life);
    }

    @Override
    public void takeDamage(Damage damage){
        if( !this.isDead() ){
            damage.applyDamage(this.life);
            if( this.isDead() ){
                this.position = new HellsPlot();
            }
        }
    }

    @Override
    public void takeBuff(Buff buff) {
        if( !this.isDead() ){
            for( Attribute attribute: attributes){
                attribute.applyBuff(buff);
            }
            if( this.isDead() ){
                this.position = new HellsPlot();
            }
        }
    }

    @Override
    public void locateIn(Plot plot) throws WrongPlace {
        plot.receive(this);
        this.position = plot;
    }

    @Override
    public boolean distanceToBiggerThan(Plot position, Distance attackDistance) {
        return this.position.distanceToBiggerThan(position, attackDistance);
    }

    private boolean isDead(){
        Life deadEntityLife = new Life(0);
        return (deadEntityLife.higher(this.life) || deadEntityLife.equals(this.life));
    }

    public String won(Queue<ArrayList<Enemy>> troops, ArrayList<Enemy> actualEnemies){
        if( this.isDead()){
            return "Lose.";
        }
        if( troops.isEmpty() && actualEnemies.isEmpty()){
            return "Won.";
        }
        return "In game.";
    }
}
