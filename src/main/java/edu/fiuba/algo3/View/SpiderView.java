package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SpiderView extends Paintable {


    public SpiderView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/spider.png";
    }
}
