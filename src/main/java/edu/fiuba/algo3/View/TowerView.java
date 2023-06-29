package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class TowerView extends Paintable{
    public TowerView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    private void addButtonHandler() {

        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/redCircle.png");
        ImageView rangeCircleView = new ImageView(image);

        rangeCircleView.setOpacity(0.3);
        double range = range();

        rangeCircleView.setLayoutX( (positions.get(0) - range) * 45 );
        rangeCircleView.setLayoutY( (positions.get(1) - range) * 45 );
        rangeCircleView.setFitHeight( (2*range + 1) * 45 );
        rangeCircleView.setFitWidth((2*range + 1)*45);

        towerImageView.setOnMouseEntered(e->{
            grid.getChildren().add(rangeCircleView);
            towerImageView.toFront();
        });

        towerImageView.setOnMouseExited(e->{
            this.grid.getChildren().remove(rangeCircleView);
        });

    }

    public void paint(){
        super.paint();
        this.addButtonHandler();
    }

    protected  abstract double range();

}
