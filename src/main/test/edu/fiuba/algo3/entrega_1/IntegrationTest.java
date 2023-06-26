package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @Test
    public void whenAllEnemiesAreDeadTheUserWinsTheGame() {
        Game game = new Game("Fitzgerald");

        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,1)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(1,3)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,2)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(2,8)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,3)));

        game.buildDefenses();
        game.buildDefenses();
        game.buildDefenses();

        for( int i = 1; i < 36; i++ ) {
            game.advanceEnemies();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
            game.makeDefensesAttack();

        }

        assertEquals("Won.", game.gameWon());

    }

    @Test
    public void whenNotAllEnemiesAreKilledTheUserStillWinsTheGame() {

        Game game = new Game("Fitzgerald");

        assertDoesNotThrow(()->game.buyDefense("White Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,3)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,4)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(2,8)));
        game.buildDefenses();
        game.buildDefenses();



        for( int i = 1; i < 11; i++ ) {
            game.advanceEnemies();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
            game.makeDefensesAttack();

        }


        for( int i = 0; i < 36; i++ ) {
            game.advanceEnemies();
            game.makeDefensesAttack();
        }

        assertEquals("Won.", game.gameWon());

    }

    @Test
    public void whenEnemiesReachTheGoalAndKillThePlayerThePlayerDies() {

        Game game = new Game("Fitzgerald");

        for( int i = 0; i < 36; i++ ) {
            game.advanceEnemies();
        }

        assertEquals("Lose.", game.gameWon());

    }

}

