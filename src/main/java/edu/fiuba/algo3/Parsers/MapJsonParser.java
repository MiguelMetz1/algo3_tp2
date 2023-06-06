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
    int numberOfColumns;
    int numberOfRows;

    public MapJsonParser(String fileName) {
        super(fileName);
    }

    private Plot createPlot(String plotName, Coordinate coordinate) {
        switch (plotName) {
            case "Rocoso":
                /*System.out.println("Rocoso");*/
                return new Rocky(coordinate);

            case "Pasarela":

                if (this.previousGangway == null) {
                    /*System.out.println("Init");*/
                    InitialGangway gangwayBeginning = new InitialGangway(coordinate);
                    this.previousGangway = gangwayBeginning;
                    return gangwayBeginning;
                }
                /*System.out.println("Pasarela");*/
                Gangway gangway = new Gangway(coordinate, this.previousGangway);
                this.previousGangway = gangway;
                return gangway;

            default:
                /*System.out.println("Tierra");*/
                return new Ground(coordinate);
        }
    }

    public HashMap< Coordinate, Plot > get() {

        HashMap< Coordinate, Plot> map = new HashMap<>();

        String fileContent = this.readJson();
        JSONObject jsonObject = new JSONObject(fileContent);

        JSONObject gameMapJson = jsonObject.getJSONObject("Mapa");
        Iterator<String> gameMapKeys = gameMapJson.keys();

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
            /*System.out.println();*/
        }
        return map;
    }

}