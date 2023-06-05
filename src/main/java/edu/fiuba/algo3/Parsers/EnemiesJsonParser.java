package edu.fiuba.algo3.Parsers;

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

    public EnemiesJsonParser( String fileName ){
        super(fileName);
    }
    @Override
    public Queue<ArrayList<Enemy>> get() {
        Queue< ArrayList<Enemy> > enemiesQueue = new LinkedList<>();
        String fileContent = this.readJson();
        JSONArray enemiesOnTurnArray = new JSONArray(fileContent);
        Iterator<Object> enemiesOnTurnIterator = enemiesOnTurnArray.iterator();

        while (enemiesOnTurnIterator.hasNext()) {
            ArrayList<Enemy> enemiesList = new ArrayList<>();
            enemiesQueue.add(enemiesList);
            JSONObject turnAndEnemies = (JSONObject) enemiesOnTurnIterator.next();
            JSONObject enemies = (JSONObject) turnAndEnemies.get("enemigos");
            Iterator<String> enemiesKeys = enemies.keys();

            while (enemiesKeys.hasNext()) {
                String enemy = enemiesKeys.next();
                int amountOfEnemyType = enemies.getInt(enemy);
                enemiesList.addAll(this.createEnemies(enemiesKeys.next(), amountOfEnemyType));
            }
        }
        return enemiesQueue;
    }

    private ArrayList<Enemy> createEnemies(String enemy, int amountOfEnemyType) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        switch (enemy) {
            case "hormiga":
                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Ant() );
                }
                break;
            default:
                for(int i = 0; i < amountOfEnemyType; i++) {
                    enemies.add( new Spider() );
                }
        }
        return enemies;

    }
}
