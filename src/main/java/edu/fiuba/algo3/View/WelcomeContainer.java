package edu.fiuba.algo3.View;


import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.View.Events.ExitButtonEventHandler;
import edu.fiuba.algo3.View.Events.StartButtonEventHandler;
import edu.fiuba.algo3.View.Events.TextEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class WelcomeContainer extends VBox {

    Stage stage;
    Name name;

    AlgoDefense algoDefense;


    public WelcomeContainer(Stage stage, AlgoDefense algoDefense){
        super();

        this.algoDefense = algoDefense;
        //this.name = algoDefense.getName();
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
        label.setPadding(new Insets(20, 0, 0, 0));

        this.getChildren().add(label);

        this.createUser(startButton,name);

        this.getChildren().addAll(startButton, exitButton);

    }

    private void createUser(Button startButton, Name name) {


        TextField text = new TextField();
        text.setPromptText("Enter your name");
        text.setMaxWidth(300);
        text.setStyle("-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-font-style: white; -fx-border-width: 2px; -fx-border-color: black");

        text.setFont(Font.font("Arial", 18));


        Label label1 = new Label();
        label1.setText(text.getText());
        label1.setStyle("-fx-background-color: #ef4335;-fx-border-radius: 10px");



        TextEventHandler textEventHandler = new TextEventHandler(startButton);
        text.setOnKeyPressed(textEventHandler);

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(text,label1,stage, name,this.algoDefense);
        startButton.setOnAction(startButtonEventHandler);

        VBox conteiner = new VBox(text,label1);
        //conteiner.setStyle("-fx-background-color: white");
        conteiner.setAlignment(Pos.CENTER);
        conteiner.setSpacing(20);
        conteiner.setPadding(new Insets(10));
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
        //label.setStyle("-fx-padding: -20px 0 0 0");
    }

    private void styleButton(Button button){
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

        // Restaurar el color de fondo al salir del mouse
        button.setOnMouseExited(e -> button.setBackground(new Background(new BackgroundFill(defaultGradient, CornerRadii.EMPTY, Insets.EMPTY))));

    }



}