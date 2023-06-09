package edu.fiuba.algo3.View.Views;


import edu.fiuba.algo3.Model.TypeData.Name.Name;
import edu.fiuba.algo3.Controller.Events.ExitButtonEventHandler;
import edu.fiuba.algo3.Controller.Events.StartButtonEventHandler;
import edu.fiuba.algo3.Controller.Events.TextEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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



    public WelcomeContainer(Stage stage){
        super();

        this.name = new Name("");

        this.stage = stage;
        stage.setResizable(false);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/algoDefenseFrontCover.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(backgroundImage));


        Button startButton = new Button("Start Game");
        styleButton(startButton);


        Button exitButton = new Button("Exit");
        styleButton(exitButton);

        ExitButtonEventHandler exitButtonEventHandler = new ExitButtonEventHandler();
        exitButton.setOnAction(exitButtonEventHandler);

        Label label = new Label();
        createTitle(label,"AlgoDefense",60);
        label.setPadding(new Insets(20, 0, 0, 0));

        this.getChildren().add(label);

        this.createUser(startButton,name);

        this.getChildren().addAll(startButton, exitButton);

    }

    private void createUser(Button startButton, Name name) {


        Label label = new Label();
        label.setMaxWidth(300);
        label.setBackground(Background.EMPTY);
        label.setAlignment(Pos.CENTER);
        createTitle(label,"Introduce your name",18);

        TextField text = new TextField();
        text.setPromptText("Enter your name");
        text.setMaxWidth(300);
        text.setStyle("-fx-text-fill: white;-fx-border-radius: 10px;-fx-background-radius: 10px;-fx-background-color: #7a5b3e; -fx-border-width: 2px; -fx-border-color: black");


        text.setFont(Font.font("Arial", 18));


        Label label1 = new Label();
        label1.setText(text.getText());
        label1.setStyle("-fx-background-color: #ef4300;-fx-border-radius: 10px");



        TextEventHandler textEventHandler = new TextEventHandler(startButton);
        text.setOnKeyPressed(textEventHandler);

        StartButtonEventHandler startButtonEventHandler = new StartButtonEventHandler(text,label1,stage, name);
        startButton.setOnAction(startButtonEventHandler);

        VBox conteiner = new VBox(label,text,label1);
        //conteiner.setStyle("-fx-background-color: white");
        conteiner.setAlignment(Pos.CENTER);
        conteiner.setSpacing(10);
        conteiner.setPadding(new Insets(5));
        conteiner.setMaxWidth(350);

        this.getChildren().addAll(conteiner);

    }

    private void createTitle(Label label, String string, int size){
        Text text = new Text(string);
        text.setFont(Font.font("Arial", FontWeight.BOLD,size));
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
