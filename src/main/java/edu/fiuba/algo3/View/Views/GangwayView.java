package edu.fiuba.algo3.View.Views;

import javafx.scene.control.Button;

public class GangwayView extends PlotView {
    public GangwayView(Button button) {
        super(button);
    }

    @Override
    protected String getPlotStringImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/gangway.png";
    }
}
