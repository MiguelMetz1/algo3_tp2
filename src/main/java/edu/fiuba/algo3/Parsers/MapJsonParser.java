package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Plots.*;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import edu.fiuba.algo3.Exceptions.InvalidJson;


public class MapJsonParser extends JsonParser {
    private Gangway previousGangway;
    private Coordinate finalGangwayCoordinate;
    int numberOfColumns;
    int numberOfRows;

    public MapJsonParser(String fileName) {
        super(fileName);
    }

    private Plot createPlot(String plotName, Coordinate coordinate) throws InvalidJson {
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

            case "Tierra":
                return new Ground();

            default:
                String exceptionMessage = String.format("'%s' invalid plot name in file '%s'.", plotName, this.fileName);
                throw new InvalidJson(exceptionMessage);
        }
    }

    public HashMap< Coordinate, Plot > get() throws InvalidJson {

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

            // System.out.print("\nRow number: " + rowNumber);
            String rowNumberString = Integer.toString(rowNumber);
            JSONArray rowArray;
            Iterator<Object> plotName;
            
            try {
                rowArray = gameMapJson.getJSONArray(rowNumberString);
                plotName = rowArray.iterator();
            } catch ( JSONException e ) {
                throw new InvalidJson(e.getMessage());
            }
            
            // System.out.print("\n\tColumn number: ");
            int columnNumber = 1;
            Plot currentPlot;
            
            while (plotName.hasNext()) {
                
                // System.out.print(columnNumber + " | ");
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
        
        Gangway actualFinalGangway = (Gangway) map.get(this.finalGangwayCoordinate);
        map.put(this.finalGangwayCoordinate, new FinalGangway(actualFinalGangway));
        
        return map;
    }

}