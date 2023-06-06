package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Exceptions.UnespawnablePlace;
import edu.fiuba.algo3.Parsers.EnemiesJsonParser;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Plots.Gangway;
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
                Gangway actualGangway = (Gangway) actualPlot;
                spawn = new InitialGangway(actualGangway, enemiesToSpawn);
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
            Coordinate coord = coordinateIterator.next();
            /*coord.showCoords();*/
            Plot tmpPlot = this.map.get(coord);
            if (tmpPlot != null) {
                plotList.add(tmpPlot);
            }
        }
        return plotList;
    }

    public void build(Defense defense, Coordinate coordinate) throws CannotBuild {
        Plot plotInBuild = this.map.get(coordinate);
        plotInBuild.build(defense);
    }

    public boolean canBuild(Defense defense, Coordinate coordinate){
        return map.get(coordinate).canBuild();
    }

    public static GameMap getMap() {
        return instance;
    }

    public void advanceEnemies(){
        initialGangway.advanceEnemies();
    }

    public void spawnEnemies(){

        initialGangway.spawnEnemies();

    }

    public static void resetMap(){
        instance = new GameMap("src/mapa.json");
    }

    public boolean plotHasEnemies(Coordinate coordinate){
        return (map.get(coordinate)).hasEnemies();

    }

/*    public void imprimirMapa(){
        for(int i = 1; i <= 15; i++){
            for(int j = 1; j <= 15; j++){
                System.out.print(" "+map.get(new Coordinate(i,j)).showPlotName());
            }
            System.out.println();
        }
    }*/

    public boolean MapHasEnemies() {
        Set<Coordinate> plots = this.map.keySet();
        Iterator <Coordinate> coordinateIterator = plots.iterator();

        while (coordinateIterator.hasNext()) {
            Coordinate currentCoordinate = coordinateIterator.next();
            if (this.map.get(currentCoordinate).hasEnemies()){
                return true;
            }
        }
        return false;
    }
}
