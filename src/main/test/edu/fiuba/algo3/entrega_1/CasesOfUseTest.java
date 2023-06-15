package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.Defenses.WhiteTower;
import edu.fiuba.algo3.Enemies.Ant;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.Shop.SilverTowerProvider;
import edu.fiuba.algo3.Shop.WhiteTowerProvider;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.TypeData.Distance;
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

import java.util.LinkedList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

public class CasesOfUseTest {

    @Test
    public void playerStartsWithCorrespondingCredits() {
        //Vida:20     Creditos:100
        Player player = new Player("Lisandro");
        Shop shop = new Shop(player);
        assertDoesNotThrow(()->{shop.addArticle("White Tower", new WhiteTowerProvider());});
        assertDoesNotThrow(()->{shop.addArticle("Silver Tower", new SilverTowerProvider());});
        for (int i = 0; i < 10; i++) {
            assertDoesNotThrow(() -> {
                shop.buy("White Tower");
            }, "The player hasn't got sufficient credits.");
        }

        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("Silver Tower");
        });

    }
    @Test
    public void playerStartsWithCorrespondingLife() {

        GameMap map = new GameMap();

        Spider spider = new Spider(map, this.copyPath());
        PlayerCharacter playerCharacter = new PlayerCharacter();
        for (int i=0;i<10;i++){
            spider.attack(playerCharacter);
        }

        assertEquals( true, !playerCharacter.distanceToBiggerThan(new HellsPlot(), new Distance(0)));

    }

    /*  Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
        “operativas” cuando ya se terminaron de construir.*/

    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        GameMap map = new GameMap();
        Spider spider = new Spider(map, this.copyPath());
        WhiteTower tower = new WhiteTower();
        assertDoesNotThrow(()->{map.locateEntityIn(tower, new Coordinate(3,1));});
        spider.advance();
        spider.advance();
        tower.attack(spider);
        tower.attack(spider);
        assertEquals( true, spider.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        tower.continueWithTheConstruction();
        tower.attack(spider);
        tower.attack(spider);
        assertEquals( false, spider.distanceToBiggerThan(new HellsPlot(), new Distance(0)));

    }

    @Test
    public void silverTowerConstructionTimeIsCorrect(){
        GameMap map = new GameMap();
        Spider spider = new Spider(map, this.copyPath());
        SilverTower tower = new SilverTower();
        assertDoesNotThrow(()->{map.locateEntityIn(tower, new Coordinate(3,1));});
        spider.advance();
        spider.advance();
        tower.attack(spider);
        tower.attack(spider);
        assertEquals( true, spider.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        tower.continueWithTheConstruction();
        tower.attack(spider);
        tower.attack(spider);
        assertEquals( true, spider.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        tower.continueWithTheConstruction();
        tower.attack(spider);
        tower.attack(spider);
        assertEquals( false, spider.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        
    }

    //Verificar que se disponga de credito para realizar las construcciones

     @Test
     public void amountOfCreditsIsSuficientToBuy(){

         Player player = new Player("Lisandro");
         Shop shop = new Shop(player);
         assertDoesNotThrow(()->{shop.addArticle("White Tower", new WhiteTowerProvider());});
         assertDoesNotThrow(()->{shop.addArticle("Silver Tower", new SilverTowerProvider());});
         assertDoesNotThrow(()->{shop.buy("White Tower");});
         assertDoesNotThrow(()->{shop.buy("Silver Tower");});

     }

     //Verificar solo se pueda construir defensas sobre tierra (y verificar lo contrario)

    @Test
    public void defensesCanOnlyBeBuiltOnGround(){
        WhiteTower whiteTower = new WhiteTower();
        SilverTower silverTower = new SilverTower();
        assertDoesNotThrow(()->{silverTower.locateIn(new Ground(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{silverTower.locateIn(new Rocky(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{silverTower.locateIn(new Gangway(new Coordinate(0,0)));});
        assertDoesNotThrow(()->{whiteTower.locateIn(new Ground(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{whiteTower.locateIn(new Rocky(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{whiteTower.locateIn(new Gangway(new Coordinate(0,0)));});
    }

    @Test
    public void TheTowersAttackEnemiesWithinTheExpectedRange(){

        GameMap map = new GameMap();
        Spider spiderA = new Spider(map, this.copyPath());
        Spider spiderB = new Spider(map, this.copyPath());
        Spider spiderC = new Spider(map, this.copyPath());
        assertDoesNotThrow(()->{map.locateEntityIn(spiderA, new Coordinate(2,1));});
        assertDoesNotThrow(()->{map.locateEntityIn(spiderB, new Coordinate(2,7));});
        assertDoesNotThrow(()->{map.locateEntityIn(spiderC, new Coordinate(15,11));});
        SilverTower silverTower = new SilverTower();
        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(()->{map.locateEntityIn(silverTower, new Coordinate(3,1));});
        assertDoesNotThrow(()->{map.locateEntityIn(whiteTower, new Coordinate(2,8));});
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(spiderA);
        whiteTower.attack(spiderA);
        assertEquals( true, spiderA.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        silverTower.attack(spiderA);
        assertEquals( false, spiderA.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        silverTower.attack(spiderB);
        assertEquals( true, spiderB.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        whiteTower.attack(spiderB);
        whiteTower.attack(spiderB);
        assertEquals( false, spiderB.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        whiteTower.attack(spiderB);
        whiteTower.attack(spiderB);
        assertEquals( false, spiderB.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        whiteTower.attack(spiderC);
        whiteTower.attack(spiderC);
        assertEquals( true, spiderC.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        silverTower.attack(spiderC);
        assertEquals( true, spiderC.distanceToBiggerThan(new HellsPlot(), new Distance(0)));

    }

    @Test
    public void enemiesAreDamagedAcordingToRecievesAttack(){

        GameMap map = new GameMap();
        Spider spiderA = new Spider(map, this.copyPath());
        Spider spiderB = new Spider(map, this.copyPath());
        Spider spiderC = new Spider(map, this.copyPath());
        assertDoesNotThrow(()->{map.locateEntityIn(spiderA, new Coordinate(2,1));});
        assertDoesNotThrow(()->{map.locateEntityIn(spiderB, new Coordinate(2,7));});
        assertDoesNotThrow(()->{map.locateEntityIn(spiderC, new Coordinate(15,11));});
        SilverTower silverTower = new SilverTower();
        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(()->{map.locateEntityIn(silverTower, new Coordinate(3,1));});
        assertDoesNotThrow(()->{map.locateEntityIn(whiteTower, new Coordinate(2,8));});
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();
        whiteTower.continueWithTheConstruction();
        silverTower.attack(spiderA);
        assertEquals( false, spiderA.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        whiteTower.attack(spiderB);
        assertEquals( true, spiderB.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
        whiteTower.attack(spiderB);
        assertEquals( false, spiderB.distanceToBiggerThan(new HellsPlot(), new Distance(0)));
    }

    //Verificar que las unidades enemigas solo se muevan por la parcela autorizada.
    @Test
    public void enemiesMoveToTheAuthorizedPlot(){
        GameMap map = new GameMap();
        Spider spider = new Spider(map, this.copyPath());
        Ground ground = new Ground(new Coordinate(0,0));
        Rocky rocky = new Rocky(new Coordinate(0,0));
        Gangway gangway = new Gangway(new Coordinate(0,0));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(0,0));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(0,0));

        assertThrows( WrongPlace.class ,()->{ spider.locateIn(ground); } );
        assertThrows( WrongPlace.class ,()->{ spider.locateIn(rocky); } );
        assertDoesNotThrow( ()->{ spider.locateIn(gangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn(finalGangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn(initialGangway); } );
    }


    //TODO ARREGLAR LA ENTREGA DE CREDITOS AL JUGADOR
    @Test
    public void DestroyEnemiesGivesTheCorrectAmountOfCreditsToThePlayer(){

        WhiteTower whiteTower = new WhiteTower();
        GameMap map = new GameMap();
        assertDoesNotThrow(()->{ map.locateEntityIn(whiteTower, new Coordinate(3,1)); });
        whiteTower.continueWithTheConstruction();
        Player player = new Player("Lisandro");
        Shop shop = new Shop(player);
        assertDoesNotThrow(()->{shop.addArticle("White Tower", new WhiteTowerProvider());});
        assertDoesNotThrow(()->{shop.addArticle("Silver Tower", new SilverTowerProvider());});
        for( int i = 0; i < 10; i++) {
            Ant ant = new Ant(map,this.copyPath());
            ant.advance();
            whiteTower.attack(ant);
            ant.transferLootTo(player);
        }

        for (int i = 0; i < 11; i++) {
            assertDoesNotThrow(() -> {
                shop.buy("White Tower");
            });
        }

    }

    @Test
    public void whenPassingTurnEnemiesMoveAccordingToTheirSpeed(){
        GameMap map = new GameMap();
        Ant ant = new Ant(map, this.copyPath());
        Spider spider = new Spider(map, this.copyPath());
        ant.advance();
        spider.advance();
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        assertEquals( false, ant.distanceToBiggerThan(initialGangway, new Distance(0)));
        assertEquals( false, spider.distanceToBiggerThan(initialGangway, new Distance(0)));
        spider.advance();
        Gangway gangway = new Gangway(new Coordinate(2,3));
        assertEquals( false, spider.distanceToBiggerThan(gangway, new Distance(0)));
        ant.advance();
        Gangway antGangway = new Gangway(new Coordinate(2,2));
        assertEquals( false, ant.distanceToBiggerThan(antGangway, new Distance(0)));
    }

    public LinkedList<Coordinate> getPath(){
        LinkedList<Coordinate> path = new LinkedList<>();
        path.add(new Coordinate(2,1));
        path.add(new Coordinate(2,2));
        path.add(new Coordinate(2,3));
        path.add(new Coordinate(2,4));
        path.add(new Coordinate(2,5));
        path.add(new Coordinate(2,6));
        path.add(new Coordinate(2,7));
        path.add(new Coordinate(3,7));
        path.add(new Coordinate(4,7));
        path.add(new Coordinate(5,7));
        path.add(new Coordinate(6,7));
        path.add(new Coordinate(7,7));
        path.add(new Coordinate(8,7));
        path.add(new Coordinate(9,7));
        path.add(new Coordinate(9,8));
        path.add(new Coordinate(9,9));
        path.add(new Coordinate(9,10));
        path.add(new Coordinate(9,11));
        path.add(new Coordinate(10,11));
        path.add(new Coordinate(11,11));
        path.add(new Coordinate(12,11));
        path.add(new Coordinate(13,11));
        path.add(new Coordinate(14,11));
        path.add(new Coordinate(15,11));
        return path;
    }

    public LinkedList<Coordinate> copyPath(){
        LinkedList<Coordinate> path = this.getPath();
        ListIterator<Coordinate> iterator = path.listIterator(0);
        LinkedList<Coordinate> newPath = new LinkedList<>();
        while (iterator.hasNext()){
            newPath.add(iterator.next());
        }
        return newPath;
    }

    @Test
    public void whenAllEnemiesAreDeadTheUserWinsTheGame() {

    }


    /*@Test
    public void whenNotAllEnemiesAreKilledTheUserStillWinsTheGame() {

        GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        GameMap.getMap().locateEntityIn(new SilverTower(), new Coordinate(3,2));
        GameMap.getMap().locateEntityIn(new SilverTower(), new Coordinate(3,3));
        GameMap.getMap().locateEntityIn(new SilverTower(), new Coordinate(3,4));
        GameMap.getMap().locateEntityIn(new SilverTower(), new Coordinate(4,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(5,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(6,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(7,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(3,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(10,8));
        GameMap.getMap().locateEntityIn(new WhiteTower(), new Coordinate(11,7));

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
        Player.resetPlayer();

    }*/

    @Test
    public void whenEnemiesReachTheGoalAndKillThePlayerThePlayerDies() {

        /*GameInterface gameInterface = new GameInterface(Player.getPlayer());

        ComputerTurn computerTurn = new ComputerTurn(gameInterface);

        for (int i = 0; i < 37; i++) {
            computerTurn.executeTurn();
        }

        assertEquals("Game Over", gameInterface.gameWon());

        GameMap.resetMap();
        Player.resetPlayer();*/

    }



}


