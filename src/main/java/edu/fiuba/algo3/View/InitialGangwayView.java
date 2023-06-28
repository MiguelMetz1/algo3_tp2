package edu.fiuba.algo3.View;

import javafx.scene.control.Button;

public class InitialGangwayView extends PlotView{
    public InitialGangwayView(Button button) {
        super(button);
    }

    @Override
    protected String getPlotStringImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/initialGangway.png";
    }
}
