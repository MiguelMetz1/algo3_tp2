package edu.fiuba.algo3.Defenses.States;
import edu.fiuba.algo3.Attackers.Attack;
import edu.fiuba.algo3.Builders.Builder;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.TypeData.RangeAttack;

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


