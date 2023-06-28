package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import java.util.Map;

public class PlotButtonEventHandler implements EventHandler<ActionEvent> {

    private Plot plot;
    private VBox consoleContainer;

    Game game;

    Coordinate coordinate;

    Map<Coordinate, Button> buttonMap;
    Map<Coordinate, StackPane> stackPaneMap;

    PrincipalContainer principalContainer;

    Map<Coordinate, Plot> plots;

    Boolean clicked = false;

    public PlotButtonEventHandler(Map<Coordinate, Plot> plots,PrincipalContainer principalContainer,VBox consoleContainer, Plot plot, Game game, Coordinate coordinate, Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){
        this.principalContainer = principalContainer;
        this.consoleContainer = consoleContainer;
        this.plot = plot;
        this.coordinate = coordinate;
        this.game = game;
        this.buttonMap = buttonMap;
        this.stackPaneMap = stackPaneMap;
        this.plots = plots;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

        opacityReset(buttonMap);
        showRange( buttonMap,coordinate, Color.RED);


        try {
            game.locateLastBoughtDefenseIn(coordinate);
            //makeSound();
        } catch (WrongPlace e) {
            throw new RuntimeException(e);
        }

        game.defensesImage(buttonMap,stackPaneMap);



        principalContainer.showMap();



    }


    public void showRange( Map<Coordinate, Button> buttonMap, Coordinate coordinate, Color color){
        for (Map.Entry<Coordinate, Button> entry: buttonMap.entrySet()){
            Coordinate ownCoordinate = entry.getKey();
            Button button = entry.getValue();
            this.plots.get(coordinate).showRange(ownCoordinate,button);
        }
    }
    private void opacityReset(Map<Coordinate, Button> buttonMap) {
        for (Map.Entry<Coordinate, Button> entry : buttonMap.entrySet()) {
            Button button = entry.getValue();
            button.setStyle("-fx-opacity: 1");

        }
    }
}
