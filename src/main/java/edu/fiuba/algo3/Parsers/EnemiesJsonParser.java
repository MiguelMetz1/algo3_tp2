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



        for(int i = 0; i < enemiesOnTurnArray.length();i++){
            ArrayList<Enemy> enemiesList = new ArrayList<>();
            enemiesQueue.add(enemiesList);
            JSONObject turnAndEnemies = (JSONObject) enemiesOnTurnArray.get(i);
            JSONObject enemies = (JSONObject) turnAndEnemies.get("enemigos");
            int ant =  enemies.getInt("hormiga");
            int spider =  enemies.getInt("arana");
/*
            System.out.println("cant hormiga: " + ant);
            System.out.println("cant arania: " + spider);*/



            /*while (enemiesKeys.hasNext()) {*/
            enemiesList.addAll(this.createEnemies("hormiga", ant));
            enemiesList.addAll(this.createEnemies("arana", spider));

           /* }*/
        }
        return enemiesQueue;
    }

    private ArrayList<Enemy> createEnemies(String enemy, int amountOfEnemyType) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        /*System.out.println(enemy);*/
        switch (enemy) {
            case "hormiga":

                for(int i = 0; i < amountOfEnemyType; i++) {
                    /*System.out.println("Hormiga");*/
                    enemies.add( new Ant() );
                }
                break;
            default:

                for(int i = 0; i < amountOfEnemyType; i++) {
                    /*System.out.println("Arania");*/
                    enemies.add( new Spider() );
                }
        }
        return enemies;

    }
}
