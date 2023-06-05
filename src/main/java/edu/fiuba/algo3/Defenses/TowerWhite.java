package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Attackers.AttackTowerWhite;
import edu.fiuba.algo3.Builders.BuilderTowerWhite;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.RangeAttack;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.Attackers.Attack;

public class TowerWhite extends Defense {
    private Attack attacker;

    public TowerWhite(){
        super(new RangeAttack(new Coordinate(0,0), GameMap.getMap(),3),
                new Damage(1),
                new Credits(10));

        this.stateDefense = new UnderConstruction(new BuilderTowerWhite(1));
        this.attacker = new AttackTowerWhite(new RangeAttack(new Coordinate(0,0), GameMap.getMap(),3));
    }

    /*public TowerWhite (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerWhite(timeConstruction));
        this.attacker = new AttackTowerWhite(rangeAttack);

    }*/

    @Override
    public void attack() throws CannotAttack {
        this.stateDefense.attack(this.attacker,this.attackRange);
    }

    @Override
    public void build() throws CannotConstruction {
        this.stateDefense.build();
        if (this.stateDefense.buildFinished()) {
            this.setState(new FinishedConstruction());
        }

    }
}

