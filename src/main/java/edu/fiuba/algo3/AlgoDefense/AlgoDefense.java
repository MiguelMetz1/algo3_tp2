package edu.fiuba.algo3.AlgoDefense;

import edu.fiuba.algo3.Interface.GameInterface;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Turn.Turn;

import java.util.ArrayList;


public class AlgoDefense {
    ArrayList<Turn> turns;

    GameInterface gameInterface;

    public void startGame(){
        this.gameInterface = new GameInterface(Player.getPlayer());
    }
}
