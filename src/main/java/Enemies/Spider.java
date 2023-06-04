package Enemies;

import Players.Player;
import TypeData.Damage;
import TypeData.Energy;
import TypeData.Life;
import TypeData.Speed;

public class Spider extends Enemy{

    public Spider(){
        super(new Damage(2),new Speed(2), new Energy(new Life(2)));

    }

    public void attack(Player player) {
        player.receiveAttack(this.damage);
    }
}
