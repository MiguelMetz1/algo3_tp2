package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Attackers.Attack;
import edu.fiuba.algo3.Attackers.AttackTowerSilver;
import edu.fiuba.algo3.Builders.BuilderTowerSilver;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.Exceptions.CannotAttack;
import edu.fiuba.algo3.Exceptions.CannotConstruction;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class TowerSilver extends Defense {

    private Attack attacker;

    public TowerSilver(){

        //ESTO HAY QUE CAMBIARLO
        super(new RangeAttack(new Coordinate(0,0), GameMap.getMap(),5),
                new Damage(2),
                new Credits(20));

        this.stateDefense = new UnderConstruction(new BuilderTowerSilver(2));
        this.attacker = new AttackTowerSilver(new RangeAttack(new Coordinate(0,0), GameMap.getMap(),5));
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
