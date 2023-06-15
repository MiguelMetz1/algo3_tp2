package edu.fiuba.algo3.AlgoDefense;

import edu.fiuba.algo3.Interface.Game;


public class AlgoDefense {

    Game gameInterface;

    public void startGame(){
        this.gameInterface = new Game();
    }
}
