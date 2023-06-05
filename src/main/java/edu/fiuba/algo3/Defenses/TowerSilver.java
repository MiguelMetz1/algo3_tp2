package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Attackers.AttackerInConstruction;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.RangeAttack;

public class TowerSilver extends Defense {

    public TowerSilver() throws CannotBuild {


        super(new Damage(2),
                new Credits(20),
                new UnderConstruction(2, new FinishedConstruction()),
                new AttackerInConstruction());
    }

    protected int range(){
        return 5;
    }


}
