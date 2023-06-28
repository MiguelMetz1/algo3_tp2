package edu.fiuba.algo3.View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class GroundView extends PlotView{
    public GroundView(Button button) {
        super(button);
    }

    @Override
    protected String getPlotStringImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/ground.png";
    }
}
