package edu.fiuba.algo3.Enemies;


import Advancer.SpiderAdvancer;
import Players.Player;
import TypeData.Damage;
import TypeData.Energy;
import TypeData.Life;


public class Spider extends Enemy{

    public Spider(){
        super(new Damage(2), new Energy(new Life(2)), new SpiderAdvancer());

    }

    public void attack(Player player) {
        player.receiveAttack(this.damage);
    }
}
