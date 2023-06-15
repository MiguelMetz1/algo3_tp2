package edu.fiuba.algo3.Interface;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.HellsPlot;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

import java.util.ArrayList;
import java.util.Queue;

public class Game {
    ArrayList<Defense> defenses;
    ArrayList<Enemy> enemies;
    Queue<ArrayList<Enemy>> troops;
    Player player;
    PlayerCharacter playerCharacter;
    GameMap map;
    ArrayList<Enemy> deadEnemies;

    public Game(){
        //Hacer que el parser de las peticiones del usuario se ocupe de construir el usuario con el nombre y lo devuelva.
        //Mientras tanto Hardcodeo.
        this.player = new Player("Fabricio");
        this.defenses = new ArrayList<>();
        this.playerCharacter = new PlayerCharacter();
        this.map = new GameMap();
        this.deadEnemies = new ArrayList<>();
        ExternalResources resources = new ExternalResources(this.map);
        this.troops = resources.getEnemies();
        this.enemies = this.troops.poll();
        Coordinate playerCharacterPosition = resources.getPlayerCharacterCoordinate();
        try {
            this.map.locateEntityIn(playerCharacter, playerCharacterPosition);
        } catch (WrongPlace e) {
            System.out.println("The player character cannot be located here.");
        }
    }

    public void requireAction(){
        //System.out.println("Que quiere hacer? pasar o construir");
        //Scanner scanner = new Scanner(System.in);
        //String action = scanner.nextLine();
        /*switch (action){
            case "pasar":
                changeTurn();
                break;
            case "construir":
                //chooseConstruction();
                break;
            default:
                break;

        }*/

    }

    public void deleteDeadEnemies(){
        for (Enemy enemy:enemies){
            enemy.selfDestroy(deadEnemies);
        }
        removeDeadEnemies(deadEnemies);
    }

    public void lootEnemies(){
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        for(Enemy enemy: enemies){
            enemy.transferLootTo(player);
            if( !enemy.distanceToBiggerThan(new HellsPlot(), new Distance(0)) ){
                deadEnemies.add(enemy);
            }
        }
        this.removeDeadEnemies( deadEnemies );
    }

    public void removeDeadEnemies(ArrayList<Enemy> deadEnemies) {
        for( Enemy deadEnemy: deadEnemies){
            enemies.remove(deadEnemy);
        }
    }

    public void makeEnemiesAttack() {
        for(Enemy enemy: enemies){
            enemy.attack(this.playerCharacter);
        }
    }

    public void makeDefensesAttack() {
        for( Defense defense: defenses ){
            this.enemiesReceiveAttackFrom(defense);
        }
    }

    public void enemiesReceiveAttackFrom( Defense defense ){
        for( Enemy enemy: enemies) {
            defense.attack(enemy);
        }
    }

    public void advanceEnemies() {
        for( Enemy enemy: enemies){
            enemy.advance();
        }
        this.enemies.addAll(this.troops.poll());
    }

    public void buildDefenses() {
        for( Defense defense: defenses){
            defense.continueWithTheConstruction();
        }
    }

    public void buildDefenseIn( Defense defense, Coordinate coordinate) throws WrongPlace {
        this.map.locateEntityIn(defense, coordinate);
    }

    public String gameWon(){
            return this.playerCharacter.won(this.troops, this.enemies);
    }

}
