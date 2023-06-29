package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Attacker.DefenseAttacker.InConstructionAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.DefenseAttacker.TowerAttacker;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Buff.Buff;
import edu.fiuba.algo3.TypeData.Time;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class UnderConstructionAttacker implements Builder {

    private Time timeOfConstruction;

    private Distance range;

    private Coordinate position;

    private Buff debuff;

    /*public UnderConstructionAttacker(int timeOfConstruction, Buff debuff, Plot position, Distance range) {
        this.debuff = debuff;
        this.timeOfConstruction = timeOfConstruction;
        this.positionedPlace = position;
        this.range = range;
    }*/

    public UnderConstructionAttacker(Time timeOfConstruction, Buff debuff, Coordinate position, Distance range) {
        this.debuff = debuff;
        this.timeOfConstruction = timeOfConstruction;
        this.position = position;
        this.range = range;
    }

    @Override
    public Attacker actualState() {
        this.timeOfConstruction.reduceIn(1);
        if( this.timeOfConstruction.equals(new Time(0)) || this.timeOfConstruction.lower(new Time(0))){
            return new TowerAttacker(debuff, position, range);
        }

        return new InConstructionAttacker();
    }


}
