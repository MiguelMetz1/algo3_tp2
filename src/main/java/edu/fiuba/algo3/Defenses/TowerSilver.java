package edu.fiuba.algo3.Defenses;
import edu.fiuba.algo3.Manager.ConstructionManager;
import edu.fiuba.algo3.Defenses.States.FinishedConstruction;
import edu.fiuba.algo3.Defenses.States.UnderConstruction;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.TypeData.Credits;
import edu.fiuba.algo3.TypeData.Damage;

public class TowerSilver extends Defense {

    public TowerSilver() throws CannotBuild {


        super(new Damage(2),
                new Credits(20),
                new UnderConstruction(2, new FinishedConstruction()),
                new ConstructionManager(),
                5);
    }

    protected int range(){

        return (this.range);
    }


}
