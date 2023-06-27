package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Interface.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.Events.PlotButtonEventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.*;

public class GameMap {
    private HashMap< Coordinate, Plot > map;

    public GameMap( HashMap<Coordinate, Plot> map ){
        this.map = map;
    }

    public void locateEntityIn(Placeable entity, Coordinate destinationPlace ) throws WrongPlace {
        if( !this.map.containsKey(destinationPlace) ){
            throw new WrongPlace("The place that you are trying to access is not part of this map");
        }
        Plot destinationPlot = map.get(destinationPlace);
        entity.locateIn(destinationPlace, destinationPlot);
    }

    public void showMap(AnchorPane root, VBox consoleContainer, Game game, Map<Coordinate, Button> buttonMap, Map<Coordinate, StackPane> stackPaneMap){
        Map<Coordinate, Plot> plots = this.map;

        for (Map.Entry<Coordinate, Plot> entry : plots.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Plot plot = entry.getValue();

            Button button = new Button();
            button.setLayoutX(coordinate.getX() * 45);
            button.setLayoutY(coordinate.getY() * 45);
            button.setPrefWidth(45);
            button.setPrefHeight(45);
            button.setBackground(new Background(new BackgroundImage(plot.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(40,40,false,false,false,true))));
            button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            button.setOnMouseEntered(event -> button.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
            button.setOnMouseExited(event -> button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));

            StackPane stackPane = new StackPane();

            PlotButtonEventHandler plotButtonEventHandler = new PlotButtonEventHandler(consoleContainer, plot, game, coordinate, buttonMap, stackPaneMap);
            button.setOnAction(plotButtonEventHandler);


            //stackPane.getChildren().addAll(overlayImageView);

            button.setGraphic(stackPane);

            buttonMap.put(coordinate,button);
            stackPaneMap.put(coordinate,stackPane);
            root.getChildren().add(button);



        }
    }

}
