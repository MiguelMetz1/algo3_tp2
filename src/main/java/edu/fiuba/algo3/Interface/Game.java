package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Loot.Looteable;
import edu.fiuba.algo3.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
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
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import edu.fiuba.algo3.Shop.Provider.SandTrapProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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

    public void buyDefense( String defense ) throws InsuficientCredits, NonExistentArticle {
        this.shop.buy(defense);
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
        for(Enemy enemy: enemies){
            enemy.attack(players);
        }
        this.removeFinalizedWayEnemies();
    }

    public void makeDefensesAttack() {
        player.makeDefensesAttack();
    }

    public void advanceEnemies() {
        if( !this.troops.isEmpty() )
            this.enemies.addAll(this.troops.poll());


        for( Enemy enemy: enemies){
            enemy.advance();
        }

    }

    public void buildDefenses() {
        player.buildDefenses();
    }

    public String gameWon(){
            return this.player.won();
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
        this.map.remainigTime(coordinate, timeOfConstruction);
    }

}
