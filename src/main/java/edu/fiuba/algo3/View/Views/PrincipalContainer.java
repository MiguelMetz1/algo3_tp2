package edu.fiuba.algo3.View.Views;

import edu.fiuba.algo3.Model.Interface.Game;
import edu.fiuba.algo3.Model.TypeData.Coordinate.Coordinate;
import edu.fiuba.algo3.Model.TypeData.Name.Name;
import edu.fiuba.algo3.Controller.Events.BuyDefenseButtonEventHandler;
import edu.fiuba.algo3.Controller.Events.EndTurnButtonEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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

    VBox notificationsContainer;
    public PrincipalContainer(Stage stage, Game game, Name name){
        this.game = game;
        this.name = name;
        this.stackPaneMap = new HashMap<>();
        this.stage = stage;
        this.turn = 0;

        this.notificationsContainer = new VBox();
        this.setMessagePanel();
        this.setRight(this.consoleContainer);
        this.consoleContainer.setAlignment(Pos.CENTER);
        AnchorPane grid = new AnchorPane();
        this.setCenter(grid);
        this.setBottomContainer("");
        this.setBottom(notificationsContainer);
        this.mapView = new MapView( this, game, grid, this.consoleContainer );
        this.setUserInfoPanel(game);

        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:src/main/java/edu/fiuba/algo3/View/Images/principalBackground.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));
        this.setMenu(stage);

    }

    public void setBottomContainer(String message) {
        notificationsContainer.getChildren().clear();
        Label notificationLabel = new Label(message);
        notificationsContainer.setStyle("-fx-text-fill: white;-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");
        notificationLabel.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");
        notificationsContainer.setAlignment(Pos.TOP_CENTER);
        notificationLabel.setTextFill(Color.WHITE);
        notificationsContainer.prefHeight(50);
        notificationsContainer.prefWidth(10);
        notificationsContainer.getChildren().add(notificationLabel);
        notificationsContainer.toFront();
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

        Label userName = new Label("User:" + name.getName());

        userName.setStyle("-fx-text-fill: white;-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold ");

        Image userImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/userIcon.png");
        ImageView userImageView = new ImageView(userImage);
        userImageView.setFitWidth(30);
        userImageView.setFitHeight(30);
        VBox containerImage = new VBox(userImageView);
        containerImage.setPadding(new Insets(10));

        HBox user = new HBox(containerImage, userName);
        user.setStyle("-fx-text-fill: white;-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");
        user.prefHeight(50);


        VBox verticalContainer = new VBox(user,buyWhiteTower,buySandTrap,buySilverTower,endTurn, userInfo, entitiesInfo);

        verticalContainer.setSpacing(10);
        verticalContainer.setPadding(new Insets(15));

        this.setLeft(verticalContainer);
    }

    private void setButtonStyle(Button button) {

        button.setMaxWidth(300);

        LinearGradient defaultGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.ORANGE), new Stop(1, Color.RED));

        LinearGradient hoverGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.ORANGE.darker()), new Stop(1, Color.RED.darker()));

        button.setBackground(new Background(new BackgroundFill(defaultGradient, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        button.setTextFill(Color.BLACK);

        Light.Distant light = new Light.Distant();
        light.setAzimuth(-100.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);


        button.setFont(Font.font(null, FontWeight.BOLD, 18));
        button.setEffect(lighting);

        button.setOnMouseEntered(e -> button.setBackground(new Background(new BackgroundFill(hoverGradient, CornerRadii.EMPTY, Insets.EMPTY))));

        button.setOnMouseExited(e -> button.setBackground(new Background(new BackgroundFill(defaultGradient, CornerRadii.EMPTY, Insets.EMPTY))));
    }

    public void setUserInfoPanel(Game game){
        Label lifeLabel = new Label("Life: " + game.remainingLife());
        lifeLabel.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");
        lifeLabel.setTextFill(Color.WHITE);
        Label creditsLabel = new Label("Credits: " + game.remainingCredits());
        creditsLabel.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");
        creditsLabel.setTextFill(Color.WHITE);
        Label turnLabel = new Label("Turn: " + this.turn);
        turnLabel.setStyle("-fx-padding: 10px;-fx-font-size: 20px; -fx-font-weight: bold");

        turnLabel.setTextFill(Color.WHITE);
        VBox userInfo = new VBox(lifeLabel,creditsLabel, turnLabel);
        userInfo.setAlignment(Pos.BOTTOM_CENTER);
        userInfo.setStyle("-fx-font-size: 20px;-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");


        VBox entitiesInfo = new VBox();
        this.mapView.showEntitiesList(entitiesInfo);
        entitiesInfo.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        entitiesInfo.setPrefHeight(200);
        entitiesInfo.setAlignment(Pos.BOTTOM_CENTER);
        entitiesInfo.setStyle("-fx-font-size: 20px;-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");
        this.setButtonPanel(this.name,userInfo, entitiesInfo);

        //this.stage.getScene().getWindow().setWidth(this.stage.getWidth());

    }

    private void setMenu(Stage stage) {
        this.menuBar = new OwnMenuBar(stage);
        this.setTop(menuBar);
    }

    private void setMessagePanel(){
        Label label = new Label("");
        label.setTextFill(Color.WHITE);

        this.consoleContainer = new VBox(label);
        /*VBox consoleContainer = new VBox(label);*/
        this.consoleContainer.setSpacing(10);
        //consoleContainer.setStyle("-fx-background-color: black");
        this.consoleContainer.setStyle("-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");
        this.consoleContainer.setPrefHeight(200);
        this.consoleContainer.setPrefWidth(150);

        //VBox verticalConsole = new VBox(this.consoleContainer);
        //this.consoleContainer.setAlignment(Pos.CENTER);


    }

    public void updateMap() {
        this.turn++;
        this.mapView.updateMap();
    }
}

