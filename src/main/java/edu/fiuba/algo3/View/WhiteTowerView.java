package edu.fiuba.algo3.View;

import javafx.scene.layout.StackPane;

public class WhiteTowerView extends Paintable{
    public WhiteTowerView(StackPane stackPane) {
        super(stackPane);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/whiteTower.png";
    }
}
