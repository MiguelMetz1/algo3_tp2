package edu.fiuba.algo3.Defenses.States;

import edu.fiuba.algo3.Attacker.InConstructionAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.ReadyAttacker;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Damage;
import edu.fiuba.algo3.TypeData.Distance;

public class UnderConstructionAttacker implements Builder {

    private int timeOfConstruction;
    private Damage damage;
    private Plot position;
    private Distance range;

    public UnderConstructionAttacker(int timeOfConstruction, Damage damage, Plot position, Distance range) {

        this.timeOfConstruction = timeOfConstruction;
        this.damage = damage;
        this.position = position;
        this.range = range;
    }

    @Override
    public Attacker actualState() {
        this.timeOfConstruction--;
        if( this.timeOfConstruction <= 0 ){
            return new ReadyAttacker(damage, position, range);
        }
        return new InConstructionAttacker();
    }
}
