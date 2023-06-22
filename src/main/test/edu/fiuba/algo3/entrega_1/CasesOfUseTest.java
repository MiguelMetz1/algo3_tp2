package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.Defenses.SilverTower;
import edu.fiuba.algo3.Defenses.WhiteTower;
import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.Shop.SilverTowerProvider;
import edu.fiuba.algo3.Shop.WhiteTowerProvider;
import edu.fiuba.algo3.TypeData.Coordinate;
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

import java.util.ArrayList;
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

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();
        Spider spider = new Spider(map, this.copyPath());
        enemies.add(spider);
        PlayerCharacter playerCharacter = new PlayerCharacter("Fabricio", map, resources.getPlayerCharacterCoordinate());
        assertDoesNotThrow(()->map.locateEntityIn(spider, resources.getPlayerCharacterCoordinate()));
        ArrayList<PlayerCharacter> players = new ArrayList<>();
        players.add(playerCharacter);
        for (int i=0;i<10;i++){
            spider.attack(players);
        }

        assertEquals( "Lose.", playerCharacter.won());

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

    /*  Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
        “operativas” cuando ya se terminaron de construir.*/

    @Test
    public void whiteTowerConstructionTimeIsCorrect(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Spider spider = new Spider(map, this.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        WhiteTower tower = new WhiteTower();
        assertDoesNotThrow(()->{map.locateEntityIn(tower, new Coordinate(3,1));});
        spider.advance();
        spider.advance();
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(spider);
        tower.attack(enemies);
        tower.attack(enemies);
        spider.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spider));
        tower.continueWithTheConstruction();
        tower.attack(enemies);
        tower.attack(enemies);
        spider.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spider));

    }

    @Test
    public void silverTowerConstructionTimeIsCorrect(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Spider spider = new Spider(map, this.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        SilverTower tower = new SilverTower();
        assertDoesNotThrow(()->{map.locateEntityIn(tower, new Coordinate(3,1));});
        spider.advance();
        spider.advance();
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(spider);
        tower.attack(enemies);
        tower.attack(enemies);
        spider.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spider));
        tower.continueWithTheConstruction();
        tower.attack(enemies);
        tower.attack(enemies);
        spider.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spider));
        tower.continueWithTheConstruction();
        tower.attack(enemies);
        tower.attack(enemies);
        spider.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spider));
        
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
        assertDoesNotThrow(()->{silverTower.locateIn(new Coordinate(0,0), new Ground(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{silverTower.locateIn(new Coordinate(0,0), new Rocky(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{silverTower.locateIn(new Coordinate(0,0), new Gangway(new Coordinate(0,0)));});
        assertDoesNotThrow(()->{whiteTower.locateIn(new Coordinate(0,0), new Ground(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{whiteTower.locateIn(new Coordinate(0,0), new Rocky(new Coordinate(0,0)));});
        assertThrows(WrongPlace.class, ()->{whiteTower.locateIn(new Coordinate(0,0), new Gangway(new Coordinate(0,0)));});
    }

    @Test
    public void TheTowersAttackEnemiesWithinTheExpectedRange(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Spider spiderA = new Spider(map, this.copyPath());
        Spider spiderB = new Spider(map, this.copyPath());
        Spider spiderC = new Spider(map, this.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
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

        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(spiderA);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        spiderA.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spiderA));
        silverTower.attack(enemies);
        spiderA.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spiderA));
        enemies.add(spiderB);
        silverTower.attack(enemies);
        spiderB.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spiderB));
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        spiderB.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spiderB));
        enemies.add(spiderC);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        spiderC.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spiderC));
        silverTower.attack(enemies);
        spiderC.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spiderC));

    }

    @Test
    public void enemiesAreDamagedAcordingToRecievesAttack(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
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
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        enemies.add(spiderA);
        silverTower.attack(enemies);
        spiderA.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spiderA));
        enemies.add(spiderB);
        whiteTower.attack(enemies);
        spiderB.die(deadEnemies);
        assertEquals( false, deadEnemies.contains(spiderB));
        whiteTower.attack(enemies);
        spiderB.die(deadEnemies);
        assertEquals( true, deadEnemies.contains(spiderB));
    }

    //Verificar que las unidades enemigas solo se muevan por la parcela autorizada.
    @Test
    public void enemiesMoveToTheAuthorizedPlot(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Spider spider = new Spider(map, this.copyPath());
        Ground ground = new Ground(new Coordinate(0,0));
        Rocky rocky = new Rocky(new Coordinate(0,0));
        Gangway gangway = new Gangway(new Coordinate(0,0));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(0,0));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(0,0));

        assertThrows( WrongPlace.class ,()->{ spider.locateIn( new Coordinate(0,0), ground ); } );
        assertThrows( WrongPlace.class ,()->{ spider.locateIn(new Coordinate(0,0), rocky); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(0,0), gangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(0,0), finalGangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(0,0), initialGangway); } );
    }


    //TODO ARREGLAR LA ENTREGA DE CREDITOS AL JUGADOR
    @Test
    public void DestroyEnemiesGivesTheCorrectAmountOfCreditsToThePlayer(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(()->{ map.locateEntityIn(whiteTower, new Coordinate(3,1)); });
        whiteTower.continueWithTheConstruction();
        Player player = new Player("Lisandro");
        Shop shop = new Shop(player);
        assertDoesNotThrow(()->{shop.addArticle("White Tower", new WhiteTowerProvider());});
        assertDoesNotThrow(()->{shop.addArticle("Silver Tower", new SilverTowerProvider());});
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        for( int i = 0; i < 10; i++) {
            Ant ant = new Ant(map,this.copyPath());
            ant.advance();
            enemies.add(ant);
            whiteTower.attack(enemies);
            ant.transferLootTo(player);
            ant.die(deadEnemies);
            enemies.removeAll(deadEnemies);
        }

        for (int i = 0; i < 11; i++) {
            assertDoesNotThrow(() -> {
                shop.buy("White Tower");
            });
        }

    }

    @Test
    public void whenPassingTurnEnemiesMoveAccordingToTheirSpeed(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Ant ant = new Ant(map, this.copyPath());
        Spider spider = new Spider(map, this.copyPath());
        ant.advance();
        spider.advance();
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        assertEquals( false, ant.distanceToBiggerThan(new Coordinate(2,1), new Distance(0)));
        assertEquals( false, spider.distanceToBiggerThan(new Coordinate(2,1), new Distance(0)));
        spider.advance();
        assertEquals( false, spider.distanceToBiggerThan(new Coordinate(2,3), new Distance(0)));
        ant.advance();
        assertEquals( false, ant.distanceToBiggerThan(new Coordinate(2,2), new Distance(0)));
    }

    @Test
    public void whenAllEnemiesAreDeadTheUserWinsTheGame() {
        Game game = new Game();

        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,1)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(1,3)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,2)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(1,2)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,3)));
        game.buildDefenses();
        game.buildDefenses();

        for( int i = 1; i < 30; i++ ) {
            game.advanceEnemies();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
        }

        assertEquals("Won.", game.gameWon());

    }



    public void owlMovement(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Owl owl = new Owl( map, this.owlPath(), resources.getPath().getLast() );
        SilverTower tower = new SilverTower();
        try {
            map.locateEntityIn(tower, new Coordinate(3,1));
            tower.continueWithTheConstruction();
            tower.continueWithTheConstruction();
        } catch (WrongPlace e) {
            throw new RuntimeException(e);
        }
        owl.advance();
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(owl);
        tower.attack(enemies);
        tower.attack(enemies);
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
    }

    public LinkedList<Coordinate> owlPath(){
        LinkedList<Coordinate> owlPath = new LinkedList<>();

        owlPath.add( new Coordinate( 2, 1));
        owlPath.add( new Coordinate( 2, 2));
        owlPath.add( new Coordinate( 2, 3));
        owlPath.add( new Coordinate( 2, 4));
        owlPath.add( new Coordinate( 2, 5));
        owlPath.add( new Coordinate( 2, 6));
        owlPath.add( new Coordinate( 2, 7));
        owlPath.add( new Coordinate( 2, 8));
        owlPath.add( new Coordinate( 2, 9));
        owlPath.add( new Coordinate( 2, 10));
        owlPath.add( new Coordinate( 2, 11));
        owlPath.add( new Coordinate( 3, 11));
        owlPath.add( new Coordinate( 4, 11));
        owlPath.add( new Coordinate( 5, 11));
        owlPath.add( new Coordinate( 6, 11));
        owlPath.add( new Coordinate( 7, 11));
        owlPath.add( new Coordinate( 8, 11));
        owlPath.add( new Coordinate( 9, 11));
        owlPath.add( new Coordinate( 10, 11));
        owlPath.add( new Coordinate( 11, 11));
        owlPath.add( new Coordinate( 12, 11));
        owlPath.add( new Coordinate( 13, 11));
        owlPath.add( new Coordinate( 14, 11));
        owlPath.add( new Coordinate( 15, 11));

        return owlPath;
    }


    @Test
    public void whenNotAllEnemiesAreKilledTheUserStillWinsTheGame() {

        Game game = new Game();

        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,1)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(1,3)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,2)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(1,2)));
        assertDoesNotThrow(()->game.buyDefense("Silver Tower"));
        assertDoesNotThrow(()->game.locateLastBoughtDefenseIn(new Coordinate(3,3)));
        game.buildDefenses();
        game.buildDefenses();

        for( int i = 1; i < 11; i++ ) {
            game.advanceEnemies();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
            game.makeDefensesAttack();
        }

        for( int i = 0; i < 36; i++ ) {
            game.advanceEnemies();
        }

        assertEquals("Won.", game.gameWon());

    }

    @Test
    public void whenEnemiesReachTheGoalAndKillThePlayerThePlayerDies() {

        Game game = new Game();

        for( int i = 0; i < 36; i++ ) {
            game.advanceEnemies();
        }

        assertEquals("Lose.", game.gameWon());

    }


}


