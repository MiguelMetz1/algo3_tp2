package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Interface.PlayerInterface;

public class PlayerTurn implements Turn {

    //TODO playerInterface
    PlayerInterface playerInterface;

    public void executeTurn(){
        while(!playerInterface.turnChanged()){
            playerInterface.requireAction();
        }
    }
}
