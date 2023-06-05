package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Builders.Builder;
import edu.fiuba.algo3.Defenses.States.StateDefense;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.RangeAttack;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.Exceptions.CannotAttack;


public abstract class Defense {

    Credits credits;
    protected Builder builder;
    protected RangeAttack attackRange;

    private Damage damage;
    protected StateDefense stateDefense;

    public Defense(RangeAttack rangeAttack, Damage damage, Credits credits) {
        this.attackRange = rangeAttack;
        this.damage = damage;
        this.credits = credits;

    }
    public abstract void attack() throws CannotAttack;
    public abstract void build () throws CannotConstruction;

    public void notifyPlayer () {
        //TODO: Implement code to inform the player
    }

    protected void setState(StateDefense state) {
        this.stateDefense = state;
    }


    public void buy(Credits credits) throws InsuficientCredits {
        if(!canBuy(credits)){
            throw (new InsuficientCredits("Insuficient Credits"));
        }
        credits.wasteCredits(this.credits);


    }

    private boolean canBuy(Credits credits) {
        return !(this.credits.lowerCredits(credits));
    }


}
