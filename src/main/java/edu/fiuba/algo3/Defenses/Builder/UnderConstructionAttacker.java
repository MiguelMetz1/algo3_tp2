package edu.fiuba.algo3.Defenses.Builder;

import edu.fiuba.algo3.Attacker.DefenseAttacker.InConstructionAttacker;
import edu.fiuba.algo3.Attacker.Attacker;
import edu.fiuba.algo3.Attacker.DefenseAttacker.TowerAttacker;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Buff.Buff;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

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
        if( this.timeOfConstruction == 0 ){
            makeSound();
        }
        if( this.timeOfConstruction <= 0 ){
            return new TowerAttacker(debuff, position, range);
        }

        return new InConstructionAttacker();
    }

    public void makeSound(){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/java/edu/fiuba/algo3/View/Sounds/towerConstructed.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
