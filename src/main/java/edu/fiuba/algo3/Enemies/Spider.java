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

import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Spider extends LooteableEnemy {

    public Spider(GameMap map, LinkedList<Coordinate> path) {
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
    protected double amountOfCredits() {
        int random = (int) Math.round(Math.random());
        return 10*(random);
    }

    protected String enemyImage(){return "file:src/main/java/edu/fiuba/algo3/View/Images/spider.png";}

    protected String getType(){
        return "Spider Life: " + Double.toString(this.getLife());
    }

    protected File soundFile(){
        return new File("src/main/java/edu/fiuba/algo3/View/Sounds/spiderSound.wav").getAbsoluteFile();
    }
}
