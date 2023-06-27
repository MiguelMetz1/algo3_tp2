package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.sound.sampled.*;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class PlotButtonEventHandler implements EventHandler<ActionEvent> {

    private Plot plot;
    private VBox consoleContainer;

    Game game;

    Coordinate coordinate;

    Map<Coordinate, Button> buttonMap;
    Map<Coordinate, StackPane> stackPaneMap;

    PrincipalContainer principalContainer;

    public PlotButtonEventHandler(PrincipalContainer principalContainer,VBox consoleContainer, Plot plot, Game game, Coordinate coordinate, Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){
        this.principalContainer = principalContainer;
        this.consoleContainer = consoleContainer;
        this.plot = plot;
        this.coordinate = coordinate;
        this.game = game;
        this.buttonMap = buttonMap;
        this.stackPaneMap = stackPaneMap;
    }


    @Override
    public void handle(ActionEvent actionEvent) {


        try {
            game.locateLastBoughtDefenseIn(coordinate);
            //makeSound();
        } catch (WrongPlace e) {
            throw new RuntimeException(e);
        }

        game.defensesImage(buttonMap,stackPaneMap);


        principalContainer.showMap();



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
