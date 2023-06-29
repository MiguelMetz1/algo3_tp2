package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        } catch (NonExistentArticle e) {
            throw new RuntimeException(e);
        }
        this.principalContainer.setUserInfoPanel(game);
    }

}