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
import edu.fiuba.algo3.View.PrincipalContainer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import edu.fiuba.algo3.Shop.Provider.SandTrapProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

public class Game {

    ArrayList<Enemy> enemies;

    Queue<ArrayList<Enemy>> troops;

    Player player;
    GameMap map;
    Shop shop;

    ArrayList<LooteableEnemy> looteableEnemies;

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
        player.locateLastDefense(coordinate);
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
        printearFinales(enemiesToRemove);
        this.enemies.removeAll(enemiesToRemove);
    }

    private void printearFinales(ArrayList<Enemy> enemiesToRemove) {
        for (Enemy enemy: enemiesToRemove){
            System.out.println(enemy.getClass().getName());
            enemy.makeSound();
        }
    }

    private void makeEnemiesAttack() {
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


        this.makeEnemiesAttack();

    }

    public void buildDefenses() {
        player.buildDefenses();
    }

    public String gameWon(){
            return this.player.won();
    }

    public void showMap(PrincipalContainer principalContainer,AnchorPane root, VBox consoleContainer, Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){
        this.map.showMap(principalContainer,root, consoleContainer,this, buttonMap, stackPaneMap);
    }

    public void showPlayerCredist() {
        this.player.showCredits();
    }

    public Name getName() {
        return this.player.getName();
    }

    public String lastDefenseImage() {
        return this.player.lastDefenseImage();
    }

    public void enemiesImages(Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap) {
         for(Enemy enemy:enemies){
             enemy.addImage(buttonMap, stackPaneMap);
         }
    }

    public void defensesImage(Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap) {
        player.defenseImage(buttonMap,stackPaneMap);
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

    /*public void updateMap(Map<Coordinate, Button> buttonMap) {
        mapReset(buttonMap);
        this.enemiesImages(buttonMap);
    }*/

   /* private void mapReset(Map<Coordinate, Button> buttonMap) {
        for(Button button: buttonMap.values()){
            button = new Button();
        }
    }*/
}
