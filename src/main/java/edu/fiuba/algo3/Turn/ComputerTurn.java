package edu.fiuba.algo3.Turn;

import edu.fiuba.algo3.Interface.Game;

public class ComputerTurn implements Turn{

    Game game;

    public ComputerTurn(Game game) {
        this.game = game;
    }


    @Override
    public void executeTurn() {
        game.advanceEnemies();
        game.makeDefensesAttack();
        game.lootEnemies();
        game.buildDefenses();
    }
}
