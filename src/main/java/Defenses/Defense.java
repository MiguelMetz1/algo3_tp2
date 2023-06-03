package Defenses;
import Builders.Builder;
import Defenses.States.FinishedConstruction;
import Defenses.States.StateDefense;
import Exceptions.CannotConstruction;
import TypeData.RangeAttack;
import TypeData.Damage;
import Exceptions.CannotAttack;


public abstract class Defense {

    protected Builder builder;
    protected RangeAttack attackRange;

    private Damage damage;
    protected StateDefense stateDefense;

    public Defense(RangeAttack rangeAttack, Damage damage) {
        this.attackRange = rangeAttack;
        this.damage = damage;

    }
    public abstract void attack() throws CannotAttack;
    public abstract void build () throws CannotConstruction;

    public void notifyPlayer () {
        //TODO: Implement code to inform the player
    }

    protected void setState(StateDefense state) {
        this.stateDefense = state;
    }


}
