package edu.fiuba.algo3.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Paintable {
    StackPane stackPane;

    protected ImageView paintableImageView;

    public Paintable(StackPane stackPane){
        this.paintableImageView = new ImageView(new Image(getPaintableImageView()));
        this.stackPane = stackPane;
    }

    public void erasePaintables(){
        this.stackPane.getChildren().clear();
    }

    public void paint(){
        paintableImageView.setFitHeight(30);
        paintableImageView.setFitWidth(30);
        this.stackPane.getChildren().addAll(paintableImageView);
        paintableImageView.toFront();
    }

    protected abstract String getPaintableImageView();



}
