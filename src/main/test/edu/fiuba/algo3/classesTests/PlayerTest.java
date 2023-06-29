package edu.fiuba.algo3.classesTests;

import edu.fiuba.algo3.Model.Enemies.Enemy;
import edu.fiuba.algo3.Model.Enemies.Spider;
import edu.fiuba.algo3.Model.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Model.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Model.Exceptions.WrongPlace;
import edu.fiuba.algo3.Model.Exceptions.WrongPlayerName;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Parsers.ExternalResources;
import edu.fiuba.algo3.Model.Players.Player;

import edu.fiuba.algo3.Model.Plots.*;
import edu.fiuba.algo3.Model.Shop.Provider.SandTrapProvider;
import edu.fiuba.algo3.Model.Shop.Provider.SilverTowerProvider;
import edu.fiuba.algo3.Model.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Model.Shop.Shop;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Name.Name;
import edu.fiuba.algo3.classesTests.PathForTheTest.NormalPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void playerStartsWithCorrespondingCredits() throws InsufficientCredits, NonExistentArticle {

        //Life:20     Credits:100

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();
        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);

        Shop shop = new Shop(player);

        assertDoesNotThrow(()->{shop.addArticle("White Tower", new WhiteTowerProvider());});

        for (int i = 0; i < 10; i++) {
            shop.buy("White Tower");

        }

        assertThrows(InsufficientCredits.class, () -> {
                shop.buy("White Tower");
        });

    }

    @Test
    public void playerStartsWithCorrespondingLife() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Spider spider = new Spider(map, new NormalPath().copyPath());

        enemies.add(spider);
        players.add(player);

        for (int i = 0;i < 13; i++)
            spider.advance();

        for(int i = 0; i < 10; i++)
            spider.attack(players);

        assertEquals( "Lose.", player.won());

    }

    @Test
    public void playerCanOnlyBuyDefensesThatExist(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        players.add(player);

        Shop shop = new Shop(player);
        shop.addArticle("Silver Tower", new SilverTowerProvider());
        shop.addArticle("White Tower", new WhiteTowerProvider());
        shop.addArticle("SandTrap", new SandTrapProvider(player));

        assertDoesNotThrow(() -> {shop.buy("Silver Tower");});
        assertDoesNotThrow(() -> {shop.buy("White Tower");});
        assertDoesNotThrow(() -> {shop.buy("SandTrap");});
        assertThrows(NonExistentArticle.class, () -> shop.buy("Gold Tower"));

    }

    @Test
    public void playerNameNeedsToHaveSixLettersOrMoreToBeCreated(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        ArrayList<Enemy> enemies = new ArrayList<>();


        assertThrows(WrongPlayerName.class, () -> new Player(new Name("Pepe"), map,
               playerCoordinate, new LinkedList<>(), enemies));

        assertDoesNotThrow(() ->
                new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies));

    }



    @Test
    public void playerCantBePlacedInOtherPlotThatIsntFinalGangway(){

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        Coordinate playerCoordinate = resources.getPlayerCharacterCoordinate();

        ArrayList<Enemy> enemies = new ArrayList<>();


        Player player = new Player(new Name("Fitzgerald"), map, playerCoordinate, new LinkedList<>(), enemies);
        assertThrows(WrongPlace.class,
                () -> player.locateIn(new Coordinate(2,2), new Gangway( new Coordinate(2,2))));

        assertThrows(WrongPlace.class,
                () -> player.locateIn(new Coordinate(3,1), new Ground( new Coordinate(3,1))));

        assertThrows(WrongPlace.class,
                () -> player.locateIn(new Coordinate(1,1), new Rocky( new Coordinate(1,1))));

        assertThrows(WrongPlace.class,
                () -> player.locateIn(new Coordinate(2,1), new InitialGangway( new Coordinate(2,1))));

        assertDoesNotThrow(() -> player.locateIn(playerCoordinate, new FinalGangway( new Coordinate(15,11))));

    }








}
