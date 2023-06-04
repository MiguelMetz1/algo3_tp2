package GameMap;

import Plots.Gangway;
import Plots.Ground;
import Plots.Plot;
import Plots.Rocky;
import TypeData.Coordinate;

import java.util.List;
import java.util.Map;

public class GameMapData {
    private Map<String, List<String>> gameMap;
    private Gangway previousGangwayPlot;

    public Plot getPlotAt(int x, int y) {
        String type = this.gameMap.get(Integer.toString(x+1)).get(y);
        Plot plot;

        switch (type) {
            case "Pasarela":
                plot = new Gangway(new Coordinate(x,y));

                if (previousGangwayPlot != null) {
                    previousGangwayPlot.setNext((Gangway) plot);
                }

                previousGangwayPlot = (Gangway) plot;
                break;

            case "Rocoso":
                plot = new Rocky(new Coordinate(x,y));
                break;

            case "Tierra":
                plot = new Ground(new Coordinate(x,y));
                break;

            default:
                throw new IllegalArgumentException("Unkwnown Plot type: " + type);
        }


        return plot;
    }

    public int getSize() {
        return this.gameMap.size();
    }
}