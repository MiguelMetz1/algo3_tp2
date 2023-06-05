package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Parsers.EnemiesJsonParser;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Plots.InitialGangway;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.*;

public class GameMap {
    private static GameMap instance = new GameMap("src/mapa.json");
    private HashMap< Coordinate, Plot > map;

    private InitialGangway initialGangway;

    private GameMap(String json) {

        MapJsonParser mapParser = new MapJsonParser(json);
        this.map = mapParser.get();
        initialGangway = this.returnSpawnPoint();

    }

    private InitialGangway returnSpawnPoint() {
        InitialGangway spawn = null;
        Set<Coordinate> plots = this.map.keySet();
        Iterator <Coordinate> coordinateIterator = plots.iterator();
        EnemiesJsonParser enemiesParser = new EnemiesJsonParser("src/enemigos.json");
        Queue< ArrayList<Enemy> > enemiesToSpawn = enemiesParser.get();
        while (coordinateIterator.hasNext()){
            Coordinate actualCoordinate = coordinateIterator.next();
            Plot actualPlot = this.map.get(actualCoordinate);
            if (actualPlot.canSpawnEnemies()) {
                spawn = new InitialGangway(actualCoordinate, enemiesToSpawn);
                this.map.replace(actualCoordinate, spawn);
            }
        }
       return spawn;
    }

    public ArrayList<Plot> getAround(Coordinate coordinate, int distance) {
        ArrayList<Plot> plotList = new ArrayList<>();
        ArrayList<Coordinate> coordinates = coordinate.getAround(distance);

        Iterator <Coordinate> coordinateIterator = coordinates.iterator();
        while (coordinateIterator.hasNext()){
            Plot tmpPlot = this.map.get(coordinateIterator.next());
            if (tmpPlot != null) {
                plotList.add(tmpPlot);
            }
        }
        return plotList;
    }

    public void build(Defense defense, Coordinate coordinate) throws CannotBuild {
        this.map.get(coordinate).build(defense);
    }

    public static GameMap getMap() {
        return instance;
    }

    public void advanceEnemies(){
        initialGangway.advanceEnemies();
    }
}
