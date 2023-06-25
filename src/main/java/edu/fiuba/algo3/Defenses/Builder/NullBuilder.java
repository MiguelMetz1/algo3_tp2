package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Attacker.Attacker;

public class NullBuilder implements Builder{

    @Override
    public Attacker actualState(){
        return new NullAttacker();
    }
}
