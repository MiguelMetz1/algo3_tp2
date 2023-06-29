package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public class OwlView extends EnemyView {


    public OwlView(AnchorPane grid, Coordinate coordinate) {
        super(grid, coordinate);
    }

    @Override
    protected String entitySound() {
        return "src/main/java/edu/fiuba/algo3/View/Sounds/owlSound.wav";
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/owl.png";
    }
}
