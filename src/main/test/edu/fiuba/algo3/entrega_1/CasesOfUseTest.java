package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Defenses.TowerSilver;
import edu.fiuba.algo3.Defenses.TowerWhite;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Interface.GameInterface;
import edu.fiuba.algo3.Plots.FinalGangway;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Turn.ComputerTurn;
import edu.fiuba.algo3.Turn.PlayerTurn;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.TypeData.Credits;
import org.junit.jupiter.api.Test;


/*
Caso de uso 1
●Verificar que jugador empieza con la vida y los créditos correspondientes.
Caso de uso 2
●Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
“operativas” cuando ya se terminaron de construir.
Caso de uso 3
●Verificar que se disponga de credito para realizar las construcciones.
Caso de uso 4
●Verificar solo se pueda construir defensas sobre tierra (y verificar lo contrario)
Caso de uso 5
●Verificar que las defensas ataquen dentro del rango esperado (y verificar lo contrario)
Caso de uso 6
●Verificar que las unidades enemigas son dañadas acorde al ataque recibido.
Caso de uso 7
●Verificar que las unidades enemigas solo se muevan por la parcela autorizada.
Caso de uso 8
●Verificar que al destruir una unidad enemiga, el jugador cobra el crédito que le
corresponde.

* */

import static org.junit.jupiter.api.Assertions.*;

public class CasesOfUseTest {

    @Test
    public void playerStartsWithCorrespondingCredits() {
        //Vida:20     Creditos:100


        for (int i = 0; i < 5; i++) {
            assertDoesNotThrow(() -> {
                Player.getPlayer().buy(new TowerSilver());
            }, "Insuficient Credits");
        }



        assertThrows(InsuficientCredits.class, () -> {
            Player.getPlayer().buy(new TowerSilver());
        });

        Player.resetplayer();
    }
    @Test
    public void playerStartsWithCorrespondingLife() {

        Spider spider = new Spider();


        for (int i=0;i<9;i++){
            spider.attack(Player.getPlayer());
            assertEquals(false, Player.getPlayer().isDead());
        }
        spider.attack(Player.getPlayer());

        assertEquals(true, Player.getPlayer().isDead());

        Player.resetplayer();
    }

    /*  Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
        “operativas” cuando ya se terminaron de construir.*/

    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        TowerWhite towerWhite = new TowerWhite();
        try {
            towerWhite.buy(new Credits(100));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        towerWhite.putIn(new Coordinate(1,3));
        //Intentar construir y que no tire error

        //assertThrows(CannotAttack.class,()->{towerWhite.attack();});
        assertDoesNotThrow(()->{towerWhite.build();},"The defense is under construction.");

        //assertDoesNotThrow(()->{towerWhite.attack();}, "An Attacker in construction cant attack.");
        assertThrows(CannotConstruction.class, ()->{towerWhite.build();});


    }

    @Test
    public void silverTowerConstructionTimeIsCorrect(){
        TowerSilver silverTower = new TowerSilver();
        try {
            silverTower.buy(new Credits(100));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        silverTower.putIn(new Coordinate(3,1));
        //Intentar construir y que no tire error

        //assertThrows(CannotAttack.class,()->{silverTower.attack();});
        assertDoesNotThrow(()->{silverTower.build();},"The defense is under construction.");

        //assertThrows(CannotAttack.class,()->{silverTower.attack();});
        assertDoesNotThrow(()->{silverTower.build();},"The defense is under construction.");

        //assertDoesNotThrow(() -> {silverTower.attack();}, "An Attacker in construction cant attack." );
        assertThrows(CannotConstruction.class, ()->{silverTower.build();});

        
    }

    //Verificar que se disponga de credito para realizar las construcciones

     @Test
     public void amountOfCreditsIsSuficientToBuy(){

        TowerSilver towerSilver = new TowerSilver();

        TowerWhite towerWhite = new TowerWhite();

        assertDoesNotThrow(()->{Player.getPlayer().buy(towerWhite);}, "Insuficient Credits");
        assertDoesNotThrow(()->{Player.getPlayer().buy(towerSilver);}, "Insuficient Credits");

         Player.resetplayer();
     }

     //Verificar solo se pueda construir defensas sobre tierra (y verificar lo contrario)

    @Test
    public void defensesCanOnlyBeBuiltOnGround(){
        TowerSilver towerSilver = new TowerSilver();

        try {
            Player.getPlayer().buy(towerSilver);
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        assertDoesNotThrow(() ->{towerSilver.putIn(new Coordinate(3,1));}, "Can't build in this plot.");
        assertThrows(CannotBuild.class, () ->  {towerSilver.putIn(new Coordinate(1,1));});
        assertThrows(CannotBuild.class, () ->  {towerSilver.putIn(new Coordinate(2,1));});

        Player.resetplayer();
    }

    @Test
    public void SilverTowerAttackEnemiesWithinTheExpectedRange(){

        TowerSilver towerSilver = new TowerSilver();


        try {
            towerSilver.buy(new Credits(10000));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        towerSilver.putIn(new Coordinate(1,6));

        try {
            towerSilver.build();
            towerSilver.build();

        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            towerSilver.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        GameMap.getMap().spawnEnemies();
        assertDoesNotThrow(() -> {towerSilver.attack();} , "There's no enemies arround");

        towerSilver.putIn(new Coordinate(1,7));
        assertThrows(EnemyNotFound.class, () -> {towerSilver.attack();});

        GameMap.resetMap();
    }
    @Test
    public void WhiteTowerAttackEnemiesWithinTheExpectedRange(){

        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10000));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(1,4));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            whiteTower.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThrows(EnemyNotFound.class, () -> {whiteTower.attack();});
        GameMap.getMap().spawnEnemies();
        whiteTower.putIn(new Coordinate(1,5));
        assertThrows(EnemyNotFound.class, () -> {whiteTower.attack();});

        GameMap.resetMap();

    }
    @Test
    public void enemiesAreDamagedAcordingToRecievesAttack(){

        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(1,4));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            //Mata dos hormigas
            whiteTower.attack();
            GameMap.getMap().spawnEnemies();
            whiteTower.attack();
            //Mata una arania
            whiteTower.attack();
            whiteTower.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThrows(EnemyNotFound.class,()-> {whiteTower.attack();});

        GameMap.resetMap();

    }

    //Verificar que las unidades enemigas solo se muevan por la parcela autorizada.
    @Test
    public void enemiesMoveToTheAuthorizedPlot(){
        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(2,8));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();

        assertThrows(EnemyNotFound.class, ()->{whiteTower.attack();});

        GameMap.getMap().advanceEnemies();
        GameMap.getMap().advanceEnemies();
        GameMap.getMap().advanceEnemies();
        GameMap.getMap().advanceEnemies();
        GameMap.getMap().advanceEnemies();
        GameMap.getMap().advanceEnemies();


        assertDoesNotThrow(()->{whiteTower.attack();},"There's no enemies arround");

        GameMap.resetMap();

    }

    @Test
    public void DestroyEnemiesGivesTheCorrectAmountOfCreditsToThePlayer(){

        TowerWhite whiteTower = new TowerWhite();
        try {
            whiteTower.buy(new Credits(10));
        } catch (InsuficientCredits e) {
            throw new RuntimeException(e);
        }

        whiteTower.putIn(new Coordinate(1,4));

        try {
            whiteTower.build();
        } catch (CannotConstruction e) {
            throw new RuntimeException(e);
        }

        GameMap.getMap().spawnEnemies();
        try {
            //Mata una hormigas
            whiteTower.attack();
            GameMap.getMap().spawnEnemies();
            //Mata una hormigas
            whiteTower.attack();
            //Mata una arania
            whiteTower.attack();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Credits expectedCredits = new Credits(2);

        Credits myCredits = new Credits(0);

        whiteTower.transferPickedCreditsTo(myCredits);

        assert(myCredits.sameCredits(expectedCredits));

        try {
            whiteTower.attack();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Credits newExpectedCredits = new Credits(13);
        whiteTower.transferPickedCreditsTo(myCredits);

        assert(newExpectedCredits.higherCredits(myCredits));

        GameMap.resetMap();
    }

    @Test
    public void whenPassingTurnEnemiesMoveAccordingToTheirSpeed(){
        GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();

        assert(GameMap.getMap().plotHasEnemies(new Coordinate(2,7)));

        GameMap.resetMap();
        Player.resetplayer();
    }



    @Test
    public void whenAllEnemiesAreDeadTheUserWinsTheGame() {
        Player.resetplayer();

        GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        gameInterface.build(new TowerSilver(), new Coordinate(3,2));
        gameInterface.build(new TowerSilver(), new Coordinate(3,3));
        gameInterface.build(new TowerSilver(), new Coordinate(3,4));
        gameInterface.build(new TowerSilver(), new Coordinate(4,8));
        gameInterface.build(new TowerWhite(), new Coordinate(5,8));
        gameInterface.build(new TowerWhite(), new Coordinate(6,8));
        gameInterface.build(new TowerWhite(), new Coordinate(7,8));
        gameInterface.build(new TowerWhite(), new Coordinate(3,8));
        gameInterface.build(new TowerWhite(), new Coordinate(10,8));
        gameInterface.build(new TowerWhite(), new Coordinate(11,7));
        gameInterface.build(new TowerWhite(), new Coordinate(12,8));
        gameInterface.build(new TowerWhite(), new Coordinate(12,9));
        gameInterface.build(new TowerWhite(), new Coordinate(12,10));
        gameInterface.build(new TowerWhite(), new Coordinate(12,11));
        gameInterface.build(new TowerWhite(), new Coordinate(12,12));
        gameInterface.build(new TowerWhite(), new Coordinate(12,13));
        gameInterface.build(new TowerWhite(), new Coordinate(12,14));
        gameInterface.build(new TowerWhite(), new Coordinate(12,15));

        for (int i = 0; i < 37; i++) {
            computerTurn.executeTurn();
        }

        assertEquals("You Won", gameInterface.gameWon());

        GameMap.resetMap();
        Player.resetplayer();
    }


    @Test
    public void whenNotAllEnemiesAreKilledTheUserStillWinsTheGame() {

        GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        gameInterface.build(new TowerSilver(), new Coordinate(3,2));
        gameInterface.build(new TowerSilver(), new Coordinate(3,3));
        gameInterface.build(new TowerSilver(), new Coordinate(3,4));
        gameInterface.build(new TowerSilver(), new Coordinate(4,8));
        gameInterface.build(new TowerWhite(), new Coordinate(5,8));
        gameInterface.build(new TowerWhite(), new Coordinate(6,8));
        gameInterface.build(new TowerWhite(), new Coordinate(7,8));
        gameInterface.build(new TowerWhite(), new Coordinate(3,8));
        gameInterface.build(new TowerWhite(), new Coordinate(10,8));
        gameInterface.build(new TowerWhite(), new Coordinate(11,7));

        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();
        computerTurn.executeTurn();

        assertEquals("You Won", gameInterface.gameWon());

        GameMap.resetMap();
        Player.resetplayer();

    }

    @Test
    public void whenEnemiesReachTheGoalAndKillThePlayerThePlayerDies() {

        GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        for (int i = 0; i < 37; i++) {
            computerTurn.executeTurn();
        }

        assertEquals("Game Over", gameInterface.gameWon());

        GameMap.resetMap();
        Player.resetplayer();

    }



}


