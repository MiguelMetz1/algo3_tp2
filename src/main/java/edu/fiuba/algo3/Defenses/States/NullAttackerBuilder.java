package edu.fiuba.algo3.Defenses.States;

import edu.fiuba.algo3.Attacker.NullAttacker;
import edu.fiuba.algo3.Attacker.Attacker;

public class NullAttackerBuilder implements Builder{

    @Override
    public Attacker actualState(){
        return new NullAttacker();
    }
}
