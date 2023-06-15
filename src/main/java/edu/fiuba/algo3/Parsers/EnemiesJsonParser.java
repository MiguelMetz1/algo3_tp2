package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.fiuba.algo3.Exceptions.*;
import java.util.*;

public class EnemiesJsonParser extends JsonParser{

    private Queue<Coordinate> path;

    private GameMap map;

    public EnemiesJsonParser(String fileName, GameMap map, Queue<Coordinate> path){
        super(fileName);
        this.path = path;
        this.map = map;
    }

    public Queue<ArrayList<Enemy>> get() throws InvalidJson {
        
        Queue< ArrayList<Enemy> > enemiesQueue = new LinkedList<>();
        JSONArray enemiesOnTurnArray;
        try {
            String fileContent = this.readJson();
            enemiesOnTurnArray = new JSONArray(fileContent);
        } catch ( JSONException e ) {
            throw new InvalidJson(e.getMessage());
        }

        if ( enemiesOnTurnArray.length() == 0 ) {
            throw new InvalidJson("The enemies json is empty.");
        }

        JSONObject turnAndEnemies;
        JSONObject enemies;
        Set <String> enemiesKeys;

        for ( int i = 0; i < enemiesOnTurnArray.length(); i++ ) {
            ArrayList<Enemy> enemiesList = new ArrayList<>();
            enemiesQueue.add(enemiesList);
            try {
                turnAndEnemies = (JSONObject) enemiesOnTurnArray.get(i);
                enemies = (JSONObject) turnAndEnemies.get("enemigos");
            } catch ( JSONException e ) {
                throw new InvalidJson(e.getMessage());
            }

            enemiesKeys = enemies.keySet();
            for ( String key : enemiesKeys ) {
                try {
                    int numberOfEnemies = enemies.getInt(key);
                    enemiesList.addAll(this.createEnemies(key, numberOfEnemies));
                } catch ( JSONException|InvalidEnemy e ) {
                    throw new InvalidJson(e.getMessage());
                }
            }
        }
        
        return enemiesQueue;
    }

    private ArrayList<Enemy> createEnemies(String enemy, int amountOfEnemyType) throws InvalidEnemy {

        if ( amountOfEnemyType < 0 ) {
            throw new InvalidEnemy(String.format("Number of enemies of each type must be greater or equal than zero (is %d).", amountOfEnemyType));
        }

        ArrayList<Enemy> enemies = new ArrayList<>();
        
        switch (enemy) {
            case "hormiga":
                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Ant(this.map, this.path) );
                }
                break;

            case "arana":
                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Spider(this.map, this.path) );
                }
                break;
            default:
                throw new InvalidEnemy(String.format("Invalid enemy name '%s'.", enemy));
        }
        
        return enemies;
    }
}
