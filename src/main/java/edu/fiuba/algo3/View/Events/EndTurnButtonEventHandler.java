package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Turn.ComputerTurn;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class EndTurnButtonEventHandler implements EventHandler<ActionEvent> {

    ComputerTurn computerTurn;

    Game game;

    Map<Coordinate, Button> buttonMap;

    Map<Coordinate, StackPane> stackPaneMap;

    PrincipalContainer principal;

    public EndTurnButtonEventHandler(PrincipalContainer principal,Game game, Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){
        this.game = game;
        this.computerTurn = new ComputerTurn(game);
        this.buttonMap = buttonMap;
        this.stackPaneMap = stackPaneMap;
        this.principal = principal;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
       this.computerTurn.executeTurn();
       this.game.enemiesImages(this.buttonMap, this.stackPaneMap);
       this.principal.showMap();
    }

}