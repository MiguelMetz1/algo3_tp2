package edu.fiuba.algo3.classesTests;

import edu.fiuba.algo3.Model.Game.Game;
import edu.fiuba.algo3.Model.Turn.ComputerTurn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TurnTest {


    @Test
    public void computerTurnExecuteTheRightsCommands(){

        Game game = new Game("Fitzgerald");

        ComputerTurn computerTurn = new ComputerTurn(game);

        for(int i = 0; i < 36; i++)
            computerTurn.executeTurn();

        assertEquals("Lose." , game.gameWon());

    }

}
