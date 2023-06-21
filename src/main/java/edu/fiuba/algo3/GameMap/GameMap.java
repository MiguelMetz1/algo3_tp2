package edu.fiuba.algo3.GameMap;

import edu.fiuba.algo3.Defenses.Defense;
import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Exceptions.CannotBuild;
import edu.fiuba.algo3.Exceptions.UnespawnablePlace;
import edu.fiuba.algo3.Parsers.EnemiesJsonParser;
import edu.fiuba.algo3.Parsers.MapJsonParser;
import edu.fiuba.algo3.Players.Player;
import edu.fiuba.algo3.Plots.Gangway;
import edu.fiuba.algo3.Plots.InitialGangway;
import edu.fiuba.algo3.Plots.Plot;
import edu.fiuba.algo3.TypeData.Coordinate;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class GameMap {
    private static GameMap instance = new GameMap("src/mapa.json");
    private HashMap< Coordinate, Plot > map;

    private InitialGangway initialGangway;

    private GameMap(String json) {
        MapJsonParser mapParser = new MapJsonParser(json);
        this.map = mapParser.get();

        initialGangway = this.returnSpawnPoint();
    }

    private InitialGangway returnSpawnPoint() {
        InitialGangway spawn = null;
        Set<Coordinate> plots = this.map.keySet();
        Iterator <Coordinate> coordinateIterator = plots.iterator();
        EnemiesJsonParser enemiesParser = new EnemiesJsonParser("src/enemigos.json");
        Queue< ArrayList<Enemy> > enemiesToSpawn = enemiesParser.get();
        while (coordinateIterator.hasNext()){
            Coordinate actualCoordinate = coordinateIterator.next();
            Plot actualPlot = this.map.get(actualCoordinate);
            if (actualPlot.canSpawnEnemies()) {
                Gangway actualGangway = (Gangway) actualPlot;
                spawn = new InitialGangway(actualGangway, enemiesToSpawn);
                this.map.replace(actualCoordinate, spawn);
            }
        }
       return spawn;
    }

    public ArrayList<Plot> getAround(Coordinate coordinate, int distance) {
        ArrayList<Plot> plotList = new ArrayList<>();
        ArrayList<Coordinate> coordinates = coordinate.getAround(distance);

        Iterator <Coordinate> coordinateIterator = coordinates.iterator();

        while (coordinateIterator.hasNext()){
            Coordinate coord = coordinateIterator.next();
            /*coord.showCoords();*/
            Plot tmpPlot = this.map.get(coord);
            if (tmpPlot != null) {
                plotList.add(tmpPlot);
            }
        }
        return plotList;
    }

    public void build(Defense defense, Coordinate coordinate) throws CannotBuild {
        this.map.get(coordinate).build(defense);
    }

    public boolean canBuild(Defense defense, Coordinate coordinate){
        return map.get(coordinate).canBuild();
    }

    public static GameMap getMap() {
        return instance;
    }

    public void advanceEnemies(){
        initialGangway.advanceEnemies();
    }

    public void spawnEnemies(){

        initialGangway.spawnEnemies();

    }

    public static void resetMap(){
        instance = new GameMap("src/mapa.json");
    }

    public boolean plotHasEnemies(Coordinate coordinate){
        return (map.get(coordinate)).hasEnemies();
    }

/*    public void imprimirMapa(){
        for(int i = 1; i <= 15; i++){
            for(int j = 1; j <= 15; j++){
                System.out.print(" "+map.get(new Coordinate(i,j)).showPlotName());
            }
            System.out.println();
        }
    }*/

    public boolean MapHasEnemies() {
        Set<Coordinate> plots = this.map.keySet();
        Iterator <Coordinate> coordinateIterator = plots.iterator();

        while (coordinateIterator.hasNext()) {
            Coordinate currentCoordinate = coordinateIterator.next();
            if (this.map.get(currentCoordinate).hasEnemies()){
                return true;
            }
        }
        return false;
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


    public void showMap(AnchorPane root) {
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

            root.getChildren().add(button);
        }
    }

}
