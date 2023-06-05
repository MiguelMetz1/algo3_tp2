package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

public class MapJsonParser extends JsonParser {
    private Gangway previousGangway;

    public MapJsonParser(String fileName) {
        super(fileName);
    }

    private Plot createPlot(String plotName, Coordinate coordinate) {
        switch (plotName) {
            case "Rocoso":
                return new Rocky(coordinate);

            case "Pasarela":
                if (this.previousGangway == null) {
                    InitialGangway gangwayBeginning = new InitialGangway(coordinate);
                    this.previousGangway = gangwayBeginning;
                    return gangwayBeginning;
                }
                Gangway gangway = new Gangway(coordinate, this.previousGangway);
                this.previousGangway = gangway;
                return gangway;

            default:
                return new Ground(coordinate);
        }
    }

    public HashMap< Coordinate, Plot > get() {

        HashMap< Coordinate, Plot> map = new HashMap<>();

        String fileContent = this.readJson();
        JSONObject jsonObject = new JSONObject(fileContent);

        JSONObject gameMapJson = jsonObject.getJSONObject("Mapa");
        Iterator<String> gameMapKeys = gameMapJson.keys();

        int rowNumber = 1;

        while (gameMapKeys.hasNext()) {
            JSONArray rowArray = gameMapJson.getJSONArray(gameMapKeys.next());
            Iterator<Object> plotName = rowArray.iterator();

            //ArrayList < Plot > tempRow = new ArrayList<Plot>();
            //map.add(tempRow);

            int columnNumber = 1;

            while (plotName.hasNext()) {
                Coordinate coordinate = new Coordinate(rowNumber, columnNumber);
                map.put(coordinate, this.createPlot(plotName.next().toString(), coordinate));
                //System.out.print(" - " + plotName.next());
                //tempRow.add(this.createPlot(plotName.next().toString()));
                columnNumber ++;
            }
            //System.out.println();
            rowNumber ++;
        }
        return map;
    }
}
