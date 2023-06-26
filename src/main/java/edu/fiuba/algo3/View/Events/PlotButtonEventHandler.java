package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlotButtonEventHandler implements EventHandler<ActionEvent> {

    private Plot plot;
    private VBox consoleContainer;

    Game game;

    Coordinate coordinate;

    Button button;

    public PlotButtonEventHandler(VBox consoleContainer, Plot plot, Game game, Coordinate coordinate, Button button){
        this.consoleContainer = consoleContainer;
        this.plot = plot;
        this.coordinate = coordinate;
        this.game = game;
        this.button = button;
    }


    @Override
    public void handle(ActionEvent actionEvent) {

        Label label = new Label();
        label.setText(plot.getName());
        label.setStyle("-fx-font-size: 40; -fx-padding: 0 0 0 100px;");

        consoleContainer.getChildren().clear();


        try {
            game.locateLastBoughtDefenseIn(coordinate);
        } catch (WrongPlace e) {
            label.setText(e.getMessage());
            throw new RuntimeException(e);
        }
        consoleContainer.getChildren().addAll(label);


        Image overlayImage1 = new Image(game.lastDefenseImage());
        ImageView overlayImageView1 = new ImageView(overlayImage1);
        overlayImageView1.setFitHeight(20);
        overlayImageView1.setFitWidth(20);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(overlayImageView1);

        button.setGraphic(stackPane);
    }
}
