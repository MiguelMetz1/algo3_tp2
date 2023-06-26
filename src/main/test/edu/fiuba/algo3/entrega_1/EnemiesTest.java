package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.Shop.Provider.SilverTowerProvider;
import edu.fiuba.algo3.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Name.Name;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class EnemiesTest {

    @Test
    public void antsSpawnWithTheCorrectPointsOfLife() throws WrongPlace {
        /* Ants have one point of life. After one shot of white tower the ant should die */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.locateIn((new Coordinate(2,2)), new Gangway(new Coordinate(2,2)));
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void spidersSpawnWithTheCorrectPointsOfLife() throws WrongPlace {
        /* Spiders have two points of life. After two hits of white tower the spider should die */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Spider spider = new Spider(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(spider);
        spider.locateIn((new Coordinate(2,2)), new Gangway(new Coordinate(2,2)));
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        spider.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(spider));

    }

    @Test
    public void owlsSpawnWithTheCorrectPointsOfLife() throws WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        Path path = new Path();
        Owl owl = new Owl(map, path.copyPath(), playerCoordinate);

        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(owl);

        owl.locateIn((new Coordinate(2,2)), new Gangway(new Coordinate(2,2)));
        whiteTower.continueWithTheConstruction();

        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);


        owl.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(owl));

    }

    @Test

    public void antsAdvanceCorrectly(){


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());

        ant.advance();

        ant.advance();
        boolean isPosition1Correct = ant.distanceToBiggerThan(new Coordinate(2,2), new Distance(0));
        assertFalse(isPosition1Correct);

        ant.advance();
        boolean isPosition2Correct = ant.distanceToBiggerThan(new Coordinate(2,3), new Distance(0));
        assertFalse(isPosition2Correct);


    }

    @Test
    public void spidersAdvanceCorrectly(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Spider spider = new Spider(map, path.copyPath());

        spider.advance();

        spider.advance();
        boolean isPosition1Correct = spider.distanceToBiggerThan(new Coordinate(2,3), new Distance(0));
        assertFalse(isPosition1Correct);


        spider.advance();
        boolean isPosition2Correct = spider.distanceToBiggerThan(new Coordinate(2,5), new Distance(0));
        assertFalse(isPosition2Correct);

    }

    @Test
    public void owlsMoreThan50PercentLifeAdvanceCorrectly() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        OwlPath owlPath = new OwlPath();
        Owl owl = new Owl(map, owlPath.owlPath(), playerCoordinate);
        owl.advance();

        owl.advance();

        boolean isPosition1Correct = owl.distanceToBiggerThan(new Coordinate(2,6), new Distance(0));
        assertFalse(isPosition1Correct);

        owl.advance();
        boolean isPosition2Correct = owl.distanceToBiggerThan(new Coordinate(2,11), new Distance(0));
        assertFalse(isPosition2Correct);

        owl.advance();
        boolean isPosition3Correct = owl.distanceToBiggerThan(new Coordinate(7,11), new Distance(0));
        assertFalse(isPosition3Correct);


    }

    @Test
    public void owlsLessThan50PercentLifeAdvanceCorrectly(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        OwlPath owlPath = new OwlPath();
        Owl owl = new Owl(map, owlPath.owlPath(), playerCoordinate);
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(owl);

        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(() -> map.locateEntityIn(whiteTower, new Coordinate(3,1)));
        whiteTower.continueWithTheConstruction();

        owl.advance();
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);

        Coordinate startCoordinate = new Coordinate(2,1 );
        Coordinate finalCoordinate = new Coordinate(15,11);

        Coordinate actualPosition = startCoordinate.nextCoordinateInDirectionWithDistance(finalCoordinate, new Distance(5));

        owl.advance();
        boolean isPosition1Correct = owl.distanceToBiggerThan(actualPosition, new Distance(0));
        assertFalse(isPosition1Correct);

        owl.advance();
        actualPosition = actualPosition.nextCoordinateInDirectionWithDistance(finalCoordinate, new Distance(5));
        boolean isPosition2Correct = owl.distanceToBiggerThan(actualPosition, new Distance(0));
        assertFalse(isPosition2Correct);

        owl.advance();
        actualPosition = actualPosition.nextCoordinateInDirectionWithDistance(finalCoordinate, new Distance(5));
        boolean isPosition3Correct = owl.distanceToBiggerThan(actualPosition, new Distance(0));
        assertFalse(isPosition3Correct);

        owl.advance();
        actualPosition = actualPosition.nextCoordinateInDirectionWithDistance(finalCoordinate, new Distance(5));
        boolean isPosition4Correct = owl.distanceToBiggerThan(finalCoordinate, new Distance(0));
        assertFalse(isPosition4Correct);




    }



    @Test
    public void molesAdvanceCorrectly() throws WrongPlace {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Mole mole = new Mole(map, path.copyPath());

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,9));

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(mole);

        mole.advance();
        boolean isPosition1Correct = mole.distanceToBiggerThan(new Coordinate(2,1), new Distance(0));
        assertFalse(isPosition1Correct);

        mole.advance();
        mole.advance();
        mole.advance();
        mole.advance();
        mole.advance();
        boolean isPosition2Correct = mole.distanceToBiggerThan(new Coordinate(2,6), new Distance(0));
        assertFalse(isPosition2Correct);

        mole.advance();
        boolean isPosition3Correct = mole.distanceToBiggerThan(new Coordinate(3,7), new Distance(0));
        assertFalse(isPosition3Correct);

        mole.advance();
        mole.advance();
        mole.advance();
        mole.advance();
        boolean isPosition4Correct = mole.distanceToBiggerThan(new Coordinate(9,9), new Distance(0));
        assertFalse(isPosition4Correct);

        mole.advance();
        boolean isPosition5Correct = mole.distanceToBiggerThan(new Coordinate(10,11), new Distance(0));
        assertFalse(isPosition5Correct);
    }

    @Test
    public void molesDoTheCorrectDamage(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Mole mole = new Mole(map, new Path().copyPath());

        enemies.add(mole);
        players.add(player);

        for (int i = 0;i < 15; i++)
            mole.advance();

        for(int i = 0; i < 4; i++)
            mole.attack(players);

        assertEquals( "Lose.", player.won());
    }

    @Test
    public void spidersDoTheCorrectDamage() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Spider spider = new Spider(map, new Path().copyPath());


        enemies.add(spider);
        players.add(player);


        for (int i = 0;i < 13; i++)
            spider.advance();

        for(int i = 0; i < 10; i++)
            spider.attack(players);


        assertEquals( "Lose.", player.won());

    }

    @Test
    public void antsDoTheCorrectDamage() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Ant ant = new Ant(map, new Path().copyPath());


        enemies.add(ant);
        players.add(player);


        for (int i = 0;i < 24; i++)
            ant.advance();

        for(int i = 0; i < 20; i++)
            ant.attack(players);


        assertEquals( "Lose.", player.won());

    }

    @Test
    public void owlsDestroyTheFirstTowerIfAttackThePlayer() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Owl owl = new Owl(map, new OwlPath().owlPath(), resources.getPath().getLast());
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Spider spider = new Spider(map, new Path().copyPath());
        Ant ant = new Ant(map, new Path().copyPath());


        enemies.add(spider);


        players.add(player);

        SilverTower silverTower = new SilverTower();
        assertDoesNotThrow( ()->map.locateEntityIn(silverTower, new Coordinate(3,1)) );
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();
        player.addDefense(silverTower);

        spider.advance();

        player.makeDefensesAttack();

        assertEquals( "Won.", player.won());

        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.attack(players);

        enemies.add(ant);
        enemies.add(owl);

        player.makeDefensesAttack();
        assertEquals("In game.", player.won());
    }

    @Test
    public void owlsAttackThePlayerButCantDestroyTowersIfThereIsNoTowersInTheMap(){
        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Owl owl = new Owl(map, new OwlPath().owlPath(), resources.getPath().getLast());
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Spider spider = new Spider(map, new Path().copyPath());

        enemies.add(spider);

        players.add(player);

        spider.advance();

        player.makeDefensesAttack();

        assertEquals( "In game.", player.won());

        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.advance();
        owl.attack(players);

        enemies.add(owl);

        player.makeDefensesAttack();
        assertEquals("In game.", player.won());

    }


    @Test
    public void spidersGivesTheCorrectAmountOfCredits() throws InsuficientCredits, NonExistentArticle, WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);


        players.add(player);

        Shop shop = new Shop(player);
        shop.addArticle("Silver Tower", new SilverTowerProvider());
        shop.addArticle("White Tower", new WhiteTowerProvider());

        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(3, 1));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 3));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 4));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 5));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 6));

        player.buildDefenses();
        player.buildDefenses();

        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("Silver Tower");
        });

        for (int i = 0; i < 10; i++) {
            Spider spider = new Spider(map, new Path().getPath());
            enemies.add(spider);
            spider.advance();
            player.makeDefensesAttack();
            spider.transferLootTo(player);
            spider.finalizeYourWay(deadEnemies);
            enemies.removeAll(deadEnemies);

        }

        assertDoesNotThrow(()->shop.buy("White Tower"));
        player.locateLastDefense(new Coordinate(3, 3));
        player.buildDefenses();


    }

    @Test
    public void antsGivesTheCorrectAmountOfCredits() throws InsuficientCredits, NonExistentArticle, WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        players.add(player);

        Shop shop = new Shop(player);
        shop.addArticle("Silver Tower", new SilverTowerProvider());
        shop.addArticle("White Tower", new WhiteTowerProvider());

        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(3, 1));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 3));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 4));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 5));
        shop.buy("Silver Tower");
        player.locateLastDefense(new Coordinate(1, 6));

        player.buildDefenses();
        player.buildDefenses();

        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("Silver Tower");
        });



        for (int i = 0; i < 10; i++) {
            Ant ant = new Ant(map, new Path().getPath());
            enemies.add(ant);
            ant.advance();
            player.makeDefensesAttack();
            ant.transferLootTo(player);
            ant.finalizeYourWay(deadEnemies);
            enemies.removeAll(deadEnemies);

        }

        shop.buy("White Tower");
        player.locateLastDefense(new Coordinate(3, 3));
        player.buildDefenses();

        assertThrows(InsuficientCredits.class, () -> shop.buy("White Tower"));


        for (int i = 0; i < 5; i++) {
            Ant ant = new Ant(map, new Path().getPath());
            enemies.add(ant);
            ant.advance();
            player.makeDefensesAttack();
            ant.transferLootTo(player);
            ant.finalizeYourWay(deadEnemies);

        }

        shop.buy("White Tower");
        player.locateLastDefense(new Coordinate(4, 3));
        player.buildDefenses();

        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("White Tower");
        });
    }

    @Test
    public void antsOnlyMovesOnGangways(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Ant ant = new Ant(map, new Path().getPath());

        Ground ground = new Ground(new Coordinate(3, 1));
        Rocky rocky = new Rocky(new Coordinate(1, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));

        assertThrows( WrongPlace.class ,()->{ ant.locateIn( new Coordinate(3,1), ground ); } );
        assertThrows( WrongPlace.class ,()->{ ant.locateIn(new Coordinate(1,1), rocky); } );
        assertDoesNotThrow( ()->{ ant.locateIn( new Coordinate(2,2), gangway); } );
        assertDoesNotThrow( ()->{ ant.locateIn( new Coordinate(15,11), finalGangway); } );
        assertDoesNotThrow( ()->{ ant.locateIn( new Coordinate(2,1), initialGangway); } );
    }
    @Test
    public void spidersOnlyMovesOnGangways(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Spider spider = new Spider(map, new Path().getPath());

        Ground ground = new Ground(new Coordinate(3, 1));
        Rocky rocky = new Rocky(new Coordinate(1, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));

        assertThrows( WrongPlace.class ,()->{ spider.locateIn( new Coordinate(3,1), ground ); } );
        assertThrows( WrongPlace.class ,()->{ spider.locateIn(new Coordinate(1,1), rocky); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(2,2), gangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(15,11), finalGangway); } );
        assertDoesNotThrow( ()->{ spider.locateIn( new Coordinate(2,1), initialGangway); } );

    }
    @Test
    public void molesOnlyMovesOnGangways(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Mole mole = new Mole(map, new Path().getPath());

        Ground ground = new Ground(new Coordinate(3, 1));
        Rocky rocky = new Rocky(new Coordinate(1, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));

        assertThrows( WrongPlace.class ,()->{ mole.locateIn( new Coordinate(3,1), ground ); } );
        assertThrows( WrongPlace.class ,()->{ mole.locateIn(new Coordinate(1,1), rocky); } );
        assertDoesNotThrow( ()->{ mole.locateIn( new Coordinate(2,2), gangway); } );
        assertDoesNotThrow( ()->{ mole.locateIn( new Coordinate(15,11), finalGangway); } );
        assertDoesNotThrow( ()->{ mole.locateIn( new Coordinate(2,1), initialGangway); } );

    }
    @Test
    public void owlsCanMovesOnAnyPlot(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Owl owl = new Owl(map, new OwlPath().owlPath(), resources.getPath().getLast());

        Ground ground = new Ground(new Coordinate(3, 1));
        Rocky rocky = new Rocky(new Coordinate(1, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));

        assertDoesNotThrow( ()->{ owl.locateIn( new Coordinate(3,1), ground); } );
        assertDoesNotThrow( ()->{ owl.locateIn( new Coordinate(1,1), rocky); } );
        assertDoesNotThrow( ()->{ owl.locateIn( new Coordinate(2,2), gangway); } );
        assertDoesNotThrow( ()->{ owl.locateIn( new Coordinate(15,11), finalGangway); } );
        assertDoesNotThrow( ()->{ owl.locateIn( new Coordinate(2,1), initialGangway); } );

    }



    @Test
    public void mobsCanBeLootedOnlyOneTime() throws InsuficientCredits, NonExistentArticle {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);


        players.add(player);

        Shop shop = new Shop(player);
        shop.addArticle("Silver Tower", new SilverTowerProvider());
        shop.addArticle("White Tower", new WhiteTowerProvider());

        shop.buy("Silver Tower");
        shop.buy("Silver Tower");
        shop.buy("Silver Tower");
        shop.buy("Silver Tower");
        shop.buy("Silver Tower");

        player.buildDefenses();
        player.buildDefenses();

        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("Silver Tower");
        });


        Spider spider = new Spider(map, new Path().getPath());
        enemies.add(spider);
        spider.advance();
        player.makeDefensesAttack();
        spider.transferLootTo(player);
        spider.transferLootTo(player);
        spider.finalizeYourWay(deadEnemies);
        enemies.removeAll(deadEnemies);



        assertThrows(InsuficientCredits.class, () -> {
            shop.buy("Silver Tower");
        });

    }

    @Test
    public void firstEnemyAdvancerOnlySpawnEnemiesInInitialGangway() throws WrongPlace {

        /* coordinates -666 -666 means that the enemy doesn't spawn yer */


        LinkedList<Coordinate> path = new LinkedList<>();
        path.add(new Coordinate(3,1));
        path.add(new Coordinate(3,2));




        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Spider spider = new Spider(map, path);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(spider);

        spider.advance();
        boolean isPosition1Correct = spider.distanceToBiggerThan(new Coordinate(-666,-666), new Distance(0));
        assertTrue(isPosition1Correct);

        boolean isPosition2Correct = spider.distanceToBiggerThan(new Coordinate(3,1), new Distance(0));
        assertTrue(isPosition2Correct);


    }

    @Test
    public void enemiesWithConstinousAdvancerMovesToTheCorrectPlace(){

        LinkedList<Coordinate> path = new LinkedList<>();
        path.add(new Coordinate(2,1));
        path.add(new Coordinate(2,2));
        path.add(new Coordinate(3,2));

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Ant ant = new Ant(map, path);

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(ant);

        ant.advance();

        ant.advance();
        boolean isPosition1Correct = ant.distanceToBiggerThan(new Coordinate(2,2), new Distance(0));
        assertFalse(isPosition1Correct);

        ant.advance();
        boolean isPosition2Correct = ant.distanceToBiggerThan(new Coordinate(3,2), new Distance(0));
        assertTrue(isPosition2Correct);


    }

    @Test
    public void deadEnemiesCantAdvanceAnyMore() {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(() -> map.locateEntityIn(whiteTower, new Coordinate(3,1)));
        whiteTower.continueWithTheConstruction();

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.advance();

        boolean isPosition1Correct = ant.distanceToBiggerThan(new Coordinate(2,1), new Distance(0));
        assertFalse(isPosition1Correct);

        whiteTower.attack(enemies);

        ant.advance();
        boolean isPosition2Correct = ant.distanceToBiggerThan(new Coordinate(2,1), new Distance(0));
        assertFalse(isPosition2Correct);




    }

    @Test
    public void deadEnemiesCantAttackThePlayer() {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        players.add(player);

        WhiteTower whiteTower = new WhiteTower();
        assertDoesNotThrow(() -> map.locateEntityIn(whiteTower, new Coordinate(14,12)));
        whiteTower.continueWithTheConstruction();



        enemies.add(ant);

        for(int i = 0; i < 24; i++)
            ant.advance();

        whiteTower.attack(enemies);

        for(int i = 0; i < 20; i++)
            ant.attack(players);

        assertEquals( "In game.", player.won());

    }











}
