package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Distance.Distance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.fiuba.algo3.Exceptions.*;
import java.util.*;

public class EnemiesJsonParser extends JsonParser{

    private MapJsonParser mapJsonParser;

    private ArrayList<Enemy> attackReceivers;

    private ArrayList<LooteableEnemy> looteables;



    public EnemiesJsonParser(String fileName,MapJsonParser mapJsonParser){
        super(fileName);
        this.mapJsonParser = mapJsonParser;
        this.attackReceivers = new ArrayList<>();
        this.looteables = new ArrayList<>();
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
                    Ant ant = new Ant(this.mapJsonParser.get(), this.mapJsonParser.getPath());
                    enemies.add( ant );
                    this.attackReceivers.add(ant);
                    this.looteables.add(ant);
                }
                break;

            case "arana":
                for(int i = 0; i < amountOfEnemyType; i++) {
                    Spider spider = new Spider(this.mapJsonParser.get(), this.mapJsonParser.getPath());
                    enemies.add( spider );
                    this.attackReceivers.add(spider);
                    this.looteables.add(spider);
                }
                break;
            case "topo":
                for(int i = 0; i < amountOfEnemyType; i++) {
                    Mole mole = new Mole(this.mapJsonParser.get(), this.mapJsonParser.getPath());
                    enemies.add( mole );
                    attackReceivers.add(mole);
                }
                break;
            case "lechuza":

                for(int i = 0; i < amountOfEnemyType; i++) {
                    Owl owl = new Owl(this.mapJsonParser.get(), this.createOwlPath(), this.mapJsonParser.getPath().getLast());
                    enemies.add( owl );
                    this.attackReceivers.add( owl );
                }
                break;
            default:
                throw new InvalidEnemy(String.format("Invalid enemy name '%s'.", enemy));
        }
        
        return enemies;
    }

    private LinkedList<Coordinate> createOwlPath( ){
        LinkedList<Coordinate> owlPath = new LinkedList<>();
        Coordinate start = this.mapJsonParser.getPath().getFirst();
        Coordinate destination = this.mapJsonParser.getPath().getLast();
        Coordinate checkPoint = start.perpendicularProjectionInVerticalLine(destination);
        Coordinate actualCoordinate = start;
        owlPath.add(actualCoordinate);
        while ( !actualCoordinate.equals(destination) ){
            actualCoordinate = actualCoordinate.nextCoordinateInDirectionWithDistance( checkPoint, new Distance(1));
            owlPath.add(actualCoordinate);
            if( actualCoordinate.distanceTo(checkPoint).equalsTo(new Distance(0)) ){
                checkPoint = destination;
            }
        }

        return owlPath;

    }

    public ArrayList<Enemy> getTargetableEnemies(){
        return this.attackReceivers;
    }

    public ArrayList<LooteableEnemy> getLooteables(){
        return this.looteables;
    }

}
