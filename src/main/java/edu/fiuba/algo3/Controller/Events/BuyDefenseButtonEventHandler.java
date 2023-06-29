package edu.fiuba.algo3.Controller.Events;

import edu.fiuba.algo3.Model.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Model.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.View.Views.PrincipalContainer;
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
        } catch (InsufficientCredits e) {
            principalContainer.setBottomContainer("Your credits are insufficient to buy.");
        } catch (NonExistentArticle e) {
            throw new RuntimeException(e);
        }
        this.principalContainer.setUserInfoPanel(game);
    }

}