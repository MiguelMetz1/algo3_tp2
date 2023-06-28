package edu.fiuba.algo3.View.Events;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
        //alert.setContentText(message);

        alert.show();
    }

    private void setMapReferences(Alert alert){
        Image finalGangwayImage = new Image( "file:src/main/java/edu/fiuba/algo3/View/Images/orangeWool.png");
        ImageView finalGangwayImageView = new ImageView(finalGangwayImage);
        finalGangwayImageView.setFitHeight(20);
        finalGangwayImageView.setFitWidth(20);
        Text finalGangwayText = new Text("Final Gangway");
        HBox finalGangwayBox = new HBox(finalGangwayImageView,finalGangwayText);

        Image initialGangwayImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/initialGangway.png");
        ImageView initialGangwayImageView = new ImageView(initialGangwayImage);
        initialGangwayImageView.setFitHeight(20);
        initialGangwayImageView.setFitWidth(20);
        Text initialGangwayText = new Text("Initial Gangway");
        HBox initialGangwayBox = new HBox(initialGangwayImageView,initialGangwayText);

        Image groundImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/ground.png");
        ImageView groundImageView = new ImageView(groundImage);
        groundImageView.setFitHeight(20);
        groundImageView.setFitWidth(20);
        Text groundText = new Text("Ground");
        HBox groundBox = new HBox(groundImageView,groundText);

        Image rockyImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/rocky.jpg");
        ImageView rockyImageView = new ImageView(rockyImage);
        rockyImageView.setFitHeight(20);
        rockyImageView.setFitWidth(20);
        Text rockyText = new Text("Rocky");
        HBox rockyBox = new HBox(rockyImageView,rockyText);

        Image gangwayImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/gangway.jpg");
        ImageView gangwayImageView = new ImageView(gangwayImage);
        gangwayImageView.setFitHeight(20);
        gangwayImageView.setFitWidth(20);
        Text gangwayText = new Text("Gangway");
        HBox gangwayBox = new HBox(gangwayImageView,gangwayText);

        Image antImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/ant.png");
        ImageView antImageView = new ImageView(antImage);
        antImageView.setFitHeight(20);
        antImageView.setFitWidth(20);
        Text antText = new Text("Ant");
        HBox antBox = new HBox(antImageView,antText);

        Image moleImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/mole.png");
        ImageView moleImageView = new ImageView(moleImage);
        moleImageView.setFitHeight(20);
        moleImageView.setFitWidth(20);
        Text moleText = new Text("Mole");
        HBox moleBox = new HBox(moleImageView,moleText);

        Image owlImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/owl.png");
        ImageView owlImageView = new ImageView(owlImage);
        owlImageView.setFitHeight(20);
        owlImageView.setFitWidth(20);
        Text owlText = new Text("Owl");
        HBox owlBox = new HBox(owlImageView,owlText);

        Image spiderImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/spider.png");
        ImageView spiderImageView = new ImageView(spiderImage);
        spiderImageView.setFitHeight(20);
        spiderImageView.setFitWidth(20);
        Text spiderText = new Text("Spider");
        HBox spiderBox = new HBox(spiderImageView,spiderText);

        Image whiteTowerImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/whiteTower.png");
        ImageView whiteTowerImageView = new ImageView(whiteTowerImage);
        whiteTowerImageView.setFitHeight(20);
        whiteTowerImageView.setFitWidth(20);
        Text whiteTowerText = new Text("White Tower");
        HBox whiteTowerBox = new HBox(whiteTowerImageView,whiteTowerText);

        Image silverTowerImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/silverTower.png");
        ImageView silverTowerImageView = new ImageView(silverTowerImage);
        silverTowerImageView.setFitHeight(20);
        silverTowerImageView.setFitWidth(20);
        Text silverTowerText = new Text("Silver Tower");
        HBox silverTowerBox = new HBox(silverTowerImageView,silverTowerText);

        Image sandTrapImage = new Image("file:src/main/java/edu/fiuba/algo3/View/Images/sandTrap.jpeg");
        ImageView sandTrapImageView = new ImageView(sandTrapImage);
        sandTrapImageView.setFitHeight(20);
        sandTrapImageView.setFitWidth(20);
        Text sandTrapText = new Text("Sand Trap");
        HBox sandTrapBox = new HBox(sandTrapImageView,sandTrapText);

        Button plots = new Button("Show Plot Instructions");
        ShowMessageButtonEventHandler plotsInfoButtonEventHandler = new ShowMessageButtonEventHandler("Initial Gangway: this is the plot where enemies spawn.\n" +
                "\n" +
                "Final Gangway: this is the plot where the player is located and is the ed of the path.\n" +
                "\n" +
                "Ground: this is the plot where towers can be constructed.\n" +
                "\n" +
                "Rocky: this is a plot where nothing can be constructed\n" +
                "\n" +
                "Gangway: this is the plot which enemies such as spiders, ants and moles follow to reach the end of the path. Here is where sand traps can be constructed.");
        plots.setOnAction(plotsInfoButtonEventHandler);

        VBox plotsVBox = new VBox(finalGangwayBox,new Separator(),initialGangwayBox,new Separator(),groundBox,new Separator(),rockyBox,new Separator(),gangwayBox, new Separator(), plots);
        plotsVBox.setMinWidth(200);


        Button enemies = new Button("Show Enemy Instructions");
        ShowMessageButtonEventHandler enemiesInfoButtonEventHandler = new ShowMessageButtonEventHandler("Ant: advances 1 position per turn and makes 1 damage to the player when it reaches the end. It has 1 point of life but if it gets killed, 1 credits are given to the character. After 10 ants get killed they start to give 2 credits.\n" +
                "\n" +
                "Spider: advances 2 positions per turn and makes 2 damage to the player when it reaches the end. It has 2 points of life but the credits it gives to the character are a random number between 1 and 10.\n" +
                "\n" +
                "Mole: advances 1 position on the first 5 movements, then 2 on the next 5, and 3 after the 11th. If he reaches the end on an even turn makes 2 damage and 5 on odd ones. It has no life because it can't be damaged. \n" +
                "\n" +
                "Owl: advances 5 positions on \"L\" style to the end, but if it health is reduced to its 50% it goes directly to the end on the shortest path. When it reaches the end, the first constructed tower gets deleted. It has 5 points of life but if it gets killed, no credits are given to the character.");
        enemies.setOnAction(enemiesInfoButtonEventHandler);

        VBox enemiesVBox = new VBox(antBox,new Separator(),moleBox,new Separator(),owlBox,new Separator(),spiderBox, new Separator(), enemies);
        enemiesVBox.setMinWidth(200);

        Button defenses = new Button("Show Defense Instructions");
        ShowMessageButtonEventHandler defensesInfoButtonEventHandler = new ShowMessageButtonEventHandler("Silver Tower: it attacks in a range of 5 plots and makes 2 points of damage. It gets constructed in 2 turns and then it can attack and costs 20 credits.\n" +
                "\n" +
                "White Tower: it attacks in a range of 3 plots and makes 1 points of damage. It gets constructed in 1 turns and then it can attack and costs 10 credits\n" +
                "\n" +
                "Sand Trap: it reduces enemies speed in half. It lasts for 3 turns and costs 25 credits.");
        defenses.setOnAction(defensesInfoButtonEventHandler);

        VBox defensesVBox = new VBox(whiteTowerBox,new Separator(),silverTowerBox,new Separator(),sandTrapBox, new Separator(), defenses);
        defensesVBox.setMinWidth(200);


        HBox principalBox = new HBox(plotsVBox,enemiesVBox,defensesVBox);
        principalBox.setAlignment(Pos.CENTER);


        principalBox.setMinWidth(700);
        alert.setGraphic(principalBox);

    }

}
