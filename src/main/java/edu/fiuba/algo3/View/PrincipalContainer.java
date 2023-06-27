package edu.fiuba.algo3.View;

import edu.fiuba.algo3.Interface.Game;
import edu.fiuba.algo3.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.View.Events.BuyDefenseButtonEventHandler;
import edu.fiuba.algo3.View.Events.EndTurnButtonEventHandler;
import edu.fiuba.algo3.View.Events.ShowUserButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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

    VBox centralConteiner;

    VBox consoleContainer;
    Name name;

    Game game;

    Stage stage;

    Map<Coordinate, Button> buttonMap;

    Map<Coordinate, StackPane> stackPaneMap;
    public PrincipalContainer(Stage stage, Game game, Name name){
        this.game = game;
        this.name = name;
        this.buttonMap = new HashMap<>();
        this.stackPaneMap = new HashMap<>();
        this.stage = stage;
        this.setMenu(stage);
        this.setMessagePanel();

        this.showMap(game, this.consoleContainer);
        //this.setButtonPanel(name);

        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/water.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        this.setBackground(new Background(backgroundImage));


    }

    private void setButtonPanel(Name name, VBox userInfo) {
        Button buyWhiteTower = new Button();
        buyWhiteTower.setText("Buy White Tower");
        setButtonStyle(buyWhiteTower);
        BuyDefenseButtonEventHandler buyWhiteTowerButtonEventHandler = new BuyDefenseButtonEventHandler("White Tower",this.game);
        buyWhiteTower.setOnAction(buyWhiteTowerButtonEventHandler);

        Button buySilverTower = new Button();
        buySilverTower.setText("Buy Silver Tower");
        setButtonStyle(buySilverTower);
        BuyDefenseButtonEventHandler buySilverTowerButtonEventHandler = new BuyDefenseButtonEventHandler("Silver Tower",this.game);
        buySilverTower.setOnAction(buySilverTowerButtonEventHandler);

        Button buySandTrap = new Button();
        buySandTrap.setText("Buy Sand Trap");
        setButtonStyle(buySandTrap);
        BuyDefenseButtonEventHandler buySandTrapButtonEventHandler = new BuyDefenseButtonEventHandler("Sand Trap",this.game);
        buySandTrap.setOnAction(buySandTrapButtonEventHandler);



        Button endTurn = new Button();
        endTurn.setText("End turn");
        setButtonStyle(endTurn);


        EndTurnButtonEventHandler endTurnButtonEventHandler = new EndTurnButtonEventHandler(this.stage,this,this.game,this.buttonMap, this.stackPaneMap);
        endTurn.setOnAction(endTurnButtonEventHandler);



        Label user = new Label("User:" + name.getName());
        user.setStyle("-fx-background-color: #fafafa; -fx-font-family: 'Minecraft'; -fx-font-size: 16px; -fx-padding: 10px;-fx-background-radius: 4px;");


        VBox verticalConteiner = new VBox(user,buyWhiteTower,buySandTrap,buySilverTower,endTurn, userInfo);

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

    private void showMap(Game game, VBox consoleContainer){



        AnchorPane root = new AnchorPane();

        game.showMap(this,root, consoleContainer,this.buttonMap, this.stackPaneMap);


        this.centralConteiner = new VBox(root);
        this.centralConteiner.setMaxWidth(680);
        this.setCenter(this.centralConteiner);

        game.enemiesImages(this.buttonMap,  this.stackPaneMap);

        game.defensesImage(this.buttonMap,this.stackPaneMap);

        HBox life = new HBox(new Label("Life: " + game.remainingLife()));
        life.setStyle("-fx-background-color: Black; -fx-padding: 0 0 0 0");
        HBox credits = new HBox(new Label("Credits: " + game.remainingCredits()));
        credits.setStyle("-fx-background-color: Black; -fx-padding: 0 0 0 0");
        VBox userInfo = new VBox(life,credits);
        userInfo.setStyle("-fx-padding: 100px 0 0 0");

        userInfo.setAlignment(Pos.BOTTOM_CENTER);

        this.setButtonPanel(this.name,userInfo);

        this.stage.getScene().getWindow().setWidth(this.stage.getWidth());



    }

    public void showMap(){
        this.showMap(this.game,this.consoleContainer);
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

}

