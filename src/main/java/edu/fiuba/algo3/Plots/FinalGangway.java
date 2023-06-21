package edu.fiuba.algo3.Plots;


import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Players.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class FinalGangway extends Gangway {

    public  FinalGangway(Gangway finalGangway){
        super();
        finalGangway.previousGangway.setNext(this);
    }

    @Override
    public void advanceEnemies(){
        for (Enemy enemy: enemies) {
            enemy.attack(Player.getPlayer());
        }
        this.enemies = new ArrayList<Enemy>();
    }

    public Image getImage() {
        return new Image("file:src/main/java/edu/fiuba/algo3/View/Imagenes/orangeWool.png");
    }

}


