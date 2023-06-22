package edu.fiuba.algo3.View;

import edu.fiuba.algo3.GameMap.GameMap;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class PrincipalConteiner extends BorderPane {

    OwnMenuBar menuBar;
    Canvas centralCanvas;
    VBox centralConteiner;
    public PrincipalConteiner(Stage stage){
        this.setMenu(stage);
        this.setButtonPanel();
        this.setMessagePanel();

        this.showMap();


    }

    private void setButtonPanel() {
        Button addSpider = new Button();
        addSpider.setText("Add spider");

        Button addAnt = new Button();
        addAnt.setText("Add ant");

        VBox verticalConteiner = new VBox(addSpider,addAnt);
        verticalConteiner.setSpacing(10);
        verticalConteiner.setPadding(new Insets(15));

        this.setLeft(verticalConteiner);
    }

    private void showMap() {


        GameMap map = GameMap.getMap();

        AnchorPane root = new AnchorPane();

        map.showMap(root);


        this.centralConteiner = new VBox(root);
        this.centralConteiner.setMaxWidth(680);
        this.setCenter(this.centralConteiner);
       /* this.centralConteiner.setAlignment(Pos.CENTER);*/
        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Imagenes/water.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        this.centralConteiner.setBackground(new Background(backgroundImage));



    }

    private void setMenu(Stage stage) {
        this.menuBar = new OwnMenuBar(stage);
        this.setTop(menuBar);
    }

    private void setMessagePanel(){
        Label label = new Label("Messages:");
        label.setTextFill(Color.WHITE);

        VBox consoleContainer = new VBox(label);
        consoleContainer.setSpacing(10);
        consoleContainer.setStyle("-fx-background-color: black");
        consoleContainer.setMinWidth(100);

        this.setRight(consoleContainer);
    }

}

