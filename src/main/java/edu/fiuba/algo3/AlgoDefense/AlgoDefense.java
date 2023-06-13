package edu.fiuba.algo3.AlgoDefense;

import edu.fiuba.algo3.Interface.GameInterface;


public class AlgoDefense {

    GameInterface gameInterface;

    public void startGame(){
        this.gameInterface = new GameInterface();
    }
}
