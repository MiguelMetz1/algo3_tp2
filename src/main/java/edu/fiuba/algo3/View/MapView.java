package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.View.Events.PlotButtonEventHandler;
import edu.fiuba.algo3.View.Events.PlotInfoEventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class MapView {

    private Game game;
    private AnchorPane grid;

    private HashMap<Coordinate, StackPane> stackPanes;

    private HashMap<Coordinate, Button> buttons;

    private VBox consoleContainer;

    private ArrayList<Paintable> paintablesView;
    public MapView(Game game, AnchorPane grid, VBox consoleContainer){
        this.game = game;
        this.grid = grid;
        this.stackPanes = new HashMap<>();
        this.buttons = new HashMap<>();
        this.paintablesView = new ArrayList<>();
        this.consoleContainer = consoleContainer;
        this.paint();
        this.updateMap();
    }

    private HashMap<String, PlotView> plotViewByName(Button button){
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

        for (int yPoition = 1; yPoition <= dimension; yPoition++) {
            for (int xPosition = 1; xPosition <= dimension; xPosition++) {
                Coordinate actualCoordinate = new Coordinate( xPosition, yPoition);
                String plotString = game.getMapPlotType( actualCoordinate );
                Button plotButton = new Button();
                plotButton.setLayoutX(xPosition * 45);
                plotButton.setLayoutY(yPoition * 45);
                plotButton.setPrefWidth(45);
                plotButton.setPrefHeight(45);
                StackPane stackPane = new StackPane();
                plotButton.setGraphic(stackPane);
                plotButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                PlotView plotView = this.plotViewByName(plotButton).get(plotString);
                stackPanes.put(actualCoordinate, stackPane);
                this.buttons.put(actualCoordinate, plotButton);
                plotButton.setOnMouseEntered( new PlotInfoEventHandler(plotButton, actualCoordinate, consoleContainer, game));
                plotButton.setOnMouseExited(event -> { plotButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));});
                plotButton.setOnAction(new PlotButtonEventHandler(game, this, actualCoordinate));
                plotView.paint();
                grid.getChildren().add(plotButton);
            }
        }

    }

    private HashMap<String, Paintable> paintableByType(StackPane stackPane, AnchorPane grid, Button button, Coordinate coordinate){
        HashMap<String, Paintable> paintablesViews = new HashMap<>();
        paintablesViews.put("Mole", new MoleView(stackPane));
        paintablesViews.put("Owl", new OwlView(stackPane));
        paintablesViews.put("Spider", new SpiderView(stackPane));
        paintablesViews.put("Ant", new AntView(stackPane));
        paintablesViews.put("White Tower", new WhiteTowerView(stackPane));
        paintablesViews.put("Silver Tower", new SilverTowerView(stackPane, grid, button, coordinate));
        paintablesViews.put("SandTrap", new SandTrapView(stackPane));
        return paintablesViews;
    }

    public void updateMap(){
        this.erasePaintables();
        this.paintPaintables();
    }

    private void paintPaintables(){
        HashMap<Coordinate, ArrayList<String>> enemiesInGame = game.findEntities();
        this.paintablesView = new ArrayList<>();
        for( Coordinate coordinate: enemiesInGame.keySet() ){
            ArrayList<String> enemiesInTheCoordinate = enemiesInGame.get(coordinate);
            for (String enemyType: enemiesInTheCoordinate){
                if( this.stackPanes.containsKey(coordinate) ) {
                    StackPane enemyStackPane = this.stackPanes.get(coordinate);
                    Paintable paintableView = this.paintableByType(enemyStackPane, grid, buttons.get(coordinate), coordinate).get(enemyType);
                    paintableView.paint();
                    this.paintablesView.add(paintableView);
                }
            }
        }

    }

    private void erasePaintables(){
        for (Paintable paintableView: paintablesView){
            paintableView.erasePaintables();
        }
    }


}
