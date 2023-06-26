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

    public void showMap(AnchorPane root, VBox consoleContainer, Game game){
        Map<Coordinate, Plot> plots = this.map; // Acceder al campo "map" de la clase GameMap

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

            PlotButtonEventHandler plotButtonEventHandler = new PlotButtonEventHandler(consoleContainer, plot, game, coordinate, button);
            button.setOnAction(plotButtonEventHandler);

            /*Image overlayImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/owlOnly.png");
            ImageView overlayImageView = new ImageView(overlayImage);
            overlayImageView.setFitHeight(20);
            overlayImageView.setFitWidth(20);

            Image overlayImage1 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/antOnly.png");
            ImageView overlayImageView1 = new ImageView(overlayImage1);
            overlayImageView1.setFitHeight(20);
            overlayImageView1.setFitWidth(20);

            Image overlayImage2 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/moleOnly.png");
            ImageView overlayImageView2 = new ImageView(overlayImage2);
            overlayImageView2.setFitHeight(20);
            overlayImageView2.setFitWidth(20);

            Image overlayImage3 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/spiderOnly.png");
            ImageView overlayImageView3 = new ImageView(overlayImage3);
            overlayImageView3.setFitHeight(20);
            overlayImageView3.setFitWidth(20);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(overlayImageView,overlayImageView1,overlayImageView2,overlayImageView3);

            button.setGraphic(stackPane);*/

            root.getChildren().add(button);



        }
    }

}
