package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Loot.Looteable;
import edu.fiuba.algo3.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.Exceptions.InsufficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Shop.Provider.SilverTowerProvider;
import edu.fiuba.algo3.Shop.Provider.WhiteTowerProvider;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.TypeData.Time;
import edu.fiuba.algo3.Shop.Provider.SandTrapProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    ArrayList<Enemy> enemies;
    ArrayList<LooteableEnemy> looteableEnemies;
    Queue<ArrayList<Enemy>> troops;
    Player player;
    GameMap map;
    Shop shop;


    public Game(String name){
        ExternalResources resources = new ExternalResources();
        this.map = resources.getMap();
        this.troops = resources.getEnemies();
        this.looteableEnemies = resources.getLooteables();
        this.enemies = new ArrayList<>();
        Coordinate playerCharacterPosition = resources.getPlayerCharacterCoordinate();
        this.player = new Player(new Name(name), map, playerCharacterPosition, troops, enemies);
        this.shop = new Shop(player);
        this.chargeShop();
    }


    private void chargeShop(){
        this.shop.addArticle(this.whiteTowerKey(), new WhiteTowerProvider());
        this.shop.addArticle(this.silverTowerKey(), new SilverTowerProvider());
        this.shop.addArticle(this.sandTrapKey(), new SandTrapProvider(this.player));
        Logger.getLogger("Shop").log(Level.INFO, "There are new articles in the shop.");
    }

    private String sandTrapKey() {
        return "Sand Trap";
    }

    private String whiteTowerKey(){
        return "White Tower";
    }

    private String silverTowerKey(){
        return "Silver Tower";
    }

    public void buyDefense( String defense ) throws InsufficientCredits, NonExistentArticle {
        this.shop.buy(defense);
        Logger.getLogger("Shop").log(Level.INFO, "The player has bought a defense.");
    }

    public void locateLastBoughtDefenseIn( Coordinate coordinate ) throws WrongPlace {
        player.locateDefenses(coordinate);
    }

    public void lootEnemies(){
        for(Looteable enemy: looteableEnemies){
            enemy.transferLootTo(player);
        }
    }

    private void removeFinalizedWayEnemies() {

        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for( Enemy enemy: enemies){
            enemy.finalizeYourWay(enemiesToRemove);
        }

        this.enemies.removeAll(enemiesToRemove);
    }

    public void makeEnemiesAttack() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        Logger.getLogger("Game").log(Level.INFO, "Enemies in the map are trying to attack.");
        for(Enemy enemy: enemies){
            enemy.attack(players);
        }
        this.removeFinalizedWayEnemies();
    }

    public void makeDefensesAttack() {
        Logger.getLogger("Defenses").log(Level.INFO, "Defenses in the map are trying to attack.");
        player.makeDefensesAttack();
    }

    public void advanceEnemies() {
        if( !this.troops.isEmpty() )
            this.enemies.addAll(this.troops.poll());


        for( Enemy enemy: enemies){
            enemy.advance();
        }
        Logger.getLogger("Game").log(Level.INFO, "The enemies in the map are trying to advance.");

    }

    public void buildDefenses() {
        player.buildDefenses();
    }

    public String gameWon(){
        String gameWon = this.player.won();
        Logger.getLogger("Game").log(Level.INFO, "The game has " + gameWon );
        return gameWon;
    }

    public Name getName() {
        return this.player.getName();
    }


    public String remainingLife() {
        return player.remainingLife();
    }

    public String remainingCredits() {
        return player.remainingCredits();
    }

    public ArrayList<String> enemiesInPlot(Coordinate coordinate) {
        ArrayList<String> enemiesList = new ArrayList<>();
        for (Enemy enemy: enemies)
            enemy.inPosition(coordinate, enemiesList);

        return enemiesList;
    }

    public double getMapDimension() {
        return this.map.getDimension();
    }

    public String getMapPlotType(Coordinate actualCoordinate) {
        return this.map.getType(actualCoordinate);
    }

    public HashMap<Coordinate, ArrayList<String>> findEntities() {
        HashMap<Coordinate, ArrayList<String>> coordinateType = new HashMap<>();
        player.findTowersPosition(coordinateType);
        for (Enemy enemy: enemies){
            enemy.findPosition(coordinateType);
        }

        return coordinateType;
    }

    public void remainingTime(Coordinate coordinate, Time timeOfConstruction) {
        this.map.remainingTime(coordinate, timeOfConstruction);
    }

}
