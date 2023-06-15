package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Interface.Game;

public class PlayerTurn implements Turn{

    private Game game;

    public PlayerTurn(Game game){
        this.game = game;
    }

    @Override
    public void executeTurn(){
        game.requireAction();
    }
}
