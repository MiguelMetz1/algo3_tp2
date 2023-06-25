package edu.fiuba.algo3.View;

import edu.fiuba.algo3.AlgoDefense.AlgoDefense;
import edu.fiuba.algo3.TypeData.Name.Name;
import edu.fiuba.algo3.View.Events.ShowUserButtonEventHandler;
import javafx.geometry.Insets;
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


public class PrincipalContainer extends BorderPane {

    OwnMenuBar menuBar;
    Canvas centralCanvas;
    VBox centralConteiner;

    VBox consoleContainer;
    Name name;
    public PrincipalContainer(Stage stage, AlgoDefense algoDefense, Name name){
        this.name = name;
        this.setMenu(stage);
        this.setButtonPanel(name);
        this.setMessagePanel();

        this.showMap(algoDefense, this.consoleContainer);

        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/water.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        this.setBackground(new Background(backgroundImage));


    }

    private void setButtonPanel(Name name) {
        Button buyDefense = new Button();
        buyDefense.setText("Buy defense");
        setButtonStyle(buyDefense);


        Button endTurn = new Button();
        endTurn.setText("End turn");
        setButtonStyle(endTurn);



        Button user = new Button("Show username");
        user.setStyle("-fx-background-color: #fafafa; -fx-font-family: 'Minecraft'; -fx-font-size: 16px; -fx-padding: 10px;-fx-background-radius: 4px;");
        ShowUserButtonEventHandler showUserButtonEventHandler = new ShowUserButtonEventHandler(user, name);
        user.setOnAction(showUserButtonEventHandler);


        VBox verticalConteiner = new VBox(user,buyDefense,endTurn);
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
    }

    private void showMap(AlgoDefense algoDefense, VBox consoleContainer) {



        AnchorPane root = new AnchorPane();

        algoDefense.showMap(root, consoleContainer);


        this.centralConteiner = new VBox(root);
        this.centralConteiner.setMaxWidth(680);
        this.setCenter(this.centralConteiner);
       /* this.centralConteiner.setAlignment(Pos.CENTER);*/
        /*Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/water.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        this.centralConteiner.setBackground(new Background(backgroundImage));*/



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
        consoleContainer.setMinWidth(100);
        consoleContainer.setMinHeight(100);

        this.setBottom(consoleContainer);
    }

}

