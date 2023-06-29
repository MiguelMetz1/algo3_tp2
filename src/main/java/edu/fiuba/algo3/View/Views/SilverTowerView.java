package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.*;

public class SilverTowerView extends DefenseView {

    public SilverTowerView(Game game, AnchorPane grid, Coordinate coordinate) {
        super(game, grid, coordinate);
    }

    @Override
    protected double range() {
        return 5;
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/silverTower.png";
    }
}
