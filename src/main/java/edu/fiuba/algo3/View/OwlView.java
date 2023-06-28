package edu.fiuba.algo3.View;

import javafx.scene.layout.StackPane;

public class OwlView extends Paintable {

    public OwlView(StackPane stackPane) {
        super(stackPane);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/owl.png";
    }
}
