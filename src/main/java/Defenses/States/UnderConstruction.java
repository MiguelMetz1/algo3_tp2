package Defenses.States;
import Attackers.Attack;
import Builders.Builder;
import Exceptions.CannotAttack;
import TypeData.RangeAttack;

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
