package Defenses.States;
import Attackers.Attack;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import Enemies.Enemy;
import TypeData.RangeAttack;

public interface StateDefense {
    void attack(Attack attacker, RangeAttack rangeAttack) throws CannotAttack;
    void build() throws CannotConstruction;
    boolean buildFinished();
}

