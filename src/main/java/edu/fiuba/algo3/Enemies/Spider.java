package edu.fiuba.algo3.Enemies;


import edu.fiuba.algo3.Advancer.SpiderAdvancer;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Energy;
import edu.fiuba.algo3.TypeData.Life;


public class Spider extends Enemy{

    public Spider(){

        super(new Damage(2), new Energy(new Life(2)), new SpiderAdvancer());

    }
    public void attack(Player player) {

        player.receiveAttack(this.damage);

    }
    protected int amountOfCredits(){

        int random = (int) Math.round(Math.random());
        return 10*(random);

    }

    public String returnName(){

        return "Spider";
    }

}
