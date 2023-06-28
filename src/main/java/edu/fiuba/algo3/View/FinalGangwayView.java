package edu.fiuba.algo3.View;

import javafx.scene.control.Button;

public class FinalGangwayView extends PlotView{
    public FinalGangwayView(Button button) {
        super(button);
    }

    @Override
    protected String getPlotStringImage() {
        return "file:src/main/java/edu/fiuba/algo3/View/Images/finalGangway.png";
    }
}
