package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Spider;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;

import edu.fiuba.algo3.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.TypeData.Name.Name;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void playerStartsWithCorrespondingCredits() throws InsuficientCredits, NonExistentArticle {

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

        assertThrows(InsuficientCredits.class, () -> {
                shop.buy("White Tower");
        });

    }

    @Test
    public void playerStartsWithCorrespondingLife() {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();
        ArrayList<Enemy> enemies = new ArrayList<>();

        ArrayList<Player> players = new ArrayList<>();

        Player Player = new Player(new Name("Fitzgerald"), map, resources.getPlayerCharacterCoordinate(), new LinkedList<>(), enemies);
        Spider spider = new Spider(map, new Path().copyPath());

        enemies.add(spider);
        players.add(Player);

        for (int i = 0;i < 13; i++)
            spider.advance();

        for(int i = 0; i < 10; i++)
            spider.attack(players);

        assertEquals( "Lose.", Player.won());

    }



}