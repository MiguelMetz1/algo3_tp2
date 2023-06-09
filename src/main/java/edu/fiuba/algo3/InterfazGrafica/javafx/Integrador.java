
package edu.fiuba.algo3.InterfazGrafica.javafx;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Integrador extends Application {

	private Stage stage;

	private Scene scene1;
	private VBox vbox1;
	private Button button1;

	private Scene scene2;
	private VBox vbox2;
	private Button button2;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		stage.setTitle("Switching Scenes");

		scene1 = createSceneOne();
		scene2 = createSceneTwo();

		stage.setScene(scene2);

		stage.show();
	}

	private Scene createSceneOne() {

		button1 = new Button("Main menu");
		button1.setOnAction(e -> switchScenes(scene2));  // Switch Scenes
		/*vbox1 = new VBox(button1);
		vbox1.setStyle("-fx-background-color: black");*/

		//scene1.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
		Life life = new Life(20);

		TextField texto = new TextField();
		texto.setPromptText("Ingrese su nombre");

		Label etiqueta = new Label();
		etiqueta.setText(texto.getText());

		Label etiqueta2 = new Label();

		Label etiqueta3 = new Label();



		Button botonEnviar = new Button();
		botonEnviar.setText("Enviar");

		Button botonLimpiar = new Button();
		botonLimpiar.setText("Limpiar cuadro texto");

		Button agregarArania = new Button();
		agregarArania.setText("Agregar Arania");

		Button agregarHormiga = new Button();
		agregarHormiga.setText("Agregar Hormiga");

		VBox contenedorHorizontal = new VBox(botonEnviar, botonLimpiar);
		contenedorHorizontal.setSpacing(10);
/*
		VBox contenedorEnemigo = new VBox(agregarArania, agregarHormiga);
		contenedorEnemigo.setSpacing(10);*/



		VBox contenedorPrincipal = new VBox(button1,texto, contenedorHorizontal, etiqueta, agregarArania, agregarHormiga,etiqueta3, etiqueta2);
		contenedorPrincipal.setSpacing(10);
		contenedorPrincipal.setPadding(new Insets(20));
		contenedorPrincipal.setStyle("-fx-background-color: black");
		contenedorPrincipal.setAlignment(Pos.TOP_CENTER);

		BotonLimpiarEventHandler botonLimpiarEventHandler = new BotonLimpiarEventHandler(texto);
		botonLimpiar.setOnAction(botonLimpiarEventHandler);

		BotonEnviarEventHandler botonEnviarEventHandler = new BotonEnviarEventHandler(texto, etiqueta);
		botonEnviar.setOnAction(botonEnviarEventHandler);

		BotonAgregarEnemigosEventHandler botonAgregarAraniaEventHandler = new BotonAgregarEnemigosEventHandler(agregarHormiga.getText(), etiqueta2, etiqueta3, life);
		agregarHormiga.setOnAction(botonAgregarAraniaEventHandler);

		BotonAgregarEnemigosEventHandler botonAgregarHormigaEventHandler = new BotonAgregarEnemigosEventHandler(agregarArania.getText(), etiqueta2, etiqueta3, life);
		agregarArania.setOnAction(botonAgregarHormigaEventHandler);


		TextoEventHandler textoEventHandler = new TextoEventHandler(botonEnviar);
		texto.setOnKeyPressed(textoEventHandler);

		scene1 = new Scene(contenedorPrincipal, 800, 500);

		return scene1;
	}

	private Scene createSceneTwo() {

		button2 = new Button("Start Game");
		button2.setOnAction(e -> {switchScenes(scene1); button2.setText("Resume Game");});  // Switch Scenes



		Button botonSalir = new Button("Exit");
		botonSalir.setOnAction(e -> {
			Platform.exit(); // Cierra la aplicación
			System.exit(0); // Termina el proceso Java
		});


		vbox2 = new VBox(button2,botonSalir);
		vbox2.setStyle("-fx-background-color: White");
		vbox2.setSpacing(300);
		vbox2.setAlignment(Pos.TOP_CENTER);

		scene2 = new Scene(vbox2, 700, 400);


		return scene2;
	}

	// Switch Scenes in single Stage
	public void switchScenes(Scene scene) {
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}