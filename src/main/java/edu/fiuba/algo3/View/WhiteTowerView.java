package edu.fiuba.algo3.View;

import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public class WhiteTowerView extends TowerView{

    public WhiteTowerView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    @Override
    protected double range() {
        return 3;
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/whiteTower.png";
    }
}
