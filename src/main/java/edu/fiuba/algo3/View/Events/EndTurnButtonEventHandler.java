package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Turn.ComputerTurn;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EndTurnButtonEventHandler implements EventHandler<ActionEvent> {

    ComputerTurn computerTurn;

    public EndTurnButtonEventHandler(Game game){
        this.computerTurn = new ComputerTurn(game);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
       this.computerTurn.executeTurn();
    }

}