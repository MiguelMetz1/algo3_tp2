package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.EnemyNotFound;
import edu.fiuba.algo3.Exceptions.InvalidJson;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ExternalResources {

    private GameMap map;

    public ExternalResources(GameMap map){
        this.map = map;
    }

    public HashMap<Coordinate, Plot> getMap() throws RuntimeException {
        // String filename = "src/mapa.json";
        String filename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapParser = new MapJsonParser(filename);
        try {
            return mapParser.get();
        } catch (InvalidJson e) {
            throw new RuntimeException(e.getMessage());
            // throw new InvalidJson(e.getMessage());
        }
    }

    public Queue<Coordinate> getPath() throws RuntimeException {
        // String filename = "src/mapa.json";
        String filename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapParser = new MapJsonParser(filename);
        try {
            return mapParser.getPath();
        } catch (InvalidJson e) {
            throw new RuntimeException(e.getMessage());
            // throw new InvalidJson(e.getMessage());
        }
    }

    public Queue<ArrayList<Enemy>> getEnemies() throws RuntimeException {
        String filename = "src/main/java/edu/fiuba/algo3/JsonFiles/enemigos.json";
        EnemiesJsonParser enemiesParser = new EnemiesJsonParser(filename, this.map, this.getPath());
        try {
            return enemiesParser.get();
        } catch (InvalidJson e) {
            throw new RuntimeException(e.getMessage());
        }

        // return enemiesParser.get();
    }

    public Coordinate getPlayerCharacterCoordinate() {
        // String filename = "src/mapa.json";
        String filename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapParser = new MapJsonParser(filename);
        try {
            return mapParser.getLastGangwayCoordinate();
        } catch (InvalidJson e) {
            throw new RuntimeException(e.getMessage());
            // throw new InvalidJson(e.getMessage());
        }
    }
}
