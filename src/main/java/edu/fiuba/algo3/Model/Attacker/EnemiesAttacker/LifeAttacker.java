package edu.fiuba.algo3.Model.Attacker.EnemiesAttacker;

import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Players.Player;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Buff.LifeInstantDecrementerDebuff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Damage.AdditiveDamage;
import edu.fiuba.algo3.Model.TypeData.Damage.Damage;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;

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
