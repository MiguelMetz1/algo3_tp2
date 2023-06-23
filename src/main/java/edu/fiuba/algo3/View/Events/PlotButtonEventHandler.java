package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Plots.Plot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlotButtonEventHandler implements EventHandler<ActionEvent> {

    private Plot plot;
    private VBox consoleContainer;

    public PlotButtonEventHandler(VBox consoleContainer, Plot plot){
        this.consoleContainer = consoleContainer;
        this.plot = plot;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

        Label label = new Label();
        label.setText(plot.getName());
        label.setStyle("-fx-font-size: 40; -fx-padding: 0 0 0 100px;");

        consoleContainer.getChildren().clear();

        consoleContainer.getChildren().addAll(label);
    }
}
