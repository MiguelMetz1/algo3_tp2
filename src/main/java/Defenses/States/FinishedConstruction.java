package Defenses.States;
import Attackers.Attack;
import Builders.Builder;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import edu.fiuba.algo3.Enemies.Enemy;
import TypeData.RangeAttack;

public class FinishedConstruction implements StateDefense {
    protected Builder builder;

    @Override
    public void attack(Attack attacker, RangeAttack rangeAttack) throws CannotAttack{
        Enemy enemy = rangeAttack.findEnemy();
        if (enemy != null) {
            attacker.attack(enemy);
        }
    }

    @Override
    public void build() throws CannotConstruction {
        //TODO: Implement a dictionary for the message
        throw new CannotConstruction("The defense completed its construction");
    };

    @Override
    public boolean buildFinished() {
        return true;
    }
}


