package edu.fiuba.algo3.View;

import edu.fiuba.algo3.GameMap.GameMap;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class PrincipalConteiner extends BorderPane {

    OwnMenuBar menuBar;
    Canvas centralCanvas;
    VBox centralConteiner;
    public PrincipalConteiner(Stage stage){
        this.setMenu(stage);
        this.setButtonPanel();

        showMap();


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
        this.setCenter(this.centralConteiner);
        this.centralConteiner.setAlignment(Pos.CENTER);
        Image image = new Image("file:src/main/java/edu/fiuba/algo3/View/Imagenes/water.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(30,30,false,false,false,true));
        this.centralConteiner.setBackground(new Background(backgroundImage));



    }

    private void setMenu(Stage stage) {
        this.menuBar = new OwnMenuBar(stage);
        this.setTop(menuBar);
    }

}

