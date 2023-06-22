package edu.fiuba.algo3.View;


import edu.fiuba.algo3.View.Events.CleanButtonEventHandler;
import edu.fiuba.algo3.View.Events.ExitButtonEventHandler;
import edu.fiuba.algo3.View.Events.StartButtonEventHandler;
import edu.fiuba.algo3.View.Events.TextEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WelcomeConteiner extends VBox {

    Stage stage;
    OwnMenuBar menuBar;


    public WelcomeConteiner(Stage stage, Scene nextScene){
        super();

        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Imagenes/towerBackground.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(60,85,false,false,false,false));
        this.setBackground(new Background(backgroundImage));

        Button startButton = new Button("Start Game");

        Button exitButton = new Button("Exit");

        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler();
        exitButton.setOnAction(exitButtonEventHandler);

        Label label = new Label();
        label.setText("Welcome to AlgoDefense, a game of strategy and war mixed");
        label.setTextFill(Color.GREEN);
        label.setStyle("-fx-background-color: White");
        label.setMaxWidth(350);
        label.setMaxHeight(60);
        label.setAlignment(Pos.CENTER);

        this.getChildren().add(label);

        this.createUser(startButton, nextScene);

        this.getChildren().addAll(startButton, exitButton);

    }

    private void createUser(Button startButton, Scene nextScene) {


        TextField text = new TextField();
        text.setPromptText("Enter your name");
        text.setMaxWidth(300);


        Label label1 = new Label();
        label1.setText(text.getText());


        Button cleanButton = new Button();
        cleanButton.setText("Clean name");


        TextEventHandler textEventHandler = new TextEventHandler(startButton);
        text.setOnKeyPressed(textEventHandler);

        CleanButtonEventHandler cleanButtonEventHandler = new CleanButtonEventHandler(text);
        cleanButton.setOnAction(cleanButtonEventHandler);

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(text,label1,stage,nextScene);
        startButton.setOnAction(startButtonEventHandler);

        VBox conteiner = new VBox(text,label1,cleanButton);
        conteiner.setStyle("-fx-background-color: white");
        conteiner.setAlignment(Pos.CENTER);
        conteiner.setSpacing(20);
        conteiner.setPadding(new Insets(15));
        conteiner.setMaxWidth(350);

        this.getChildren().addAll(conteiner);

    }

}
