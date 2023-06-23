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
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


public class WelcomeConteiner extends VBox {

    Stage stage;
    OwnMenuBar menuBar;


    public WelcomeConteiner(Stage stage, Scene nextScene){
        super();

        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/algoDefenseFrontCover.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));


        Button startButton = new Button("Start Game");
        styleButton(startButton);


        Button exitButton = new Button("Exit");
        styleButton(exitButton);

        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler();
        exitButton.setOnAction(exitButtonEventHandler);

        Label label = new Label();
        createTitle(label);

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



        TextEventHandler textEventHandler = new TextEventHandler(startButton);
        text.setOnKeyPressed(textEventHandler);

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(text,label1,stage,nextScene);
        startButton.setOnAction(startButtonEventHandler);

        VBox conteiner = new VBox(text,label1);
        //conteiner.setStyle("-fx-background-color: white");
        conteiner.setAlignment(Pos.CENTER);
        conteiner.setSpacing(20);
        conteiner.setPadding(new Insets(15));
        conteiner.setMaxWidth(350);

        this.getChildren().addAll(conteiner);

    }

    private void createTitle(Label label){
        Text text = new Text("AlgoDefense");
        text.setFont(Font.font("Arial", FontWeight.BOLD,60));
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.ORANGE), new Stop(1, Color.RED));

        // Aplicar el degradado como relleno del texto
        text.setFill(gradient);

        text.setStroke(Color.BLACK);
        text.setStrokeWidth(2);
        text.setStrokeType(StrokeType.OUTSIDE);

        // Crear una sombra alrededor del texto
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);

        // Aplicar la sombra al texto
        text.setEffect(shadow);


        label.setGraphic(text);
        label.setStyle("-fx-padding: -20px 0 0 0");
    }

    private void styleButton(Button button){
        button.setMaxWidth(300);

        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.ORANGE), new Stop(1, Color.RED));

        button.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setStyle("-fx-border-width: 2px; -fx-border-color: black");

        button.setTextFill(Color.BLACK);
    }

}
