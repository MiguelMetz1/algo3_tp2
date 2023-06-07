package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Manager.ConstructionManager;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.Manager.Manager;

public class TowerWhite extends Defense {
    private Manager attacker;

    public TowerWhite(){
        super(new Damage(1),
                new Credits(10),
                new UnderConstruction(1, new FinishedConstruction()),
                new ConstructionManager(),
                3);
    }

    protected int range(){
        return (this.range);
    }

    /*public TowerWhite (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerWhite(timeConstruction));
        this.attacker = new AttackTowerWhite(rangeAttack);

    }*/

}

