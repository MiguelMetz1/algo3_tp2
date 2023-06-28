package edu.fiuba.algo3.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class EnemyView {
    StackPane stackPane;

    Image enemyImage;

    public EnemyView(StackPane stackPane){
        this.enemyImage = new Image(getEnemyImage());
        this.stackPane = stackPane;
    }

    public void eraseEnemy(){
        this.stackPane.getChildren().clear();
    }

    public void paint(){
        ImageView overlayImageView1 = new ImageView(enemyImage);
        overlayImageView1.setFitHeight(30);
        overlayImageView1.setFitWidth(30);
        this.stackPane.getChildren().addAll(overlayImageView1);
        overlayImageView1.toFront();
    }

    protected abstract String getEnemyImage();



}
