package edu.fiuba.algo3.View.Events;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutButtonEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Map references");
        setMapReferences(alert);
        String message = "Initial Gangway: this is the plot where enemies spawn.\n\nFinal Gangway: this is the plot where the player is located and is the ed of the path.\n\n Ground: this is the plot where towers can be constructed.\n\nRocky: this is a plot where nothing can be constructed\n\nGangway: this is the plot which enemies such as spiders, ants and moles follow to reach the end of the path. Here is where sand traps can be constructed.\n";
        alert.setContentText(message);
        alert.show();
    }

    private void setMapReferences(Alert alert){
        Image image1 = new Image( "file:src/main/java/edu/fiuba/algo3/View/Images/orangeWool.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(20);
        imageView1.setFitWidth(20);
        Text text1 = new Text("Final Gangway");
        HBox hor1 = new HBox(imageView1,text1);

        Image image2 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/redWool.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(20);
        imageView2.setFitWidth(20);
        Text text2 = new Text("Initial Gangway");
        HBox hor2 = new HBox(imageView2,text2);

        Image image3 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/ground.png");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitHeight(20);
        imageView3.setFitWidth(20);
        Text text3 = new Text("Ground");
        HBox hor3 = new HBox(imageView3,text3);

        Image image4 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/rocky.jpg");
        ImageView imageView4 = new ImageView(image4);
        imageView4.setFitHeight(20);
        imageView4.setFitWidth(20);
        Text text4 = new Text("Rocky");
        HBox hor4 = new HBox(imageView4,text4);

        Image image5 = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/madera.jpg");
        ImageView imageView5 = new ImageView(image5);
        imageView5.setFitHeight(20);
        imageView5.setFitWidth(20);
        Text text5 = new Text("Gangway");
        HBox hor5 = new HBox(imageView5,text5);


        VBox vertical = new VBox(hor1,new Separator(),hor2,new Separator(),hor3,new Separator(),hor4,new Separator(),hor5);
        alert.setGraphic(vertical);


    }

}
