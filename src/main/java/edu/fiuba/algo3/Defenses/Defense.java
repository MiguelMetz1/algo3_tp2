package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Manager.Manager;
import edu.fiuba.algo3.Manager.AttackManager;
import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.RangeAttack;
import edu.fiuba.algo3.TypeData.Damage;


public abstract class Defense {

    Credits ticketCredits;
    Credits cost;
    protected Manager manager;
    protected RangeAttack rangeAttack;
    protected int range;
    private final Damage damage;
    protected Builder builder;
    private Placeable placeable;

    public Defense(Damage damage, Credits credits, Builder builder, Manager manager, int range) {
        this.range = range;
        this.cost = credits;
        this.builder = builder;
        this.manager = manager;
        this.damage = damage;
        this.placeable = new NotBoughtDefensePlacer();
        this.ticketCredits = new Credits(0);
    }

    public  void attack() throws Exception {

        manager.attackWithin(rangeAttack);




       /* if(enemy.isDead()){
            enemy.returnCredits().transferCreditsTo(this.ticketCredits);
        }*/
    }
    public void build() throws CannotConstruction {
        this.builder = this.builder.nextBuild();
        if (builder.buildFinished()){
            this.manager = new AttackManager(this.damage);
        }
    }

    public void notifyPlayer () {
        //TODO: Implement code to inform the player
    }

    public void buy(Credits credits) throws InsuficientCredits {

        if(!canBuy(credits)){
            throw (new InsuficientCredits("Insuficient Credits"));
        }

        credits.wasteCredits(this.cost);
        this.cost = new Credits(0);

        this.placeable = new BoughtDefensePlacer();
    }

    public void putIn(Coordinate coordinate) throws CannotBuild {

        this.placeable.putIn(this, coordinate);
        this.rangeAttack = new RangeAttack( coordinate, this.range() );
    }

    private boolean canBuy(Credits credits) {

        return !(this.cost.higherCredits(credits));
    }

    protected abstract int range();

    public void transferPickedCreditsTo(Credits credits){

        this.ticketCredits.transferCreditsTo(credits);
    }



}