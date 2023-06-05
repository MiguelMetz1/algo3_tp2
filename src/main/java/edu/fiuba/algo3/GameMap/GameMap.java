package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GameMap {
    private static final GameMap instance = new GameMap("src/mapa.json");
    private HashMap< Coordinate, Plot > map ;

    /* private final GameMapData gameMapData ;
    private final Plot[][] grid; */

    public GameMap(String json) {

        this.map = new MapJsonParser("src/mapa.json").getMap();

    }

    public ArrayList<Plot> getAround(Coordinate coordinate, int distance) {
        ArrayList<Plot> plotList = new ArrayList<Plot>();
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

    public static GameMap getMap() {
        return instance;
    }
}
