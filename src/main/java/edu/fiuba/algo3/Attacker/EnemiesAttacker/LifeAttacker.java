package edu.fiuba.algo3.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Buff.LifeInstantDecrementerDebuff;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Damage.AdditiveDamage;
import edu.fiuba.algo3.TypeData.Damage.Damage;
import edu.fiuba.algo3.TypeData.Distance.Distance;

import java.util.ArrayList;

public class LifeAttacker implements Attacker<Player> {

    Damage damage;

    Attacker<Player> attacker;

    public LifeAttacker(Coordinate actualPosition, double damagePoints ){
        AdditiveDamage damage = new AdditiveDamage( damagePoints );
        this.damage = damage;
        Buff buff = new LifeInstantDecrementerDebuff( damage );
        this.attacker = new EnemiesAttacker( buff, actualPosition, new Distance(0) );
    }

    @Override
    public void attack(ArrayList<Player> enemies) {
        this.attacker.attack(enemies);
    }

}
