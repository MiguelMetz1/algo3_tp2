package edu.fiuba.algo3.AlgoDefense;

import edu.fiuba.algo3.Interface.GameInterface;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Turn.Turn;

import java.util.ArrayList;


public class AlgoDefense {
    ArrayList<Turn> turns;

    GameInterface gameInterface;

    public void startGame(){
        String name = "Pepe";
        Player player = new Player("Juan");
        this.gameInterface = new GameInterface(player);
    }
}
