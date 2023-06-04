package edu.fiuba.algo3.Enemies;

import Advancer.AntAdvancer;
import Players.Player;
import TypeData.Damage;
import TypeData.Energy;
import TypeData.Life;



public class Ant extends Enemy {

    public Ant(){
        super(new Damage(1), new Energy(new Life(1)), new AntAdvancer());


    }

    public void attack(Player player) {
        player.receiveAttack(this.damage);
    }
}
