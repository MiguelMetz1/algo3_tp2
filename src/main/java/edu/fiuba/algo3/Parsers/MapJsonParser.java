package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MapJsonParser extends JsonParser {
    private Gangway previousGangway;
    private Coordinate finalGangwayCoordinate;
    int numberOfColumns;
    int numberOfRows;

    public MapJsonParser(String fileName) {
        super(fileName);
    }

    private Plot createPlot(String plotName, Coordinate coordinate) {
        switch (plotName) {
            case "Rocoso":
                return new Rocky();

            case "Pasarela":

                if (this.previousGangway == null) {
                    InitialGangway gangwayBeginning = new InitialGangway();
                    this.previousGangway = gangwayBeginning;
                    return gangwayBeginning;
                }
                Gangway gangway = new Gangway(this.previousGangway);

                this.finalGangwayCoordinate = coordinate;
                this.previousGangway = gangway;
                return gangway;

            default:
                return new Ground();
        }
    }

    public HashMap< Coordinate, Plot > get() {

        HashMap< Coordinate, Plot> map = new HashMap<>();

        String fileContent = this.readJson();
        JSONObject jsonObject = new JSONObject(fileContent);

        JSONObject gameMapJson = jsonObject.getJSONObject("Mapa");

        this.numberOfRows = gameMapJson.length();

        for(int rowNumber = 1; rowNumber <= gameMapJson.length(); rowNumber++){

            String rowString = Integer.toString(rowNumber);
            JSONArray rowArray = gameMapJson.getJSONArray(rowString);
            Iterator<Object> plotName = rowArray.iterator();

            int columnNumber = 1;

            while (plotName.hasNext()) {
                Coordinate coordinate = new Coordinate(columnNumber, rowNumber);
                map.put(coordinate, this.createPlot(plotName.next().toString(), coordinate));
                columnNumber ++;
            }
            this.numberOfColumns = columnNumber;
        }
        Gangway actualFinalGangway = (Gangway) map.get(this.finalGangwayCoordinate);
        map.put(this.finalGangwayCoordinate, new FinalGangway(actualFinalGangway));
        return map;
    }

}