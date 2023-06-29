package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public class WhiteTowerView extends DefenseView {

    public WhiteTowerView(Game  game, AnchorPane grid, Coordinate coordinate) {
        super(game, grid, coordinate);
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
