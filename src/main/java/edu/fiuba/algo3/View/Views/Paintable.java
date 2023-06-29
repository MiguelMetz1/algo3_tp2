package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;

public abstract class Paintable {
    protected AnchorPane grid;

    protected ImageView towerImageView;

    protected ArrayList<Double> positions;

    public Paintable(AnchorPane grid, Coordinate coordinate) {
        this.grid = grid;
        positions = new ArrayList<>();
        coordinate.fillPositions(positions);
    }

    public void paint(){
        this.towerImageView = new ImageView(getPaintableImageView());
        towerImageView.setLayoutX( positions.get(0) * 45 );
        towerImageView.setLayoutY( positions.get(1) * 45 );
        towerImageView.setFitHeight(45);
        towerImageView.setFitWidth(45);
        this.grid.getChildren().add(towerImageView);
    }

    protected void makeSound(){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(entitySound()).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void erasePaintables(){
        this.grid.getChildren().remove(this.towerImageView);
    }

    protected abstract String getPaintableImageView();

    protected abstract String entitySound();



}
