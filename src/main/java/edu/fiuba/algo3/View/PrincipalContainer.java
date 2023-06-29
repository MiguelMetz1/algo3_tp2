package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.View.Events.BuyDefenseButtonEventHandler;
import edu.fiuba.algo3.View.Events.EndTurnButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class PrincipalContainer extends BorderPane {

    OwnMenuBar menuBar;

    VBox consoleContainer;
    Name name;

    Game game;

    Stage stage;

    Map<Coordinate, StackPane> stackPaneMap;

    int turn;

    MapView mapView;
    public PrincipalContainer(Stage stage, Game game, Name name){
        this.game = game;
        this.name = name;
        this.stackPaneMap = new HashMap<>();
        this.stage = stage;
        this.turn = 0;
        this.setMessagePanel();
        AnchorPane grid = new AnchorPane();
        this.setCenter(grid);
        this.mapView = new MapView( this, game, grid, this.consoleContainer );
        this.setUserInfoPanel(game);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:src/main/java/edu/fiuba/algo3/View/Images/principalBackground.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        this.setMenu(stage);

        //Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/water.jpg");
        //BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        //this.setBackground(new Background(backgroundImage));


    }

    private void setButtonPanel(Name name, VBox userInfo, VBox entitiesInfo) {
        Button buyWhiteTower = new Button();
        buyWhiteTower.setText("Buy White Tower");
        setButtonStyle(buyWhiteTower);
        BuyDefenseButtonEventHandler buyWhiteTowerButtonEventHandler = new BuyDefenseButtonEventHandler(this, "White Tower",this.game);
        buyWhiteTower.setOnAction(buyWhiteTowerButtonEventHandler);

        Button buySilverTower = new Button();
        buySilverTower.setText("Buy Silver Tower");
        setButtonStyle(buySilverTower);
        BuyDefenseButtonEventHandler buySilverTowerButtonEventHandler = new BuyDefenseButtonEventHandler(this, "Silver Tower",this.game);
        buySilverTower.setOnAction(buySilverTowerButtonEventHandler);

        Button buySandTrap = new Button();
        buySandTrap.setText("Buy Sand Trap");
        setButtonStyle(buySandTrap);
        BuyDefenseButtonEventHandler buySandTrapButtonEventHandler = new BuyDefenseButtonEventHandler(this, "Sand Trap",this.game);
        buySandTrap.setOnAction(buySandTrapButtonEventHandler);



        Button endTurn = new Button();
        endTurn.setText("End turn");
        setButtonStyle(endTurn);


        EndTurnButtonEventHandler endTurnButtonEventHandler = new EndTurnButtonEventHandler(this.stage,this,this.game, this.stackPaneMap);
        endTurn.setOnAction(endTurnButtonEventHandler);



        Label user = new Label("User:" + name.getName());
        user.setStyle("-fx-background-color: #fafafa; -fx-font-family: 'Minecraft'; -fx-font-size: 16px; -fx-padding: 10px;-fx-background-radius: 4px;");


        VBox verticalConteiner = new VBox(user,buyWhiteTower,buySandTrap,buySilverTower,endTurn, userInfo, entitiesInfo);

        verticalConteiner.setSpacing(10);
        verticalConteiner.setPadding(new Insets(15));

        this.setLeft(verticalConteiner);
    }

    private void setButtonStyle(Button button) {

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-100.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);


        button.setFont(Font.font(null, FontWeight.BOLD, 20));
        button.setEffect(lighting);

        button.setMaxWidth(200);
    }

    public void setUserInfoPanel(Game game){
        Label lifeLabel = new Label("Life: " + game.remainingLife());
        lifeLabel.setTextFill(Color.WHITE);
        Label creditsLabel = new Label("Credits: " + game.remainingCredits());
        creditsLabel.setTextFill(Color.WHITE);
        Label turnLabel = new Label("Turn: " + this.turn);
        turnLabel.setTextFill(Color.WHITE);
        VBox userInfo = new VBox(lifeLabel,creditsLabel, turnLabel);

        userInfo.setAlignment(Pos.BOTTOM_CENTER);

        VBox entitiesInfo = new VBox();
        this.mapView.showEntitiesList(entitiesInfo);
        entitiesInfo.setStyle("-fx-background-color: Black;");
        entitiesInfo.setPrefHeight(200);
        entitiesInfo.setAlignment(Pos.BOTTOM_CENTER);
        this.setButtonPanel(this.name,userInfo, entitiesInfo);
        userInfo.setStyle("-fx-background-color: Black;");

        this.stage.getScene().getWindow().setWidth(this.stage.getWidth());

    }

    private void setMenu(Stage stage) {
        this.menuBar = new OwnMenuBar(stage);
        this.setTop(menuBar);
    }

    private void setMessagePanel(){
        Label label = new Label("Messages:");
        label.setTextFill(Color.WHITE);

        this.consoleContainer = new VBox(label);
        /*VBox consoleContainer = new VBox(label);*/
        consoleContainer.setSpacing(10);
        //consoleContainer.setStyle("-fx-background-color: black");
        consoleContainer.setMinWidth(200);
        consoleContainer.setMinHeight(100);

        this.setRight(consoleContainer);
    }

    public void updateMap() {
        this.turn++;
        this.mapView.updateMap();
    }
}

