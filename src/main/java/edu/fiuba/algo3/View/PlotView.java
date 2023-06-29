package edu.fiuba.algo3.View;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public abstract class PlotView {

    private Button button;

    public PlotView( Button button ){
        this.button = button;
    }

    public void paint(){
        this.button.setBackground( new Background( new BackgroundImage(new Image(getPlotStringImage()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(40, 40, false, false, false, true))));
    }

    protected abstract String getPlotStringImage();

}
