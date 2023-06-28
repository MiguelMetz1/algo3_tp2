package edu.fiuba.algo3.View;

import javafx.scene.layout.StackPane;

public class SpiderView extends Paintable {


    public SpiderView(StackPane stackPane) {
        super(stackPane);
    }

    @Override
    protected String getPaintableImageView() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/spider.png";
    }
}
