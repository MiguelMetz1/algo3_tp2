package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Attackers.AttackerInConstruction;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.RangeAttack;
import edu.fiuba.algo3.Attackers.Attack;

public class TowerWhite extends Defense {
    private Attack attacker;

    public TowerWhite(){
        super(new Damage(1),
                new Credits(10),
                new UnderConstruction(1, new FinishedConstruction()),
                new AttackerInConstruction());
    }

    protected int range(){
        return 3;
    }

    /*public TowerWhite (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerWhite(timeConstruction));
        this.attacker = new AttackTowerWhite(rangeAttack);

    }*/

}

