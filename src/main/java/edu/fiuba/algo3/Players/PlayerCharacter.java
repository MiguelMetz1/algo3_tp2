package edu.fiuba.algo3.Players;
import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Enemies.Target;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.Plots.HellsPlot;
import edu.fiuba.algo3.Plots.NullPlot;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.*;

public class PlayerCharacter implements Target, Placeable {

    private Plot position;
    private Life life;

    public PlayerCharacter() {
        this.life = new Life(20);
        this.position = new NullPlot();
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
    public void locateIn(Plot plot) throws IncorrectPlaceable {
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

    public String toString(){
        return "Player Character";
    }
}
