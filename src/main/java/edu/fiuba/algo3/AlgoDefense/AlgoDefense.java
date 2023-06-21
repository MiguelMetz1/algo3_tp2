package edu.fiuba.algo3.AlgoDefense;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Turn.ComputerTurn;
import edu.fiuba.algo3.Turn.PlayerTurn;


public class AlgoDefense {

    PlayerTurn playerTurn;
    ComputerTurn computerTurn;

    Game game;

    public AlgoDefense(){
        this.game = new Game();
        this.playerTurn = new PlayerTurn(game);
        this.computerTurn = new ComputerTurn(game);
    }

    public void startGame(){
        while (this.game.gameWon().equals("In game.")){
            playerTurn.executeTurn();
            computerTurn.executeTurn();
        }
        System.out.println( ">>>>>>>>>>>>>Game finalized You "+game.gameWon()+"<<<<<<<<<<<<<<<<<<" );
        playerTurn.close();
    }
}
