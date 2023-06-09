package edu.fiuba.algo3.Model.Turn;

import edu.fiuba.algo3.Model.Game.Game;

public class ComputerTurn implements Turn{

    Game game;

    public ComputerTurn(Game game) {
        this.game = game;
    }


    @Override
    public void executeTurn() {
        game.makeEnemiesAttack();
        game.makeDefensesAttack();
        game.advanceEnemies();
        game.lootEnemies();
        game.buildDefenses();
    }
}
