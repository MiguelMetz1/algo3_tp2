package edu.fiuba.algo3.Enemies;

import edu.fiuba.algo3.Attacker.EnemiesAttacker.LifeAttacker;
import edu.fiuba.algo3.Enemies.Loot.LooteableEnemy;
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.Map;
import java.util.Queue;

public class Spider extends LooteableEnemy {

    public Spider(GameMap map, Queue<Coordinate> path) {
        super(map, path);
        this.setAttacker( new LifeAttacker( this.actualPosition, getDamage() ) );
    }


    protected double getDamage() {
        return 2;
    }

    @Override
    protected double getLife() {
        return 2;
    }

    @Override
    protected double getSpeed() {
        return 2;
    }

    @Override
    protected int amountOfCredits() {
        int random = (int) Math.round(Math.random());
        return 10*(random);
    }

    protected String enemyImage(){return "file:src/main/java/edu/fiuba/algo3/View/Images/spider.png";}

    protected String getType(){
        return "Spider";
    }
}
