
package edu.fiuba.algo3.InterfazGrafica.javafx;

import edu.fiuba.algo3.GameMap.GameMap;
import edu.fiuba.algo3.InterfazGrafica.javafx.eventos.manejadores.*;
import edu.fiuba.algo3.TypeData.Life;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Integrador extends Application {

	private Stage stage;

	private Scene scene1;
	private VBox vbox1;
	private Button button1;
	private Button button3;

	private Scene scene2;
	private Scene scene3;
	private VBox vbox2;
	private Button button2;



	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		stage.setTitle("Switching Scenes");

		scene1 = createSceneOne();
		scene2 = createSceneTwo();
		scene3 = createSceneThree();

		stage.setScene(scene1);

		stage.show();
	}

	private Scene createSceneOne() {

		button2 = new Button("Start Game");
		button2.setOnAction(e -> {switchScenes(scene2); button2.setText("Resume Game");});  // Switch Scenes



		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			Platform.exit(); // Cierra la aplicación
			System.exit(0); // Termina el proceso Java
		});


		vbox2 = new VBox(button2,exitButton);
		vbox2.setStyle("-fx-background-color: White");
		vbox2.setSpacing(300);
		vbox2.setAlignment(Pos.TOP_CENTER);

		scene1 = new Scene(vbox2, 700, 400);


		return scene1;
	}

	private Scene createSceneTwo() {

		button1 = new Button("Go to map");
		button1.setOnAction(e -> switchScenes(scene3));  // Switch Scenes
		vbox1 = new VBox(button1);
		vbox1.setStyle("-fx-background-color: black");

		//scene1.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
		Life life = new Life(20);

		TextField text = new TextField();
		text.setPromptText("Enter your name");


		Label label1 = new Label();
		label1.setText(text.getText());

		Label label2 = new Label();

		Label label3 = new Label();


		Button sendButton = new Button();
		sendButton.setText("Send");

		Button cleanButton = new Button();
		cleanButton.setText("Clean text");

		Button addSpider = new Button();
		addSpider.setText("Add spider");

		Button addAnt = new Button();
		addAnt.setText("Add ant");

		VBox horizontalBox = new VBox(sendButton, cleanButton);
		horizontalBox.setSpacing(10);

		VBox enemyBox = new VBox(addSpider, addAnt);
		enemyBox.setSpacing(10);



		VBox principalBox = new VBox(button1,text, horizontalBox, label1, enemyBox, label2,label3);
		principalBox.setSpacing(10);
		principalBox.setPadding(new Insets(20));
		principalBox.setStyle("-fx-background-color: black");
		principalBox.setAlignment(Pos.TOP_CENTER);

		CleanButtonEventHandler cleanButtonEventHandler = new CleanButtonEventHandler(text);
		cleanButton.setOnAction(cleanButtonEventHandler);

		SendButtonEventHandler sendButtonEventHandler = new SendButtonEventHandler(text, label1);
		sendButton.setOnAction(sendButtonEventHandler);

		AddEnemiesButtonEventHandler addAntButtonEventHandler = new AddEnemiesButtonEventHandler(addAnt.getText(), label2, label3, life);
		addAnt.setOnAction(addAntButtonEventHandler);

		AddEnemiesButtonEventHandler addSpiderButtonEventHandler = new AddEnemiesButtonEventHandler(addSpider.getText(), label2, label3, life);
		addSpider.setOnAction(addSpiderButtonEventHandler);


		TextEventHandler textEventHandler = new TextEventHandler(sendButton);
		text.setOnKeyPressed(textEventHandler);


		scene2 = new Scene(principalBox, 800, 500);

		return scene2;

	}

	private Scene createSceneThree() {

		button3 = new Button("Main menu");
		button3.setOnAction(e -> switchScenes(scene1));

		Label name = new Label("Juan");

		AnchorPane root = new AnchorPane(button3);

		//Pane root = new Pane(button3);

		GameMap map = GameMap.getMap();

		map.showMap(root);


		AnchorPane.setTopAnchor(name, 10.0);
		AnchorPane.setRightAnchor(name, 10.0);
		root.getChildren().add(name);
		root.setStyle("-fx-background-color: white");

		Scene scene = new Scene(root, 700, 700);
		return scene;
	}

	// Switch Scenes in single Stage
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}