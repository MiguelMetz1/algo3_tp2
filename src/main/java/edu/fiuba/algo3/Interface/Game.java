package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Enemies.Looteable;
import edu.fiuba.algo3.Enemies.LooteableEnemy;
import edu.fiuba.algo3.Enemies.TargetableEnemy;
import edu.fiuba.algo3.Exceptions.InsuficientCredits;
import edu.fiuba.algo3.Exceptions.NonExistentArticle;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Parsers.ExternalResources;
import edu.fiuba.algo3.Players.PlayerCharacter;
import edu.fiuba.algo3.Shop.Shop;
import edu.fiuba.algo3.Shop.SilverTowerProvider;
import edu.fiuba.algo3.Shop.WhiteTowerProvider;
import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Queue;

public class Game {

    ArrayList<Enemy> enemies;

    Queue<ArrayList<Enemy>> troops;

    PlayerCharacter playerCharacter;
    GameMap map;

    Shop shop;

    ArrayList<Enemy> deadEnemies;

    ArrayList<TargetableEnemy> attackableEnemies;

    ArrayList<LooteableEnemy> looteableEnemies;

    public Game(){
        ExternalResources resources = new ExternalResources();
        this.map = resources.getMap();
        this.deadEnemies = new ArrayList<>();
        this.troops = resources.getEnemies();
        this.attackableEnemies = resources.getAttackables();
        this.looteableEnemies = resources.getLooteables();
        this.enemies = this.troops.poll();
        Coordinate playerCharacterPosition = resources.getPlayerCharacterCoordinate();
        this.playerCharacter = new PlayerCharacter("Fabricio", map, playerCharacterPosition, troops, enemies);
        this.shop = new Shop(playerCharacter);
        this.chargeShop();
    }

    private void chargeShop(){
            this.shop.addArticle(this.whiteTowerKey(), new WhiteTowerProvider());
            this.shop.addArticle(this.silverTowerKey(), new SilverTowerProvider());
            this.shop.addArticle(this.sandTrapKey(), new SandTrapProvider(this.playerCharacter));
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
        playerCharacter.locateLastDefense(coordinate);
    }

    public void lootEnemies(){
        for(Looteable enemy: looteableEnemies){
            enemy.transferLootTo(playerCharacter);
        }
    }

    private void removeFinalizedWayEnemies() {
        ArrayList<Enemy> enemiesCopy = new ArrayList<>();
        enemiesCopy.addAll(enemies);
        for( Enemy enemy: enemiesCopy){
            enemy.finalizeYourWay(enemies);
        }
    }

    private void makeEnemiesAttack() {
        ArrayList<PlayerCharacter> players = new ArrayList<>();
        players.add(playerCharacter);
        for(Enemy enemy: enemies){
            enemy.attack(players);
        }
        this.removeFinalizedWayEnemies();
    }

    public void makeDefensesAttack() {
        playerCharacter.makeDefensesAttack(this.attackableEnemies);
    }

    public void advanceEnemies() {
        for( Enemy enemy: enemies){
            enemy.advance();
        }

        if( !this.troops.isEmpty() )
            this.enemies.addAll(this.troops.poll());

        this.makeEnemiesAttack();

    }

    public void buildDefenses() {
        playerCharacter.buildDefenses();
    }

    public String gameWon(){
            return this.playerCharacter.won();
    }

    public void showMap(AnchorPane root) {
        this.map.showMap(root);
    }
}
