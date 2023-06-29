package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public class AntView extends Paintable {


    public AntView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/ant.png";
    }
}
