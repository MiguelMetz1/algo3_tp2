package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.Plots.InitialGangway;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.*;

public class GameMap {
    private static GameMap instance = new GameMap();
    private HashMap< Coordinate, Plot > map;

    private InitialGangway initialGangway;

    private GameMap() throws RuntimeException {
        ExternalResources resources = new ExternalResources();
        try {
            this.map = resources.getMap();
        }
        catch ( RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }
        initialGangway = this.returnSpawnPoint();
    }

    private InitialGangway returnSpawnPoint() throws RuntimeException {
        InitialGangway spawn = null;
        Set<Coordinate> plots = this.map.keySet();
        Iterator <Coordinate> coordinateIterator = plots.iterator();

        ExternalResources resources = new ExternalResources();
        Queue< ArrayList<Enemy> > enemiesToSpawn;
        try {
            enemiesToSpawn = resources.getEnemies();
        } catch ( RuntimeException e ) {
            throw new RuntimeException(e.getMessage());
        }

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
        this.map.get(coordinate).build(defense);
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
        instance = new GameMap();
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
