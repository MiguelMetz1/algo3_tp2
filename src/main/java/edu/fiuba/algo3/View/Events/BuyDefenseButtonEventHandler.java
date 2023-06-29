package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.View.Paintable;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BuyDefenseButtonEventHandler implements EventHandler<ActionEvent> {

    Game game;

    String defense;

    PrincipalContainer principalContainer;
    public BuyDefenseButtonEventHandler(PrincipalContainer principalContainer, String defense, Game game){
        this.game = game;
        this.defense = defense;
        this.principalContainer = principalContainer;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            game.buyDefense(this.defense);
        } catch (InsufficientCredits e) {
            principalContainer.setBottomContainer("Your credits are insufficient to buy.");
        } catch (NonExistentArticle e) {
            throw new RuntimeException(e);
        }
        this.principalContainer.setUserInfoPanel(game);
    }

}