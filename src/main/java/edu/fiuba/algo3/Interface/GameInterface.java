package edu.fiuba.algo3.Interface;


import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.IncorrectPlaceable;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Plots.HellsPlot;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.TypeData.Distance;

import java.util.ArrayList;

public class GameInterface{
    ArrayList<Defense> defenses;
    ArrayList<Enemy> enemies;
    Player player;
    PlayerCharacter playerCharacter;
    boolean gameFinalized;
    String winner;
    GameMap map;

    public GameInterface(){
        //Hacer que el parser de las peticiones del usuario se ocupe de construir el usuario con el nombre y lo devuelva.
        //Mientras tanto Hardcodeo.
        this.player = new Player("Fabricio");
        this.turner = new NotChangedTurn();
        this.defenses = new ArrayList<>();
        this.playerCharacter = new PlayerCharacter();
        MapJsonParser mapParser = new MapJsonParser("src/mapa.json");
        this.map = new GameMap(mapParser);
        Coordinate playerCharacterPosition = mapParser.getPlayerCharacterCoordinate();
        try {
            this.map.locateEntityIn(playerCharacter, playerCharacterPosition);
        } catch (IncorrectPlaceable e) {
            System.out.println("The player character cannot be located here.");
        }
    }

    public void startGame(){
        while( !this.gameFinalized ){
            this.requireAction();
            this.computerTurn();
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

    private void removeDeadEnemies(ArrayList<Enemy> deadEnemies) {
        for( Enemy deadEnemy: deadEnemies){
            enemies.remove(deadEnemy);
        }
    }

    public void computerTurn(){
        this.buildDefenses();
        this.advanceEnemies();
        this.makeDefensesAttack();
        this.makeEnemiesAttack();
        this.lootEnemies();
    }

    private void makeEnemiesAttack() {
        for(Enemy enemy: enemies){
            enemy.attack(this.playerCharacter);
        }
    }

    private void makeDefensesAttack() {
        for( Defense defense: defenses ){
            this.enemiesReceiveAttackFrom(defense);
        }
    }

    private void enemiesReceiveAttackFrom( Defense defense ){
        for( Enemy enemy: enemies) {
            defense.attack(enemy);
        }
    }

    private void advanceEnemies() {
        for( Enemy enemy: enemies){
            enemy.advance();
        }
    }

    private void buildDefenses() {
        for( Defense defense: defenses){
            defense.continueWithTheConstruction();
        }
    }


}
