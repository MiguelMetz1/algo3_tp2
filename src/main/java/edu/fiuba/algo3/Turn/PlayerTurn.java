package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Interface.GameInterface;
import edu.fiuba.algo3.Interface.PlayerInterface;
import edu.fiuba.algo3.TypeData.Coordinate;

public class PlayerTurn implements Turn {

    //TODO playerInterface
    PlayerInterface playerInterface;

    public PlayerTurn(PlayerInterface playerInterface){
        this.playerInterface = playerInterface;
    }

    public void executeTurn(){

    }
}
