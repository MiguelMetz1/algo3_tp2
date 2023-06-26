package edu.fiuba.algo3.AlgoDefense;


import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Turn.ComputerTurn;
import edu.fiuba.algo3.Turn.PlayerTurn;
import edu.fiuba.algo3.View.GameStart;
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class AlgoDefense {

    private PrincipalContainer principalConteiner;
    PlayerTurn playerTurn;
    ComputerTurn computerTurn;

    Game game;


    public AlgoDefense(Game game){
        this.game = game;
        this.playerTurn = new PlayerTurn(game);
        this.computerTurn = new ComputerTurn(game);
    }

   /* public AlgoDefense(String[] args){
        this.game = new Game();
        this.playerTurn = new PlayerTurn(game);
        this.computerTurn = new ComputerTurn(game);
        this.args = args;
    }*/


   /* public AlgoDefense(PrincipalContainer principalConteiner) {
        this();
        this.principalConteiner = principalConteiner;

    }*/

    public void startGame(){

        while (this.game.gameWon().equals("In game.")){
            playerTurn.executeTurn();
            computerTurn.executeTurn();
        }
        System.out.println( ">>>>>>>>>>>>>Game finalized You "+game.gameWon()+"<<<<<<<<<<<<<<<<<<" );
        playerTurn.close();
    }


}
