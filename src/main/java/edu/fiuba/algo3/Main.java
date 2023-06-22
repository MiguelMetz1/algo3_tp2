package edu.fiuba.algo3;

import edu.fiuba.algo3.View.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Game.main(args);
    }

    @Override
    public void start(Stage stage) throws Exception {}
}
/*
import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {
    private static GameMap gameMap;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        GameMap map = GameMap.getMap();
        Map<Coordinate, Plot> plots = map.map(); // Acceder al campo "map" de la clase GameMap

        for (Map.Entry<Coordinate, Plot> entry : plots.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Plot plot = entry.getValue();

            Rectangle rectangle = new Rectangle(coordinate.getX() * 40, coordinate.getY() * 40, 40, 40);
            rectangle.setFill(plot.getColor());
            root.getChildren().add(rectangle);
        }

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            gameMap = GameMap.getMap();
        } catch (RuntimeException e) {
            System.out.println("Error al obtener el mapa: " + e.getMessage());
            return;
        }

        launch(args);
    }
}*/