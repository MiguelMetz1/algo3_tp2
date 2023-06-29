package edu.fiuba.algo3.Model.Parsers;

import edu.fiuba.algo3.Model.GameMap.GameMap;
import edu.fiuba.algo3.Model.Exceptions.InvalidJson;
import edu.fiuba.algo3.Model.Plots.*;
import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


public class MapJsonParser extends JsonParser {
    private Gangway previousGangway;
    private Coordinate finalGangwayCoordinate;
    int numberOfColumns;
    int numberOfRows;

    LinkedList<Coordinate> gangwayCoordinates;

    GameMap map;

    public MapJsonParser(String fileName) throws InvalidJson {
        super(fileName);
        this.gangwayCoordinates = new LinkedList<Coordinate>();
        this.chargeMap();
    }

    private Plot createPlot(String plotName, Coordinate coordinate) throws InvalidJson {
        switch (plotName) {
            case "Rocoso":
                return new Rocky(coordinate);

            case "Pasarela":
                Gangway gangway = new Gangway(coordinate);
                if (this.previousGangway == null) {
                     gangway = new InitialGangway(coordinate);
                }
                this.finalGangwayCoordinate = coordinate;
                this.previousGangway = gangway;
                this.gangwayCoordinates.add(coordinate);
                return gangway;

            case "Tierra":
                return new Ground(coordinate);

            default:
                String exceptionMessage = String.format("'%s' invalid plot name in file '%s'.", plotName, this.fileName);
                throw new InvalidJson(exceptionMessage);
        }
    }

    public GameMap get(){
        return this.map;
    }

    private void chargeMap() throws InvalidJson{
        HashMap< Coordinate, Plot> map = new HashMap<>();
        JSONObject gameMapJson;

        try {
            String fileContent = this.readJson();
            JSONObject jsonObject = new JSONObject(fileContent);
            gameMapJson = jsonObject.getJSONObject("Mapa");
        } catch ( JSONException e ) {
            throw new InvalidJson(e.getMessage());
        }

        this.numberOfRows = gameMapJson.length();
        if ( this.numberOfRows == 0 ) {
            throw new InvalidJson("The map is empty.");
        }
        int rowNumber;

        for(rowNumber = 1; rowNumber <= this.numberOfRows; rowNumber++){

            String rowNumberString = Integer.toString(rowNumber);
            JSONArray rowArray;
            Iterator<Object> plotName;

            try {
                rowArray = gameMapJson.getJSONArray(rowNumberString);
                plotName = rowArray.iterator();
            } catch ( JSONException e ) {
                throw new InvalidJson(e.getMessage());
            }

            int columnNumber = 1;
            Plot currentPlot;

            while (plotName.hasNext()) {

                Coordinate coordinate = new Coordinate(columnNumber, rowNumber);

                try {
                    currentPlot = this.createPlot(plotName.next().toString(), coordinate);
                } catch ( InvalidJson e ) {
                    throw new InvalidJson(e.getMessage());
                }

                map.put(coordinate, currentPlot);
                columnNumber ++;
            }

            if ( (rowNumber != 1) && (this.numberOfColumns != columnNumber) ) {
                throw( new InvalidJson("There are incomplete rows in '" + this.fileName + "'.") );
            }

            this.numberOfColumns = columnNumber;
        }
        map.put(this.finalGangwayCoordinate, new FinalGangway(this.finalGangwayCoordinate));

        this.map = new GameMap(map);
    }

    public LinkedList<Coordinate> getPath() {
        return this.copyPath( this.gangwayCoordinates );
    }

    private LinkedList<Coordinate> copyPath( LinkedList<Coordinate> path ){
        ListIterator<Coordinate> iterator = path.listIterator(0);
        LinkedList<Coordinate> newPath = new LinkedList<>();
        while (iterator.hasNext()){
            newPath.add(iterator.next());
        }
        return newPath;
    }

    public Coordinate getLastGangwayCoordinate() throws InvalidJson {
        this.get();
        return this.finalGangwayCoordinate;
    }
}