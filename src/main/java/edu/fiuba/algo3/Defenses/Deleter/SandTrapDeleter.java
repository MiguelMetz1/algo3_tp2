package edu.fiuba.algo3.Defenses.Deleter;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Plot;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SandTrapDeleter implements Deleter {
    Player player;

    public SandTrapDeleter( Player player){
        this.player = player;
    }

    @Override
    public void delete(Defense defense, Plot plot) {
        //makeSound();
        this.player.destroyDefense(defense);
        plot.remove(defense);
    }

    public void makeSound(){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/java/edu/fiuba/algo3/View/Sounds/sandSound.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
