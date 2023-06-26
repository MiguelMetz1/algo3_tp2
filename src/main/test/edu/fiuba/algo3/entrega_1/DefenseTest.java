package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Defenses.Traps.SandTrap;
import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.Shop.Provider.SandTrapProvider;
import edu.fiuba.algo3.Shop.Provider.SilverTowerProvider;
import edu.fiuba.algo3.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import edu.fiuba.algo3.TypeData.Name.Name;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class DefenseTest {

    @Test
    public void BuildWhiteTowersSpendTheCorrectAmountOfCreditOfThePlayer(){

        /* Player can buy 10 white towers, each cost 10 credits and the player start with 100 credits */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();


        ArrayList<Enemy> enemies = new ArrayList<>();

        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);

        Shop shop = new Shop(player);
        shop.addArticle("White Tower", new WhiteTowerProvider());



        for(int i = 0; i < 10; i++){
            assertDoesNotThrow(() -> {
                shop.buy("White Tower");
            }, "The player hasn't got sufficient credits.");
        }

        assertThrows( InsuficientCredits.class, () ->  shop.buy( "White Tower") );


    }

    @Test
    public void BuildSilverTowersSpendTheCorrectAmountOfCreditOfThePlayer(){

        /* Player can buy 5 silver towers, each cost 20 credits and the player start with 100 credits */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        ArrayList<Enemy> enemies = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);

        Shop shop = new Shop(player);
        shop.addArticle("Silver Tower", new SilverTowerProvider());


        for(int i = 0; i < 5; i++){
            assertDoesNotThrow(() -> {
                shop.buy("Silver Tower");
            }, "The player hasn't got sufficient credits.");
        }

        assertThrows( InsuficientCredits.class, () ->  shop.buy( "Silver Tower") );


    }

    @Test
    public void BuildSandTrapsSpendTheCorrectAmountOfCreditOfThePlayer(){

        /* Player can buy 4 sand traps, each cost 25 credits and the player start with 100 credits */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        ArrayList<Enemy> enemies = new ArrayList<>();
        Player player = new Player( new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies );

        Shop shop = new Shop(player);
        shop.addArticle("Sand Trap", new SandTrapProvider(player));

        for(int i = 0; i < 4; i++){
            assertDoesNotThrow(() -> {
                shop.buy("Sand Trap");
            }, "The player hasn't got sufficient credits.");
        }

        assertThrows( InsuficientCredits.class, () ->  shop.buy( "Sand Trap") );


    }




    @Test
    public void WhiteTowersTakesOneTurnUntilStartToAttackEnemies() throws WrongPlace {

        /* white tower should be constructed in one turn. While under construction cant attack
        White towers make 1 point of damage this mean can oneshot ants  because ants have 1 point of life */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(ant);
        ant.advance();
        whiteTower.attack(enemies); /* not constructed yet so cant attack*/
        ant.finalizeYourWay(deadEnemies); /* ant is not dead so deanEnemies should de empty */
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies); /* construction time is done so tower can attack */
        ant.finalizeYourWay(deadEnemies); /* ant is now dead so deanEnemies shouldn't empty */
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void SilverTowersTakesTwoTurnsUntilStartToAttackEnemies() throws WrongPlace {

        /* silver tower should be constructed in two turns. While under construction cant attack
        White towers make 2 points of damage this mean can oneshot ants  because ants have 1 point of life */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        SilverTower silverTower = new SilverTower();
        map.locateEntityIn(silverTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(ant);
        ant.advance();
        silverTower.attack(enemies); /* not constructed yet so cant attack*/
        ant.finalizeYourWay(deadEnemies); /* ant is not dead so deanEnemies should de empty */
        assertFalse(deadEnemies.contains(ant));

        silverTower.attack(enemies);
        silverTower.continueWithTheConstruction();
        ant.finalizeYourWay(deadEnemies);
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        silverTower.continueWithTheConstruction();
        silverTower.attack(enemies); /* construction time is done so tower can attack */
        ant.finalizeYourWay(deadEnemies); /* ant is now dead so deanEnemies shouldn't empty */
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void SandTrapsShouldStartApplyingEffectOnAntsInTheSameTurn() throws WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);


        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());


        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,8));

        SandTrap sandTrap = new SandTrap(player);
        map.locateEntityIn(sandTrap, new Coordinate(2,2));
        sandTrap.continueWithTheConstruction();

        enemies.add(ant);
        ant.advance();
        ant.advance();
        boolean position1 = ant.distanceToBiggerThan(new Coordinate(2,2), new Distance(0));
        assertFalse(position1);
        sandTrap.attack(enemies);
        ant.advance();
        boolean position2 = ant.distanceToBiggerThan(new Coordinate(2,2.5), new Distance(0));
        assertFalse(position2);
        ant.advance();
        boolean position3 = ant.distanceToBiggerThan(new Coordinate(2,3.5), new Distance(0));
        assertFalse(position3);
        ant.advance();
        boolean position4 = ant.distanceToBiggerThan(new Coordinate(2,4.5), new Distance(0));
        assertFalse(position4);



    }

    @Test
    public void SandTrapsShouldStartApplyingEffectOnSpidersInTheSameTurn() throws WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);


        Path path = new Path();
        Spider spider = new Spider(map, path.copyPath());


        SandTrap sandTrap = new SandTrap(player);
        map.locateEntityIn(sandTrap, new Coordinate(2,3));
        sandTrap.continueWithTheConstruction();

        enemies.add(spider);
        spider.advance();
        spider.advance();
        boolean position1 = spider.distanceToBiggerThan(new Coordinate(2,3), new Distance(0));
        assertFalse(position1);
        sandTrap.attack(enemies);
        spider.advance();
        boolean position2 = spider.distanceToBiggerThan(new Coordinate(2,4), new Distance(0));
        assertFalse(position2);
        spider.advance();
        boolean position3 = spider.distanceToBiggerThan(new Coordinate(2,6), new Distance(0));
        assertFalse(position3);
        spider.advance();
        boolean position4 = spider.distanceToBiggerThan(new Coordinate(3,7), new Distance(0));
        assertFalse(position4);



    }

    @Test
    public void SandTrapsShouldStartApplyingEffectOnMoleInTheSameTurn() throws WrongPlace {


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();


        ArrayList<Enemy> enemies = new ArrayList<>();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);


        Path path = new Path();
        Mole mole = new Mole(map, path.copyPath());


        SandTrap sandTrap = new SandTrap(player);
        map.locateEntityIn(sandTrap, new Coordinate(2,3));
        sandTrap.continueWithTheConstruction();

        enemies.add(mole);
        mole.advance();
        mole.advance();
        boolean position1 = mole.distanceToBiggerThan(new Coordinate(2,3), new Distance(0));
        assertFalse(position1);
        sandTrap.attack(enemies);
        mole.advance();
        boolean position2 = mole.distanceToBiggerThan(new Coordinate(2,4), new Distance(0));
        assertFalse(position2);
        mole.advance();
        boolean position3 = mole.distanceToBiggerThan(new Coordinate(2,6), new Distance(0));
        assertFalse(position3);
        mole.advance();
        boolean position4 = mole.distanceToBiggerThan(new Coordinate(3,7), new Distance(0));
        assertFalse(position4);



    }

    @Test
    public void WhiteTowersMakeTheCorrectAmountOfDamage() throws WrongPlace {
        /* White towers make one point of damage so should kill an spider in two shots that have one point of life */


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.advance();
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies);
        whiteTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(ant));



    }

    @Test
    public void silversTowersMakeTheCorrectAmountOfDamage() throws WrongPlace {
        /* Silver towers make two points of damage so should oneshot a spider that have two points of life */


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Spider spider = new Spider(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        SilverTower silverTower = new SilverTower();
        map.locateEntityIn(silverTower, new Coordinate(3,1));

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(spider);
        spider.advance();
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();
        silverTower.attack(enemies);
        spider.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(spider));


    }

    @Test
    public void sandTrapDisappearAfterTheCorrectAmountOfTurns() throws WrongPlace {

        /* after three turns the ant is going to spawn an the sand trap should disappear */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);


        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,8));

        SandTrap sandTrap = new SandTrap(player);
        map.locateEntityIn(sandTrap, new Coordinate(2,2));

        sandTrap.continueWithTheConstruction(); // turn 1
        sandTrap.continueWithTheConstruction(); // turn 2
        sandTrap.continueWithTheConstruction(); // turn 3
        whiteTower.continueWithTheConstruction();

        enemies.add(ant); //ant should be in (2, 5) in 5 turn if there is no sand trap
        for(int i = 0; i < 5; i++)
            ant.advance();

        whiteTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(ant));
    }

    @Test
    public void sandTrapShouldNotAttackOwls(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        OwlPath owlPath = new OwlPath();
        Owl owl = new Owl(map, owlPath.owlPath(), playerCoordinate);
        ArrayList<Enemy> enemies = new ArrayList<>();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);

        SandTrap sandTrap = new SandTrap(player);

        assertDoesNotThrow( ()-> map.locateEntityIn( sandTrap, new Coordinate(2,6) ) );

        owl.advance();

        owl.advance();
        enemies.add(owl);
        sandTrap.attack(enemies);

        boolean isPosition1Correct = owl.distanceToBiggerThan(new Coordinate(2,6), new Distance(0));
        assertFalse(isPosition1Correct);

        owl.advance();
        boolean isPosition2Correct = owl.distanceToBiggerThan(new Coordinate(2,11), new Distance(0));
        assertFalse(isPosition2Correct);
    }

    @Test
    public void whiteTowersAttackEnemiesWithinTheExpectedRange() throws WrongPlace {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,8));
        whiteTower.continueWithTheConstruction();
        whiteTower.continueWithTheConstruction();

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.advance();

        whiteTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        ant.advance();
        ant.advance();
        ant.advance();

        whiteTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void silverTowersAttackEnemiesWithinTheExpectedRange() throws WrongPlace {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        SilverTower silverTower = new SilverTower();
        map.locateEntityIn(silverTower, new Coordinate(2,8));
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();

        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.advance();

        silverTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        ant.advance();


        silverTower.attack(enemies);
        ant.finalizeYourWay(deadEnemies);
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void whiteTowersCanOnlyBeBuildOnGround(){

        WhiteTower whiteTower = new WhiteTower();

        Ground ground = new Ground(new Coordinate(3, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));
        Rocky rocky = new Rocky(new Coordinate(1, 1));

        assertDoesNotThrow(()->{whiteTower.locateIn(new Coordinate(3,1), ground);});
        assertThrows(WrongPlace.class, ()-> {whiteTower.locateIn(new Coordinate(2,2), gangway);} );
        assertThrows(WrongPlace.class, ()-> {whiteTower.locateIn(new Coordinate(2,1), initialGangway);} );
        assertThrows(WrongPlace.class, ()-> {whiteTower.locateIn(new Coordinate(15,11), finalGangway);} );
        assertThrows(WrongPlace.class, ()-> {whiteTower.locateIn(new Coordinate(1,1), rocky);} );

    }
    @Test
    public void silverTowersCanOnlyBeBuildOnGround(){

        SilverTower silverTower = new SilverTower();

        Ground ground = new Ground(new Coordinate(3, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));
        Rocky rocky = new Rocky(new Coordinate(1, 1));

        assertDoesNotThrow(()->{silverTower.locateIn(new Coordinate(3,1), ground);});
        assertThrows(WrongPlace.class, ()-> {silverTower.locateIn(new Coordinate(2,2), gangway);} );
        assertThrows(WrongPlace.class, ()-> {silverTower.locateIn(new Coordinate(2,1), initialGangway);} );
        assertThrows(WrongPlace.class, ()-> {silverTower.locateIn(new Coordinate(15,11), finalGangway);} );
        assertThrows(WrongPlace.class, ()-> {silverTower.locateIn(new Coordinate(1,1), rocky);} );


    }

    @Test
    public void sandTrapCanOnlyBeBuildOnNormalGangways(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();


        ArrayList<Enemy> enemies = new ArrayList<>();
        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);

        SandTrap sandTrap = new SandTrap(player);

        Ground ground = new Ground(new Coordinate(3, 1));
        Gangway gangway = new Gangway(new Coordinate(2, 2));
        InitialGangway initialGangway = new InitialGangway(new Coordinate(2,1));
        FinalGangway finalGangway = new FinalGangway(new Coordinate(15, 11));

        Rocky rocky = new Rocky(new Coordinate(1, 1));

        assertThrows(WrongPlace.class, ()-> {sandTrap.locateIn(new Coordinate(2,2), ground);} );
        assertDoesNotThrow(()-> {sandTrap.locateIn(new Coordinate(2,2), gangway);} );
        assertThrows(WrongPlace.class, ()-> {sandTrap.locateIn(new Coordinate(2,1), initialGangway);} );
        assertThrows(WrongPlace.class, ()-> {sandTrap.locateIn(new Coordinate(15,11), finalGangway);} );
        assertThrows(WrongPlace.class, ()-> {sandTrap.locateIn(new Coordinate(1,1), rocky);} );


    }






}


