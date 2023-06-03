package Defenses;
import Attackers.Attack;
import Attackers.AttackTowerSilver;
import Builders.BuilderTowerSilver;
import Defenses.States.FinishedConstruction;
import Defenses.States.UnderConstruction;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import TypeData.Damage;
import TypeData.RangeAttack;

public class TowerSilver extends Defense {

    private Attack attacker;
    public TowerSilver (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerSilver(timeConstruction));
        this.attacker = new AttackTowerSilver(rangeAttack);
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
