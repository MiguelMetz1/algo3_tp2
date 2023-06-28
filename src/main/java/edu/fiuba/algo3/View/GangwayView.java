package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Plots.Plot;
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
