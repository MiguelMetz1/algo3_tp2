package edu.fiuba.algo3.Model.Defenses.Builder;

import edu.fiuba.algo3.Model.Attacker.DefenseAttacker.InConstructionAttacker;
import edu.fiuba.algo3.Model.Attacker.Attacker;
import edu.fiuba.algo3.Model.Attacker.DefenseAttacker.TowerAttacker;
import edu.fiuba.algo3.Model.TypeData.Buff.Buff;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Distance.Distance;
import edu.fiuba.algo3.Model.TypeData.Time;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnderConstructionAttacker implements Builder {

    private Time timeOfConstruction;

    private Distance range;

    private Coordinate position;

    private Buff debuff;

    public UnderConstructionAttacker(Time timeOfConstruction, Buff debuff, Coordinate position, Distance range) {
        this.debuff = debuff;
        this.timeOfConstruction = timeOfConstruction;
        this.position = position;
        this.range = range;
    }

    @Override
    public Attacker actualState() {
        this.timeOfConstruction.reduceIn(1);
        Logger.getLogger("Builer").log(Level.INFO, "The tower in " + position.toString() + " has reduced the time of construction.");
        if( this.timeOfConstruction.equals(new Time(0)) || this.timeOfConstruction.lower(new Time(0))){
            Logger.getLogger("Builder").log(Level.INFO, "The tower in " + position.toString() + " has finalized the construction.");
            return new TowerAttacker(debuff, position, range);
        }

        return new InConstructionAttacker();
    }


}
