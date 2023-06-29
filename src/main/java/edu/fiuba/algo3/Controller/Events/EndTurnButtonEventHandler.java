package edu.fiuba.algo3.Controller.Events;

import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.Model.Turn.ComputerTurn;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.Views.GameStart;
import edu.fiuba.algo3.View.Views.PrincipalContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Map;

public class EndTurnButtonEventHandler implements EventHandler<ActionEvent> {

    ComputerTurn computerTurn;

    Game game;

    Map<Coordinate, StackPane> stackPaneMap;

    PrincipalContainer principal;

    Stage stage;

    public EndTurnButtonEventHandler(Stage stage, PrincipalContainer principal,Game game, Map<Coordinate, StackPane> stackPaneMap){
        this.game = game;
        this.computerTurn = new ComputerTurn(game);
        this.stackPaneMap = stackPaneMap;
        this.principal = principal;
        this.stage = stage;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
       this.computerTurn.executeTurn();
        this.principal.updateMap();
        this.principal.setUserInfoPanel(game);
       Alert endGameAlert = new Alert(Alert.AlertType.INFORMATION);

       endGameAlert.setHeaderText("You " + this.game.gameWon());
       if(this.game.gameWon().equals("Lose.") || this.game.gameWon().equals("Won.") ){

           endGameAlert.showAndWait();

           GameStart gameStart = new GameStart();
           gameStart.start(this.stage);
           this.stage.setHeight(380);
           this.stage.setWidth(600);

       }
    }

}