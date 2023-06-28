package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import javafx.scene.layout.StackPane;

public class OwlView extends EnemyView {

    public OwlView(StackPane stackPane) {
        super(stackPane);
    }

    @Override
    protected String getEnemyImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/owl.png";
    }
}
