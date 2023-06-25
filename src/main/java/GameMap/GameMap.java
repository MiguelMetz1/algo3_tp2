package GameMap;

import Plots.Plot;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class GameMap {
    private static final GameMap instance = new GameMap("src/mapa.json");

    /* private final GameMapData gameMapData ;
    private final Plot[][] grid; */

    public GameMap(String json) {



    }




    public static GameMap getMap() {
        return instance;
    }
}
