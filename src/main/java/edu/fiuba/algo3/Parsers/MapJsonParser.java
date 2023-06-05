package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

public class MapJsonParser implements MapParser {

    private HashMap< Coordinate, Plot> map ;
    private Gangway previousGangway;

    public MapJsonParser(String fileName) {

        String fileContent = this.readJson(fileName);
        JSONObject jsonObject = new JSONObject(fileContent);

        JSONObject gameMapJson = jsonObject.getJSONObject("Mapa");
        Iterator<String> gameMapKeys = gameMapJson.keys();

        this.map = new HashMap< Coordinate, Plot >();
        int rowNumber = 1;

        while (gameMapKeys.hasNext()) {
            JSONArray rowArray = gameMapJson.getJSONArray(gameMapKeys.next());
            Iterator<Object> plotName = rowArray.iterator();

            //ArrayList < Plot > tempRow = new ArrayList<Plot>();
            //map.add(tempRow);

            int columnNumber = 1;

            while (plotName.hasNext()) {
                Coordinate coordinate = new Coordinate(rowNumber, columnNumber);
                this.map.put(coordinate, this.createPlot(plotName.next().toString(), coordinate));
                //System.out.print(" - " + plotName.next());
                //tempRow.add(this.createPlot(plotName.next().toString()));
                columnNumber ++;
            }
            //System.out.println();
            rowNumber ++;
        }
    }

    private String readJson(String fileName) {
        String string = "";

        FileReader fileReader;
        try {
            String stringActual = "";

            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (stringActual != null) {
                string += stringActual;
                stringActual = bufferedReader.readLine();
                System.out.println((stringActual));
            }
            fileReader.close();
        } catch (Exception e) {
            //fileReader.close();
            throw new RuntimeException(e);
        } finally {
            //fileReader.close();
        }

        return string;
    }

    private Plot createPlot(String plotName, Coordinate coordinate) {
        // Coordinate coordinate = new Coordinate(this.map.size(), this.map.get(this.map.size()).size());
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

    public HashMap< Coordinate, Plot > getMap() {
        return this.map;
    }
}
