package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Attacker.DefenseAttacker.InConstructionAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.ReadyAttacker;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Buff.Buff;

public class UnderConstructionAttacker implements Builder {

    private int timeOfConstruction;

    private Distance range;

    private Coordinate position;

    private Buff debuff;

    /*public UnderConstructionAttacker(int timeOfConstruction, Buff debuff, Plot position, Distance range) {
        this.debuff = debuff;
        this.timeOfConstruction = timeOfConstruction;
        this.positionedPlace = position;
        this.range = range;
    }*/

    public UnderConstructionAttacker(int timeOfConstruction, Buff debuff, Coordinate position, Distance range) {
        this.debuff = debuff;
        this.timeOfConstruction = timeOfConstruction;
        this.position = position;
        this.range = range;
    }

    @Override
    public Attacker actualState() {
        this.timeOfConstruction--;
        if( this.timeOfConstruction <= 0 ){
            return new ReadyAttacker(debuff, position, range);
        }
        return new InConstructionAttacker();
    }
}
