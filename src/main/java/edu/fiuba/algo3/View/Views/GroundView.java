package edu.fiuba.algo3.View.Views;

import javafx.scene.control.Button;

public class GroundView extends PlotView{
    public GroundView(Button button) {
        super(button);
    }

    @Override
    protected String getPlotStringImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/ground.png";
    }
}
