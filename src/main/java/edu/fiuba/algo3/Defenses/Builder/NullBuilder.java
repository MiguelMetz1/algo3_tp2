package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Enemies.Enemy;

public class NullBuilder implements Builder<Attacker<Enemy>>{

    @Override
    public Attacker actualState(){
        return new NullAttacker();
    }
}
