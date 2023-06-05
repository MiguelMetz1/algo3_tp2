package edu.fiuba.algo3.Defenses.States;
import edu.fiuba.algo3.Attackers.Attack;
import edu.fiuba.algo3.Builders.Builder;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class UnderConstruction implements StateDefense {

    private Builder builder;

    public UnderConstruction (Builder builder) {
        this.builder = builder;
    }
    @Override
    public void attack(Attack attacker, RangeAttack rangeAttack) throws CannotAttack {
        //TODO: Implement a dictionary for the message
        throw new CannotAttack("The defense isn't available to attack");
    }

    @Override
    public void build(){
        this.builder.progress();
    };

    @Override
    public boolean buildFinished() {
        return this.builder.finished();
    }
}
