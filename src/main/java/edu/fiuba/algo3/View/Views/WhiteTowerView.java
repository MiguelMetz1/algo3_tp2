package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.Game.Game;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
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
