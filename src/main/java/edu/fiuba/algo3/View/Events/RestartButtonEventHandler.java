package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.View.GameStart;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class RestartButtonEventHandler implements EventHandler<ActionEvent> {

    Stage stage;
    public RestartButtonEventHandler(Stage stage){
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        GameStart gameStart = new GameStart();
        gameStart.start(this.stage);
    }

}
