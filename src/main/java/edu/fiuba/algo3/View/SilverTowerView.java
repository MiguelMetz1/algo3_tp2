package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class SilverTowerView extends Paintable {

    private AnchorPane grid;
    private Button button;

    private Coordinate coordinate;
    public SilverTowerView(StackPane stackPane, AnchorPane grid, Button button, Coordinate coordinate) {
        super(stackPane);
        this.grid = grid;
        this.button = button;
        this.coordinate = coordinate;
        this.addButtonHandler();

    }

    private void addButtonHandler() {

        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/redCircle.png");
        ImageView imv = new ImageView(image);
        this.button.addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
            eventFunction(imv);
        });

    }

    private void eventFunction(ImageView imageView){
        imageView.setOpacity(0.3);
        double range = 5;
        ArrayList<Double> positions = new ArrayList<>();
        coordinate.fillPositions(positions);
        ImageView paintImageViewCopy = new ImageView(new Image(getPaintableImageView()));

        paintImageViewCopy.setLayoutX( positions.get(0) * 45 + 10);
        paintImageViewCopy.setLayoutY( positions.get(1) * 45 + 8 );
        paintImageViewCopy.setFitHeight(30);
        paintImageViewCopy.setFitWidth(30);

        imageView.setLayoutX( (positions.get(0) - range) * 45 );
        imageView.setLayoutY( (positions.get(1) - range) * 45 );
        imageView.setFitHeight( (2*range + 1) * 45 );
        imageView.setFitWidth((2*range + 1)*45);

        grid.getChildren().add(paintImageViewCopy);

        paintImageViewCopy.setOnMouseEntered(e->{
            grid.getChildren().add(imageView);
            paintImageViewCopy.toFront();
        });

        paintImageViewCopy.setOnMouseExited(e->{
            grid.getChildren().remove(imageView);
            grid.getChildren().remove(paintImageViewCopy);
        });
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/silverTower.png";
    }
}
