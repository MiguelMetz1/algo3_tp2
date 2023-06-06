package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Interface.ComputerInterface;
import edu.fiuba.algo3.Interface.GameInterface;
import edu.fiuba.algo3.Players.Player;

public class ComputerTurn implements Turn{

    private ComputerInterface computerInterface;

    public ComputerTurn(ComputerInterface computerInterface){
        this.computerInterface = computerInterface;
    }

    public void executeTurn() {

        this.computerInterface.advanceEnemies();
        this.computerInterface.spawnEnemies();//<<<<<<<<<<<<
        this.computerInterface.makeDefensesAttack();
        this.computerInterface.buildDefenses();


    }
}
