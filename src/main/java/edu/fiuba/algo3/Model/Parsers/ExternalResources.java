package edu.fiuba.algo3.Model.Parsers;


import edu.fiuba.algo3.Model.Enemies.Enemy;
import edu.fiuba.algo3.Model.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ExternalResources {

    private MapJsonParser mapJsonParser;
    private EnemiesJsonParser enemiesJsonParser;

    private GameMap map;


    public ExternalResources() {
        String mapFilename = "src/main/java/edu/fiuba/algo3/Model/JsonFiles/mapa.json";
        this.mapJsonParser = new MapJsonParser(mapFilename);
        String enemiesFilename = "src/main/java/edu/fiuba/algo3/Model/JsonFiles/enemigosV2.json";
        this.enemiesJsonParser = new EnemiesJsonParser(enemiesFilename, this.mapJsonParser);
    }

    public GameMap getMap() throws RuntimeException {
        return this.mapJsonParser.get();
    }

    public LinkedList<Coordinate> getPath() throws RuntimeException {
        return this.mapJsonParser.getPath();
    }

    public Queue<ArrayList<Enemy>> getEnemies() throws RuntimeException {
        return this.enemiesJsonParser.get();
    }

    public Coordinate getPlayerCharacterCoordinate() {
        return this.mapJsonParser.getLastGangwayCoordinate();
    }

    public ArrayList<LooteableEnemy> getLooteables(){
        return this.enemiesJsonParser.getLooteables();
    }

}
