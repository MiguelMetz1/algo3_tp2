package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Defenses.Towers.SilverTower;
import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Defenses.Traps.SandTrap;
import edu.fiuba.algo3.Enemies.Ant;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Enemies.TargetableEnemy;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Shop.Provider.SandTrapProvider;
import edu.fiuba.algo3.Shop.Provider.SilverTowerProvider;
import edu.fiuba.algo3.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DefenseTest {

    @Test
    public void BuildWhiteTowersSpendTheCorrectAmountOfCreditOfThePlayer(){

        /* Player can buy 10 white towers, each cost 10 credits and the player start with 100 credits */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        PlayerCharacter playerCharacter = new PlayerCharacter("Lautaro", map, playerCoordinate );

        Shop shop = new Shop(playerCharacter);
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

        PlayerCharacter playerCharacter = new PlayerCharacter("Lautaro", map, playerCoordinate );

        Shop shop = new Shop(playerCharacter);
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

        PlayerCharacter playerCharacter = new PlayerCharacter("Lautaro", map, playerCoordinate );

        Shop shop = new Shop(playerCharacter);
        shop.addArticle("Sand Trap", new SandTrapProvider(playerCharacter)); //TODO por que pide un player character?


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
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(ant);
        whiteTower.attack(enemies); /* not constructed yet so cant attack*/
        ant.die(deadEnemies); /* ant is not dead so deanEnemies should de empty */
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies); /* construction time is done so tower can attack */
        ant.die(deadEnemies); /* ant is now dead so deanEnemies shouldn't empty */
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void SilverTowersTakesThTwoTurnsUntilStartToAttackEnemies() throws WrongPlace {

        /* silver tower should be constructed in two turns. While under construction cant attack
        White towers make 2 points of damage this mean can oneshot ants  because ants have 1 point of life */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();

        SilverTower silverTower = new SilverTower();
        map.locateEntityIn(silverTower, new Coordinate(3,1));

        ArrayList<TargetableEnemy> enemies = new ArrayList<>();
        enemies.add(ant);
        silverTower.attack(enemies); /* not constructed yet so cant attack*/
        ant.die(deadEnemies); /* ant is not dead so deanEnemies should de empty */
        assertFalse(deadEnemies.contains(ant));

        silverTower.attack(enemies);
        silverTower.continueWithTheConstruction();
        ant.die(deadEnemies);
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        silverTower.continueWithTheConstruction();
        silverTower.attack(enemies); /* construction time is done so tower can attack */
        ant.die(deadEnemies); /* ant is now dead so deanEnemies shouldn't empty */
        assertTrue(deadEnemies.contains(ant));

    }

    @Test
    public void SandTrapsShouldStartApplyingEffectInTheSameTurn() throws WrongPlace {

        /* White towers have range attack of three block
           if an enemy lands on a sand trap in coordinates (2,2) isn't in range of
           the tower is placed on (2, 8)
           The enemy is going to be on range after 5 turns */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        PlayerCharacter playerCharacter = new PlayerCharacter("Lautaro", map, playerCoordinate );


        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,8));

        SandTrap sandTrap = new SandTrap(playerCharacter);
        map.locateEntityIn(sandTrap, new Coordinate(2,2));

        enemies.add(ant);
        ant.advance();
        whiteTower.attack(enemies);
        whiteTower.continueWithTheConstruction();
        ant.die(deadEnemies);
        assertFalse(deadEnemies.contains(ant));

        ant.advance();
        ant.advance();
        ant.advance();
        ant.advance();

        whiteTower.attack(enemies);
        ant.die(deadEnemies);
        assertTrue(deadEnemies.contains(ant));

        
    }


    @Test
    public void WhiteTowersMakeTheCorrectAmountOfDamage() throws WrongPlace {
        /* White towers make one point of damage so should oneshot an ant that have one point of life */


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(3,1));

        ArrayList<TargetableEnemy> enemies = new ArrayList<>();

        enemies.add(ant);
        ant.advance();
        whiteTower.continueWithTheConstruction();
        whiteTower.attack(enemies);
        ant.die(deadEnemies);
        assertTrue(deadEnemies.contains(ant));



    }

    @Test
    public void SilversTowersMakeTheCorrectAmountOfDamage() throws WrongPlace {
        /* Silver towers make two points of damage so should oneshot an spider that have two point of life */


        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Spider spider = new Spider(map, path.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();

        SilverTower silverTower = new SilverTower();
        map.locateEntityIn(silverTower, new Coordinate(3,1));

        ArrayList<TargetableEnemy> enemies = new ArrayList<>();

        enemies.add(spider);
        spider.advance();
        silverTower.continueWithTheConstruction();
        silverTower.continueWithTheConstruction();
        silverTower.attack(enemies);
        spider.die(deadEnemies);
        assertTrue(deadEnemies.contains(spider));


    }

    @Test
    public void SandTrapDisappearAfterTheCorrectAmountOfTurns() throws WrongPlace {

        /* after three turns the ant is going to spawn an the sand trap should disappear */

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();
        PlayerCharacter playerCharacter = new PlayerCharacter("Lautaro", map, playerCoordinate );


        Path path = new Path();
        Ant ant = new Ant(map, path.copyPath());
        ArrayList<TargetableEnemy> deadEnemies = new ArrayList<>();
        ArrayList<TargetableEnemy> enemies = new ArrayList<>();

        WhiteTower whiteTower = new WhiteTower();
        map.locateEntityIn(whiteTower, new Coordinate(2,8));

        SandTrap sandTrap = new SandTrap(playerCharacter);
        map.locateEntityIn(sandTrap, new Coordinate(2,2));

        sandTrap.continueWithTheConstruction(); // turn 1
        sandTrap.continueWithTheConstruction(); // turn 2
        sandTrap.continueWithTheConstruction(); // turn 3
        whiteTower.continueWithTheConstruction();

        enemies.add(ant); //ant should be in (2, 5) in 5 turn if there is no sand trap
        for(int i = 0; i < 5; i++)
                ant.advance();

       whiteTower.attack(enemies);
       ant.die(deadEnemies);
       assertTrue(deadEnemies.contains(ant));
    }


}
