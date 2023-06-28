package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Enemies.Enemy;
import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class MapView {

    private Game game;
    private AnchorPane grid;

    private HashMap<Coordinate, StackPane> stackPanes;

    ArrayList<EnemyView> enemiesView;
    public MapView(Game game, AnchorPane grid){
        this.game = game;
        this.grid = grid;
        this.stackPanes = new HashMap<>();
        this.enemiesView = new ArrayList<>();
        this.paint();
        this.updateEnemies();
    }

    public HashMap<String, PlotView> imagesForName(Button button){
        HashMap<String, PlotView> plotsViews = new HashMap<>();
        plotsViews.put("Final Gangway", new FinalGangwayView(button));
        plotsViews.put("Gangway", new GangwayView(button));
        plotsViews.put("Initial Gangway", new InitialGangwayView(button));
        plotsViews.put("Rocky", new RockyView(button));
        plotsViews.put("Ground", new GroundView(button));
        return plotsViews;
    }

    private void paint(){
        double  dimension = game.getMapDimension();
        for (int height = 1; height <= dimension; height++) {
            for (int width = 1; width <= dimension; width++) {
                Coordinate actualCoordinate = new Coordinate( width, height);
                String plotStr = game.getMapPlotType( actualCoordinate );
                Button plotButton = new Button();
                plotButton.setLayoutX(width * 45);
                plotButton.setLayoutY(height * 45);
                plotButton.setPrefWidth(45);
                plotButton.setPrefHeight(45);
                StackPane stackPane = new StackPane();
                plotButton.setGraphic(stackPane);
                plotButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                PlotView plotView = this.imagesForName(plotButton).get(plotStr);
                stackPanes.put(actualCoordinate, stackPane);
                plotView.paint();
                grid.getChildren().add(plotButton);
            }
        }

    }

    public HashMap<String, EnemyView> enemyByType(StackPane stackPane){
        HashMap<String, EnemyView> plotsViews = new HashMap<>();
        plotsViews.put("Mole", new MoleView(stackPane));
        plotsViews.put("Owl", new OwlView(stackPane));
        plotsViews.put("Spider", new SpiderView(stackPane));
        plotsViews.put("Ant", new AntView(stackPane));
        return plotsViews;
    }

    public void updateEnemies(){
        this.eraseEnemies();
        this.paintEnemies();
    }

    private void paintEnemies(){
        HashMap<Coordinate, ArrayList<String>> enemiesInGame = game.getEnemiesInGameType();
        this.enemiesView = new ArrayList<>();
        for( Coordinate coordinate: enemiesInGame.keySet() ){
            ArrayList<String> enemiesInTheCoordinate = enemiesInGame.get(coordinate);
            for (String enemyType: enemiesInTheCoordinate){
                StackPane enemyStackPane = this.stackPanes.get(coordinate);
                EnemyView enemyView = this.enemyByType(enemyStackPane).get(enemyType);
                enemyView.paint();
                this.enemiesView.add(enemyView);
            }
        }

    }

    private void eraseEnemies(){
        for (EnemyView enemyView: enemiesView){
            enemyView.eraseEnemy();
        }
    }


}
