package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Advancer.AntAdvancer;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Energy;
import edu.fiuba.algo3.TypeData.Life;



public class Ant extends Enemy {

    public Ant(){
        super(new Damage(1), new Energy(new Life(1)), new AntAdvancer());


    }

    public void attack(Player player) {
        player.receiveAttack(this.damage);
    }

    protected int amountOfCredits(){
        return 1;
    }

    public String returnName(){
        return "Ant";
    }
}
