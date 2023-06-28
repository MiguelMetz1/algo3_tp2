package edu.fiuba.algo3.View;

import javafx.scene.layout.StackPane;

public class AntView extends EnemyView {


    public AntView(StackPane stackPane) {
        super(stackPane);
    }

    @Override
    protected String getEnemyImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/ant.png";
    }
}
