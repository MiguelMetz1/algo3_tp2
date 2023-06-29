package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public abstract class Paintable {
    protected AnchorPane grid;

    protected ImageView towerImageView;

    protected ArrayList<Double> positions;

    public Paintable(AnchorPane grid, Coordinate coordinate) {
        this.grid = grid;
        positions = new ArrayList<>();
        coordinate.fillPositions(positions);
    }

    public void paint(){
        this.towerImageView = new ImageView(getPaintableImageView());
        towerImageView.setLayoutX( positions.get(0) * 45 );
        towerImageView.setLayoutY( positions.get(1) * 45 );
        towerImageView.setFitHeight(45);
        towerImageView.setFitWidth(45);
        this.grid.getChildren().add(towerImageView);
    }

    public void erasePaintables(){
        this.grid.getChildren().remove(this.towerImageView);
    }

    protected abstract String getPaintableImageView();



}
