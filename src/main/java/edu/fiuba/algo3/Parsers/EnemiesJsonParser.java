package edu.fiuba.algo3.Parsers;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.Plots.Ground;
import edu.fiuba.algo3.Plots.InitialGangway;
import edu.fiuba.algo3.Enemies.*;
import edu.fiuba.algo3.Plots.Rocky;
import edu.fiuba.algo3.TypeData.Coordinate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class EnemiesJsonParser extends JsonParser{

    private MapJsonParser mapParser;
    private GameMap map;

    public EnemiesJsonParser( String fileName, GameMap map ){
        super(fileName);
        this.map = map;
        this.mapParser = new MapJsonParser("src/mapa.json");

    }
    @Override
    public Queue<ArrayList<Enemy>> get() {
        Queue< ArrayList<Enemy> > enemiesQueue = new LinkedList<>();
        String fileContent = this.readJson();
        JSONArray enemiesOnTurnArray = new JSONArray(fileContent);
        LinkedList<Coordinate> path = mapParser.getPath();
        for(int i = 0; i < enemiesOnTurnArray.length();i++){
            ArrayList<Enemy> enemiesList = new ArrayList<>();
            enemiesQueue.add(enemiesList);
            JSONObject turnAndEnemies = (JSONObject) enemiesOnTurnArray.get(i);
            JSONObject enemies = (JSONObject) turnAndEnemies.get("enemigos");
            int ant =  enemies.getInt("hormiga");
            int spider =  enemies.getInt("arana");
            enemiesList.addAll(this.createEnemies("hormiga", ant, this.copyPath(path)));
            enemiesList.addAll(this.createEnemies("arana", spider, this.copyPath(path)));
        }
        return enemiesQueue;
    }

    private ArrayList<Enemy> createEnemies(String enemy, int amountOfEnemyType, Queue<Coordinate> path) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        switch (enemy) {
            case "hormiga":

                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Ant(map, path) );
                }
                break;
            default:

                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Spider(map, path) );
                }
        }
        return enemies;

    }

    private LinkedList<Coordinate> copyPath( LinkedList<Coordinate> path ){
        ListIterator<Coordinate> iterator = path.listIterator(0);
        LinkedList<Coordinate> newPath = new LinkedList<>();
        while (iterator.hasNext()){
            newPath.add(iterator.next());
        }
        return newPath;
    }
}
