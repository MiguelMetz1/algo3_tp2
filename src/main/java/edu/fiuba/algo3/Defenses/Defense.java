package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Attackers.Attack;
import edu.fiuba.algo3.Attackers.AttackerReadyToAttack;
import edu.fiuba.algo3.Defenses.States.Builder;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.RangeAttack;
import edu.fiuba.algo3.TypeData.Damage;


public abstract class Defense {

    Credits credits;
    protected Attack attacker;
    protected RangeAttack attackRange;

    private Damage damage;
    protected Builder builder;

    private Placeable placeable;

    public Defense(Damage damage, Credits credits, Builder builder, Attack attacker) {
        this.attackRange = null;
        this.credits = credits;
        this.builder = builder;
        this.attacker = attacker;
        this.damage = damage;
        this.placeable = new NotBoughtDefensePlacer();
    }

    public  void attack() throws CannotAttack{
        if( attackRange == null){
            throw new CannotAttack("The tower hasn't a position.");
        }

        Enemy enemy = null;
        try {
            enemy = attackRange.findEnemy();
        } catch (EnemyNotFound e) {
            throw new CannotAttack(e.getMessage());
        }
        this.attacker.attack(enemy);
    }
    public void build() throws CannotConstruction {
        this.builder = this.builder.nextBuild();
        if (builder.buildFinished()){
            this.attacker = new AttackerReadyToAttack(this.damage);
        }
    }

    public void notifyPlayer () {
        //TODO: Implement code to inform the player
    }

    protected void setState(Builder state) {
        this.builder = state;
    }

    public void buy(Credits credits) throws InsuficientCredits {
        if(!canBuy(credits)){
            throw (new InsuficientCredits("Insuficient Credits"));
        }
        credits.wasteCredits(this.credits);

        this.placeable = new BoughtDefensePlacer();
    }

    public void putIn(Coordinate coordinate) throws CannotBuild {
        this.placeable.putIn(this, coordinate);
        this.attackRange = new RangeAttack( coordinate, this.range());
    }

    private boolean canBuy(Credits credits) {
        return !(this.credits.lowerCredits(credits));
    }

    protected abstract int range();


}
