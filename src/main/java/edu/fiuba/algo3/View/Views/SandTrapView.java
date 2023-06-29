package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import javafx.scene.layout.AnchorPane;

public class SandTrapView extends DefenseView{

    public SandTrapView(Game game, AnchorPane grid, Coordinate coordinate) {
        super(game, grid, coordinate);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/sandTrap.png";
    }

    protected String entitySound(){
        return "src/main/java/edu/fiuba/algo3/View/Sounds/sandSound.wav";
    }

    protected void updateState(){
        super.updateState();
        this.towerImageView.setOpacity(1);
    }
    @Override
    protected double range() {
        return 0;
    }
}
