package Defenses;
import Attackers.Attack;
import Attackers.AttackTowerSilver;
import Builders.BuilderTowerSilver;
import Defenses.States.FinishedConstruction;
import Defenses.States.UnderConstruction;
import Exceptions.CannotAttack;
import Exceptions.CannotConstruction;
import Map.Map;
import TypeData.Coordinate;
import TypeData.Credits;
import TypeData.Damage;
import TypeData.RangeAttack;

public class TowerSilver extends Defense {

    private Attack attacker;

    public TowerSilver(){

        //ESTO HAY QUE CAMBIARLO
        super(new RangeAttack(new Coordinate(0,0),Map.getMap(),5),
                new Damage(2),
                new Credits(20));

        this.stateDefense = new UnderConstruction(new BuilderTowerSilver(2));
        this.attacker = new AttackTowerSilver(new RangeAttack(new Coordinate(0,0),Map.getMap(),5));
    }
    /*public TowerSilver (RangeAttack rangeAttack, Damage damage, int timeConstruction) {
        super(rangeAttack,damage);
        this.stateDefense = new UnderConstruction(new BuilderTowerSilver(timeConstruction));
        this.attacker = new AttackTowerSilver(rangeAttack);
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
