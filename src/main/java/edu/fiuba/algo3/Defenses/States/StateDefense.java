package edu.fiuba.algo3.Defenses.States;
import edu.fiuba.algo3.Attackers.Attack;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.TypeData.RangeAttack;

public interface StateDefense {
    void attack(Attack attacker, RangeAttack rangeAttack) throws CannotAttack;
    void build() throws CannotConstruction;
    boolean buildFinished();
}

