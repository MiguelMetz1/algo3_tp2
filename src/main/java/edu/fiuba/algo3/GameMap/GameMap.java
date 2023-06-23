package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Enemies.Placeable;
import edu.fiuba.algo3.Exceptions.WrongPlace;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;
import edu.fiuba.algo3.View.Events.PlotButtonEventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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


    public HashMap< Coordinate, Plot > map(){
        return map;
    }

   /* public void showMap(VBox root) {
        Map<Coordinate, Plot> plots = this.map(); // Acceder al campo "map" de la clase GameMap

        for (Map.Entry<Coordinate, Plot> entry : plots.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Plot plot = entry.getValue();

            Rectangle rectangle = new Rectangle(coordinate.getX() * 40, coordinate.getY() * 40, 40, 40);
            rectangle.setFill(new ImagePattern(plot.getImage()));
            rectangle.setStroke(Color.BLACK);

            Pane rectanglePane = new Pane(rectangle);
            root.getChildren().add(rectanglePane);
        }
    }*/



    public void showMap(AnchorPane root, VBox consoleContainer) {
        Map<Coordinate, Plot> plots = this.map(); // Acceder al campo "map" de la clase GameMap

        for (Map.Entry<Coordinate, Plot> entry : plots.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Plot plot = entry.getValue();

            Button button = new Button();
            button.setLayoutX(coordinate.getX() * 40);
            button.setLayoutY(coordinate.getY() * 40);
            button.setPrefWidth(40);
            button.setPrefHeight(40);
            button.setBackground(new Background(new BackgroundImage(plot.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(40,40,false,false,false,true))));
            button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            button.setOnMouseEntered(event -> button.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
            button.setOnMouseExited(event -> button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));

            PlotButtonEventHandler plotButtonEventHandler = new PlotButtonEventHandler(consoleContainer, plot);
            button.setOnAction(plotButtonEventHandler);

            root.getChildren().add(button);
        }
    }

}
