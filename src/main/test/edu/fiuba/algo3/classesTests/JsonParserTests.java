package edu.fiuba.algo3.classesTests;

import edu.fiuba.algo3.Exceptions.*;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Parsers.EnemiesJsonParser;
import edu.fiuba.algo3.Parsers.MapJsonParser;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;


public class JsonParserTests {

    @Test
    public void correctMapIsCreatedWithoutExceptions() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        final MapJsonParser[] mapJsonParser = new MapJsonParser[1];
        assertDoesNotThrow(() -> {
            mapJsonParser[0] = new MapJsonParser(mapJsonFilename);}, "The json '"+mapJsonFilename+"' is invalid.");
        mapJsonParser[0].get();
    }
    
    @Test
    public void correctMapIsCreatedWithoutExceptionsFromExternalResources() {
        assertDoesNotThrow(() ->{
            ExternalResources resources = new ExternalResources();
            resources.getMap();
        }, "The json is invalid.");
    }
    
    @Test
    public void emptyMapThrowsInvalidJason() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/empty_json_map.json";
        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
               mapJsonParser.get();
               },
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = "The map is empty.";
        assertEquals(thrown.getMessage(), exceptionMessage);
    }

    @Test
    public void IncompleteMapThrowsInvalidJson() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/incomplete_map.json";


        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
               mapJsonParser.get();
               },
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = String.format("There are incomplete rows in '%s'.", mapJsonFilename);
        assertEquals(thrown.getMessage(), exceptionMessage);
    }

    @Test
    public void MapWithArenaPlotThrowsInvalidJson() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/map_with_arena_plot.json";
        
        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
               mapJsonParser.get();
               },
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = String.format("'Arena' invalid plot name in file '%s'.", mapJsonFilename);
        assertEquals(thrown.getMessage(), exceptionMessage);
    }

    @Test
    public void MapWithoutMapaKeyThrowsInvalidJson() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/map_without_mapa_key.json";
        assertThrows(InvalidJson.class, () -> {
            MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
            mapJsonParser.get();
        });
    }

    @Test
    public void MapWithInexistentRowNumberThrowsInvalidJson() {
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/map_with_inexistent_row_number.json";

        assertThrows(InvalidJson.class, () -> {
            MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
            mapJsonParser.get();
        });
    }

    @Test
    public void CorrectEnemiesJsonIsCreatedWithoutExceptions() {
        String enemiesJsonFileName = "src/main/java/edu/fiuba/algo3/JsonFiles/enemigos.json";
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);

        assertDoesNotThrow(() -> {
            EnemiesJsonParser enemiesJsonParser = new EnemiesJsonParser( enemiesJsonFileName, mapJsonParser );
            enemiesJsonParser.get();
            }, "The enemies json has got an error.");
    }

    @Test
    public void CorrectEnemiesJsonIsCreatedWithoutExceptionsFromExternalResources() {

        assertDoesNotThrow(() -> {
            ExternalResources resources = new ExternalResources();
            resources.getEnemies(); }, "The enemies json has got an error.");
    }

    @Test
    public void EnemiesJsonWithEnemyNameWaspThrowsInvalidJson() {
        String enemiesJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/enemies_with_wasp_name.json";
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);

        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               EnemiesJsonParser enemiesJsonParser = new EnemiesJsonParser(enemiesJsonFilename, mapJsonParser);
               enemiesJsonParser.get();},
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = "Invalid enemy name 'wasp'.";
        assertEquals(thrown.getMessage(), exceptionMessage);

    }

    @Test
    public void EnemiesJsonWithNumnberOfEnemiesLessThanZeroThrowsInvalidJson() {
        String enemiesJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/enemies_with_amount_LT_0_name.json";
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
        final Queue<Coordinate> path;


        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               EnemiesJsonParser enemiesJsonParser = new EnemiesJsonParser(enemiesJsonFilename, mapJsonParser);
               enemiesJsonParser.get();
               },
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = "Number of enemies of each type must be greater or equal than zero (is -2).";
        assertEquals(thrown.getMessage(), exceptionMessage);
}

    @Test
    public void EmptyEnemiesJsonThrowsInvalidJson() {
        String enemiesJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles//empty_enemies.json";
        String mapJsonFilename = "src/main/java/edu/fiuba/algo3/JsonFiles/mapa.json";
        MapJsonParser mapJsonParser = new MapJsonParser(mapJsonFilename);
        
        InvalidJson thrown = assertThrows(
           InvalidJson.class,
           () -> {
               EnemiesJsonParser enemiesJsonParser = new EnemiesJsonParser(enemiesJsonFilename, mapJsonParser);
               enemiesJsonParser.get();
               },
           "Expected get() to throw, but it didn't"
        );

        String exceptionMessage = String.format("The enemies json is empty.");
        assertEquals(thrown.getMessage(), exceptionMessage);
    }

}


