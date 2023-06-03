package Defenses;
import Attackers.AttackTowerWhite;
import Builders.BuilderTowerWhite;
import Defenses.States.FinishedConstruction;
import Defenses.States.UnderConstruction;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import TypeData.RangeAttack;
import TypeData.Damage;
import Attackers.Attack;

public class TowerWhite extends Defense {
    private Attack attacker;
    public TowerWhite (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerWhite(timeConstruction));
        this.attacker = new AttackTowerWhite(rangeAttack);

    }

    @Override
    public void attack() throws CannotAttack {
        this.stateDefense.attack(this.attacker,this.attackRange);
    }

    @Override
    public void build() throws CannotConstruction {
        this.stateDefense.build();
        if (this.builder.finished()) {
            this.setState(new FinishedConstruction());
        }
    }
}

