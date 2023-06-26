package edu.fiuba.algo3.entrega_1;


import edu.fiuba.algo3.Defenses.Towers.WhiteTower;
import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemiesTest {

    @Test
    public void AntsSpawnWithTheCorrectPointsOfLife() throws WrongPlace {
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
    public void SpidersSpawnWithTheCorrectPointsOfLife() throws WrongPlace {
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
    public void OwlsSpawnWithTheCorrectPointsOfLife() throws WrongPlace {


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
    public void OwlsStartWithTheCorrectAmountOfSpeed() throws WrongPlace {

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
    public void molesStartWithTheCorrectAmountOfSpeed() throws WrongPlace {

        ExternalResources resources = new ExternalResources();
        GameMap map = resources.getMap();

        Path path = new Path();
        Mole mole = new Mole(map, path.copyPath());

        ArrayList<Enemy> deadEnemies = new ArrayList<>();

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



}
