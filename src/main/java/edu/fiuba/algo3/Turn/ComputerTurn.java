package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Interface.ComputerInterface;
import edu.fiuba.algo3.Interface.GameInterface;

public class ComputerTurn implements Turn{

    private ComputerInterface computerInterface;

    //TODO computerInterface
    public ComputerTurn(ComputerInterface computerInterface){
        this.computerInterface = computerInterface;
    }

    public void executeTurn() {

        computerInterface.advanceEnemies();
        computerInterface.spawnEnemies();
        //computerInterface.makeDefensesAttack();
        computerInterface.buildDefenses();


    }
}
