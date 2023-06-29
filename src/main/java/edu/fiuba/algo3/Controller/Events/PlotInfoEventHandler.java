package edu.fiuba.algo3.Controller.Events;

import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class PlotInfoEventHandler implements EventHandler<MouseEvent> {
    private Game game;
    private VBox consoleContainer;
    private Coordinate coordinate;
    private Button button;

    public PlotInfoEventHandler( Button button, Coordinate coordinate, VBox consoleContainer, Game game) {
        this.coordinate = coordinate;
        this.consoleContainer = consoleContainer;
        this.game = game;
        this.button = button;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        consoleContainer.getChildren().clear();
        Label label = new Label();
        label.setText(coordinate.toString());
        label.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");
        label.setTextFill(Color.WHITE);


        consoleContainer.getChildren().addAll(label);

        ArrayList<String> enemies = this.game.enemiesInPlot(this.coordinate);

        for (String name: enemies){
            Text nameText = new Text(name);
            nameText.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");
            nameText.setFill(Color.WHITE);
            this.consoleContainer.getChildren().add(nameText);
        }
        this.button.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }


}
