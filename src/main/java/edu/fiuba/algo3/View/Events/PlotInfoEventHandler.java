package edu.fiuba.algo3.View.Events;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Map;

public class PlotInfoEventHandler implements EventHandler<MouseEvent> {
    private Game game;
    private VBox consoleContainer;
    private Coordinate coordinate;

    private Button button;

    Map<Coordinate, Button> buttonMap;


    public PlotInfoEventHandler( Map<Coordinate, Button> buttonMap, Button button, Coordinate coordinate, VBox consoleContainer, Game game) {
        this.coordinate = coordinate;
        this.consoleContainer = consoleContainer;
        this.game = game;
        this.button = button;
        this.buttonMap = buttonMap;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Label label = new Label();
        label.setText(coordinate.toIntString());
        label.setStyle("-fx-font-size: 20; -fx-padding: 0 50 0 0px;");

        consoleContainer.getChildren().addAll(label);



        ArrayList<String> enemies = this.game.enemiesInPlot(this.coordinate);

        for (String name: enemies){

            this.consoleContainer.getChildren().add(new Text(name));

        }
        this.button.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }


}
