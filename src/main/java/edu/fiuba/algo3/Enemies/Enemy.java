package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.Advancer;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Energy;

public abstract class Enemy {
    Damage damage;

    Energy energy;

    Advancer advancer;

    public Enemy(Damage damage, Energy energy, Advancer advancer) {
        this.damage = damage;
        this.energy = energy;
        this.advancer = advancer;
    }

    public boolean shouldAdvance(){
        return advancer.shouldAdvance();

    }

    public void takeDamage(Damage damage) {

        damage.applyDamage(this.energy);
        if(isDead()){
            Credits creditsToGive = returnCredits();
            Player.getPlayer().giveCredits(creditsToGive);
        }

    }


    //TODO PONER isDead En Private
    public boolean isDead(){
        return (energy.isEmpty());
    }

    public Credits returnCredits(){

        return new Credits(this.amountOfCredits());
    }

    protected abstract int amountOfCredits();



    public abstract String returnName();

    public void advance() {

        advancer.advancePosition();
    }

    public void attack(Player player) {

        player.receiveAttack(this.damage);
    }


}
