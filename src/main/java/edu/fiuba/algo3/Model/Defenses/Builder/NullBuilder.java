package edu.fiuba.algo3.Model.Defenses.Builder;

import edu.fiuba.algo3.Model.Attacker.NullAttacker;
import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Enemies.Enemy;

public class NullBuilder implements Builder<Attacker<Enemy>>{

    @Override
    public Attacker actualState(){
        return new NullAttacker();
    }
}
